package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreRepositoryImplTest {

    ScoreRepository repository = new ScoreRepositoryImpl();

    // 단위 테스트 (Unit test)
    // 테스트 시나리오
    // - 단언(Assertion) 기법

    @Test
    @DisplayName("저장소에서 findAll 을 호출하면 그 반환된 리스트에는 성적 정보가 3개 있어야 한다.")
    void findAllTest() {
        // given : 테스트를 위해 주어질 데이터 (ex : parameter)

        // when : 테스트 실제 상황
        List<Score> scoreList = repository.findAll();

        // then : 테스트 결과 확인
//        assertTrue(scoreList.size() == 43);
//        assertEquals(43, scoreList.size());
        Assertions.assertThat(scoreList.size()).isEqualTo(3);
        assertEquals("뽀로로", scoreList.get(0).getName());
    }


    @Test
    @DisplayName("저장소에서 findByStuNum 을 호출하여 학번이 2인 학생을 조회하면" +
            "그 학생의 국어 점수가 33점이고 이름이 춘식이일 것이다")
    void findByStuNumTest() {
        // given
        int stuNum = 2;

        // when
        Score score = repository.findByStuNum(stuNum);

        // then
        assertEquals(33, score.getKor());
        assertEquals("춘식이", score.getName());
    }

    @Test
    @DisplayName("저장소에서 findByStuNum 을 호출하여 학번이 99인 학생을 조회하면" +
            "null")
    void findByStuNumTest2() {
        // given
        int stuNum = 99;

        // when
        Score score = repository.findByStuNum(stuNum);

        // then
        assertNull(score);
    }

    @Test
    @DisplayName("저장소에서 학번이 2인 학생을 삭제한 후에 " +
            "리스트를 전체 조회해보면 성적의 개수가 2개일 것이고" +
            "다시 2번 학생을 조회했을 때 null 이 반환되어야 한다.")
    void deleteTest() {
        // given
        int stuNum = 2;

        // when
        assertEquals(3, repository.findAll().size());
        repository.deleteByStuNum(stuNum);

        // then
        assertEquals(2, repository.findAll().size());
        assertNull(repository.findByStuNum(stuNum));
    }


    @Test
    @DisplayName("저장소에서 학번이 2인 학생을 삭제한 후에" +
            "리스트를 전체조회보면 성적의 개수가 2개일 것이고" +
            "다시 2번학생을 조회했을 때 null이 반환되어야 한다.")
    void deleteTest2() {
        // given
        int stuNum = 2;
        // when
        repository.deleteByStuNum(stuNum);
        List<Score> scoreList = repository.findAll();
        Score score = repository.findByStuNum(stuNum);
        // then
        assertEquals(2, scoreList.size());
        assertNull(score);
    }


    @Test
    @DisplayName("새로운 성적 정보를 save 를 통해 추가하면 " +
            "목록의 개수가 4개여야 한다.")
    void saveTest() {
        // given
        Score score = new Score();
        score.setName("언년이");
        score.setKor(100);
        score.setMath(50);
        score.setEng(0);

        // when
        boolean flag = repository.save(score);
        List<Score> scoreList = repository.findAll();

        // then
        assertEquals(4, scoreList.size());
        assertTrue(flag);
        assertEquals(4, scoreList.get(scoreList.size() - 1).getStuNum());
    }

}