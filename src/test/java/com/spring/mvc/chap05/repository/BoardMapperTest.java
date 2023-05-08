package com.spring.mvc.chap05.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardMapperTest {

    @Autowired
    BoardMapper boardMapper;

//    @Test
//    @DisplayName("게시물 300개를 작성해야 한다.")
//    void test() {
//        // given
//        for (int i = 1; i <= 300; i++) {
//            boardMapper.save(
//                    Board.builder()
//                            .title(i + "번째 테스트")
//                            .content(i + "번째 내용")
//                            .build()
//            );
//        }
//        // when
//        // then
//    }

    @Test
    @DisplayName("d")
    void aa_test() {

    }


}