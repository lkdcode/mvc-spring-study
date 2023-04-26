package com.spring.mvc.chap04.repository;


import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("jdbc")
public class ScoreJdbcRepository implements ScoreRepository {
    private final String URL = "jdbc:mariadb://localhost:3306/spring";
    private final String USERNAME = "root";
    private final String PASSWORD = "1234";

    public ScoreJdbcRepository() {
        try {
            // 드라이버 클래스를 로딩 (mariadb 커넥터 로딩)
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Score> findAll() {
        return null;
    }

    @Override
    public List<Score> findAll(String sort) {
        List<Score> scoreList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            String sql = "SELECT * FROM tbl_score";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                scoreList.add(new Score(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return scoreList;
    }

    @Override
    public boolean save(Score score) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            conn.setAutoCommit(false);
            String sql = "INSERT INTO tbl_score " +
                    "(name, kor, eng, math, total, average, grade) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, score.getName());
            pstmt.setInt(2, score.getKor());
            pstmt.setInt(3, score.getEng());
            pstmt.setInt(4, score.getMath());
            pstmt.setInt(5, score.getTotal());
            pstmt.setDouble(6, score.getAverage());
            pstmt.setString(7, String.valueOf(score.getGrade()));

            int result = pstmt.executeUpdate();

            if (result > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            conn.setAutoCommit(false);
            String sql = "DELETE FROM tbl_score WHERE stu_num = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stuNum);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Score findByStuNum(int stuNum) {

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            conn.setAutoCommit(false);
            String sql = "SELECT * FROM tbl_score WHERE stu_num = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stuNum);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) return new Score(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void modify(ScoreRequestDTO dto) {
        // boolean
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            conn.setAutoCommit(false);
            String sql = "UPDATE tbl_score SET kor = ?, eng = ?, math = ?, WHERE stu_num = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            int result = pstmt.executeUpdate();


            if (result > 0) {
                conn.commit();
//                return true;
            } else {
                conn.rollback();
                // return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            // return false;
        }

        // return false;
    }
}
