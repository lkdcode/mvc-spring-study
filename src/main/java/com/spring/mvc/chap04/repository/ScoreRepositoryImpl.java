package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import static com.spring.mvc.chap04.entity.Grade.*;
import static java.util.stream.Collectors.toList;

@Repository // 스프링 빈 등록 : 객체 생성의 제어권을 스프링에게 위임
public class ScoreRepositoryImpl implements ScoreRepository {
    // key: 학번, value: 성적정보
    private static final Map<Integer, Score> scoreMap;

    // 학번에 사용할 일련번호
    private static int sequence;

    static {
        scoreMap = new HashMap<>();
        System.out.println(sequence);
        Score stu1 = new Score(new ScoreRequestDTO("뽀로로", 100, 50, 70, ++sequence));
        System.out.println(sequence);
        Score stu2 = new Score(new ScoreRequestDTO("춘식이", 33, 56, 12, ++sequence));
        System.out.println(sequence);
        Score stu3 = new Score(new ScoreRequestDTO("대길이", 88, 12, 0, ++sequence));
        System.out.println(sequence);

        scoreMap.put(stu1.getStuNum(), stu1);
        scoreMap.put(stu2.getStuNum(), stu2);
        scoreMap.put(stu3.getStuNum(), stu3);
    }

    @Override
    public List<Score> findAll(String sort) {
        Comparator<Score> comparing;

        switch (sort) {
            case "name":
                comparing = Comparator.comparing(Score::getName);
                break;
            case "avg":
                comparing = Comparator.comparing(Score::getAverage);
                break;
            case "num":
            default:
                comparing = Comparator.comparing(Score::getStuNum);
        }

        return new ArrayList<>(scoreMap.values()).stream()
                .sorted(comparing)
                .collect(toList());
    }

    @Override
    public List<Score> findAll() {
        return new ArrayList<>(scoreMap.values());
    }

    @Override
    public boolean save(Score score) {
        if (scoreMap.containsKey(score.getStuNum())) return false;
        score.setStuNum(++sequence);
        scoreMap.put(score.getStuNum(), score);
        return true;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        if (!scoreMap.containsKey(stuNum)) return false;
        scoreMap.remove(stuNum);
        return true;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        return scoreMap.get(stuNum);
    }

    @Override
    public void modify(ScoreRequestDTO dto) {
        System.out.println("수정전 scoreMap = " + scoreMap);
        // 2번

        scoreMap.put(dto.getStuNum(), new Score(dto)); // <- 0
        System.out.println("수정후 scoreMap = " + scoreMap);
        // 2번
    }

}
