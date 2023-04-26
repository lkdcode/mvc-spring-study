package com.spring.mvc.chap04.entity;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.spring.mvc.chap04.entity.Grade.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    private String name; // 학생 이름
    private int kor, eng, math; // 국, 영, 수 점수

    private int stuNum; // 학번
    private int total; // 총점
    private double average; // 평균
    private Grade grade; // 학점

    public Score(ScoreRequestDTO dto) {
        this.name = dto.getName();
        this.kor = dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();
        this.stuNum = dto.getStuNum();
        calcTotalAndAvg(); // 총점, 평균 계산
        calcGrade(); // 학점 계산
    }

    public Score(ResultSet rs) throws SQLException {
        this.stuNum = rs.getInt("stu_num");
        this.name = rs.getString("name");
        this.kor = rs.getInt("kor");
        this.eng = rs.getInt("eng");
        this.math = rs.getInt("math");
        this.total = rs.getInt("total");
        this.average = rs.getDouble("average");
        this.grade = Grade.valueOf(rs.getString("grade"));
    }

    private void calcTotalAndAvg() {
        this.total = this.kor + this.eng + this.math;
        double result = this.total / 3.0;
        this.average = Math.round(result * 100.0) / 100.0;
    }

    private void calcGrade() {
        if (this.average >= 90) {
            this.grade = A;
        } else if (this.average >= 80) {
            this.grade = B;
        } else if (this.average >= 70) {
            this.grade = C;
        } else if (this.average >= 60) {
            this.grade = D;
        } else {
            this.grade = F;
        }
    }

}
