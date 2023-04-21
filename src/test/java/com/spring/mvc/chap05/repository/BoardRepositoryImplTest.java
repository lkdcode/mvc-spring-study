package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardRepositoryImplTest {

    BoardRepository repository = new BoardRepositoryImpl();

    @Test
    @DisplayName("전체 조회를 하면 모든 게시글을 리턴하므로 사이즈가 3(초기설정)이어야 한다.")
    void findAllTest() {
        // given
        // when
        List<Board> testList = repository.findAll();

        // then
        assertEquals(3, testList.size());
    }

    @Test
    @DisplayName("게시글 번호로 조회하면 해당 게시글의 정보를 리턴한다.")
    void findByBoardNoTest() {
        // given
        int boardNum = 2;

        // when
        Board board = repository.findByBoardNo(boardNum);

        // then
        assertEquals("2번 게시글", board.getTitle());
    }

    @Test
    @DisplayName("게시글을 저장하게되면 레파지토리의 사이즈는 4가 될 것이다." +
            "해당 게시글의 번호로 조회했을 때 제목도 같을 것이다.")
    void saveTest() {
        // given
        Board board = new Board(4, "4번 게시글", "어쩌고고고고고", 0, LocalDateTime.now());

        // when
        repository.save(board);

        // then
        assertEquals(4, repository.findAll().size());
        assertEquals("4번 게시글", repository.findByBoardNo(board.getBoardNo()).getTitle());

    }

    @Test
    @DisplayName("게시글의 번호로 해당 글을 삭제하면 삭제가 되어 사이즈는 2가 될 것이다." +
            "삭제 후 해당 게시글의 번호로 조회를 하면 Null 을 리턴할 것이다.")
    void deleteByNoTest() {
        // given
        int boardNo = 1;

        // when
        repository.delete(boardNo);

        // then
        assertEquals(2, repository.findAll().size());
        assertNull(repository.findByBoardNo(boardNo));
    }


}