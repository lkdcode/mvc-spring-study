package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {
    // TODO : before 셋팅 하는 방법, 하나의 케이스는 통과하지만, 전부 돌렸을 때 안 됨

    @Autowired
    BoardService service;

    @Test
    @DisplayName("전체 조회를 하면 모든 게시글을 리턴하므로 사이즈가 3(초기설정)이어야 한다.")
    void listTest() {
        // given

        // when
        List<Board> boardList = service.findAll();
        // then
        assertEquals(3, boardList.size());
    }

    @Test
    @DisplayName("게시글 번호로 조회하면 해당 게시글의 정보를 리턴한다.")
    void findByBoardNoTest() {
        // given
        int boardNo = 1;
        // when
        Board board = service.findByBoardNo(boardNo);
        // then
        assertEquals("1번 게시글", board.getTitle());
    }

    @Test
    @DisplayName("게시글을 저장하게되면 레파지토리의 사이즈는 4가 될 것이다." +
            "해당 게시글의 번호로 조회했을 때 제목도 같을 것이다.")
    void saveTest() {
        // given
        Board board = new Board(4, "4번 게시글", "어쩌고고고고고", 0, LocalDateTime.now());

        // when
        boolean isSave = service.save(board);

        // then
        assertTrue(isSave);
        assertEquals(4, service.findAll().size());
        assertEquals("어쩌고고고고고", service.findByBoardNo(board.getBoardNo()).getContent());
    }

    @Test
    @DisplayName("게시글의 번호로 해당 글을 삭제하면 삭제가 되어 사이즈는 2가 될 것이다." +
            "삭제 후 해당 게시글의 번호로 조회를 하면 Null 을 리턴할 것이다.")
    void deleteTest() {
        // given
        int boardNo = 1;

        // when
        boolean isDelete = service.delete(boardNo);

        // then
        assertTrue(isDelete);
        assertEquals(2, service.findAll().size());
        assertNull(service.findByBoardNo(boardNo));
    }

}