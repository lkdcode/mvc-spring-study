package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreMapperTest {

    @Autowired
    ScoreMapper mapper;

    @Test
    @DisplayName("마이바티스를 이용해서 데이터 추가에 성공할 것이다.")
    void insert_test() {
        // given
        Score score = new Score(
                ScoreRequestDTO.builder()
                        .name("김마바")
                        .eng(30)
                        .kor(58)
                        .math(19)
                        .build()
        );
        // when

        boolean isSave = mapper.save(score);
        // then
        assertTrue(isSave);
    }

    @Test
    @DisplayName("마이바티스를 이용해서 데이터 삭제에 성공할 것이다.")
    void delete_by_stu_num_test() {
        // given
        int stuNum = 7;

        // when
        boolean isDelete = mapper.deleteByStuNum(stuNum);

        // then
        assertTrue(isDelete);
    }

    @Test
    @DisplayName("마이바티스를 이용해서 데이터 전체 조회에 성공할 것이다.")
    void find_all_test() {
        // given
        // when
        List<Score> list = mapper.findAll("num");

        list.forEach(System.out::println);

        // then
        assertEquals(list.size(), 4);
    }

    @Test
    @DisplayName("마이바티스를 이용해서 학번을 기준으로 학생 조회에 성공할 것이다.")
    void find_one_by_id_test() {
        // given
        int stuNum = 6;

        // when
        Score score = mapper.findByStuNum(stuNum);

        System.out.println("score = " + score);
        // then

        assertEquals(score.getName(), "강호동");
        assertEquals(score.getMath(), 77);
        assertEquals(score.getEng(), 99);
        assertEquals(score.getKor(), 18);
    }

}