package com.spring.mvc.jdbc;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class PersonRepository {
    private final String URL = "jdbc:mariadb://localhost:3306/spring";
    private final String USERNAME = "root";
    private final String PASSWORD = "1234";

    public PersonRepository() {
        try {
            // 드라이버 클래스를 로딩 (mariadb 커넥터 로딩)
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 사람 정보 저장 기능
    public void save(Person person) {
        Connection conn = null;
        try {
            // DB 연결
            // Connection : DB 연결 정보를 가지며,
            // SQL 을 작성할 수 있는 statement 객체를 받을 수 있음

            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 트랜잭션 시작
            conn.setAutoCommit(false); // 오토커밋 비활성화

            // SQL 을 실행해주는 갣체 얻기
            String sql = "INSERT INTO person (person_name, person_age) VALUES (?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            // ? 값 세팅하기
            pstmt.setString(1, person.getPersonName());
            pstmt.setInt(2, person.getPersonAge());

            // sql 실행하기
            // INSERT, UPDATE, DELETE : executeUpdate()
            // SELECT : executeQuery()

            // executeUpdate() 는 성공한 쿼리의 수를 알려줌.
            // executeQuery() 는 데이터값을 리턴
            int result = pstmt.executeUpdate();

            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void update(Person person) {
        Connection conn = null;
        try {
            // DB 연결
            // Connection : DB 연결 정보를 가지며,
            // SQL 을 작성할 수 있는 statement 객체를 받을 수 있음

            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 트랜잭션 시작
            conn.setAutoCommit(false); // 오토커밋 비활성화

            // SQL 을 실행해주는 갣체 얻기
            String sql = "UPDATE person SET person_name=?, person_age=? WHERE id=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            // ? 값 세팅하기
            pstmt.setString(1, person.getPersonName());
            pstmt.setInt(2, person.getPersonAge());
            pstmt.setLong(3, person.getId());


            // sql 실행하기
            // INSERT, UPDATE, DELETE : executeUpdate()
            // SELECT : executeQuery()

            // executeUpdate() 는 성공한 쿼리의 수를 알려줌.
            // executeQuery() 는 데이터값을 리턴
            int result = pstmt.executeUpdate();

            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // 정보 삭제
    public void remove(long id) {
        Connection conn = null;
        try {
            // DB 연결
            // Connection : DB 연결 정보를 가지며,
            // SQL 을 작성할 수 있는 statement 객체를 받을 수 있음

            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 트랜잭션 시작
            conn.setAutoCommit(false); // 오토커밋 비활성화

            // SQL 을 실행해주는 갣체 얻기
            String sql = "DELETE FROM person WHERE id=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            // ? 값 세팅하기
            pstmt.setLong(1, id);


            // sql 실행하기
            // INSERT, UPDATE, DELETE : executeUpdate()
            // SELECT : executeQuery()

            // executeUpdate() 는 성공한 쿼리의 수를 알려줌.
            // executeQuery() 는 데이터값을 리턴
            int result = pstmt.executeUpdate();

            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


}