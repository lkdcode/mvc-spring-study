package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.toList;

@Repository("memory") // 스프링 빈 등록 : 객체 생성의 제어권을 스프링에게 위임
public class ScoreRepositoryImpl implements ScoreRepository {
    // key: 학번, value: 성적정보
    private static final Map<Integer, Score> scoreMap;

    // 학번에 사용할 일련번호
    private static int sequence;

    static {
        scoreMap = new HashMap<>();
        Score stu1 = new Score(new ScoreRequestDTO("뽀로로", 100, 50, 70, ++sequence));
        Score stu2 = new Score(new ScoreRequestDTO("춘식이", 33, 56, 12, ++sequence));
        Score stu3 = new Score(new ScoreRequestDTO("대길이", 88, 12, 0, ++sequence));

        scoreMap.put(stu1.getStuNum(), stu1);
        scoreMap.put(stu2.getStuNum(), stu2);
        scoreMap.put(stu3.getStuNum(), stu3);
    }

    @Override
    public List<Score> findAll(String sort) {
        Comparator<Score> comparing;

        switch (sort) {
            case "name":
                comparing = comparing(Score::getName);
                break;
            case "avg":
                comparing = comparing(Score::getAverage);
                break;
            case "num":
            default:
                comparing = comparing(Score::getStuNum);
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
        scoreMap.put(dto.getStuNum(), new Score(dto));
    }

}
