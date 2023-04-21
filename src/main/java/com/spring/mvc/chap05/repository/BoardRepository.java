package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;

import java.util.List;

public interface BoardRepository {

    // 게시물 목록 조회 기능
    List<Board> findAll();

    // 게시물 상세 조회
    Board findByBoardNo(int boardNo);

    // 게시물 등록
    boolean save(Board board);

    // 게시물 삭제
    boolean delete(int boardNo);

    /**
     *  추가 기능...
     *  조회수 상승 기능
     *  수정 기능
     *  정렬 기능
     *  검색 기능
     */

}
