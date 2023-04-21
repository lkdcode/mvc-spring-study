package com.spring.mvc.chap04.dto;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor // final 만 골라서 초기화
public class ScoreListResponseDTO {
    private final String maskingName; // 첫글자 빼고 * 처리
    private final int stuNum;
    private final double average;
    private final Grade grade;

    public ScoreListResponseDTO(Score score) {
        this.maskingName = makeMaskingName(score.getName());
        this.stuNum = score.getStuNum();
        this.average = score.getAverage();
        this.grade = score.getGrade();
    }

    private String makeMaskingName(String name) {
        return (name.charAt(0)) + "*".repeat(name.length() - 1);
    }
}