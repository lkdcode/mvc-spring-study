package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 게시물 목록 조회 기능
//    List<Board> findAll();
    List<Board> findAll(Search page);

    // 게시물 상세 조회
    Board findByBoardNo(int boardNo);

    // 게시물 등록
    boolean save(Board board);

    // 게시물 삭제
    boolean delete(int boardNo);

    // 총 게시물 수 조회하기
    int count(Search search);
}