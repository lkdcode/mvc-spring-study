package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import org.springframework.stereotype.Repository;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    private final static Map<Integer, Board> boardMap;
    private static int sequence;

    static {
        boardMap = new HashMap<>();

        Board board1 = new Board(++sequence, "1번 게시글", "어쩌고저쩌고");
        Board board2 = new Board(++sequence, "2번 게시글", "저쩌고어쩌고");
        Board board3 = new Board(++sequence, "3번 게시글", "어쩌러쩌러");

        boardMap.put(board1.getBoardNo(), board1);
        boardMap.put(board2.getBoardNo(), board2);
        boardMap.put(board3.getBoardNo(), board3);
    }


    @Override
    public List<Board> findAll() {
        return new ArrayList<>(boardMap.values());
    }

    @Override
    public Board findByBoardNo(int boardNo) {
        return boardMap.get(boardNo);
    }

    @Override
    public boolean save(Board board) {
        board.setBoardNo(++sequence);
        boardMap.put(board.getBoardNo(), board);
        return true;
    }

    @Override
    public boolean delete(int boardNo) {
        boardMap.remove(boardNo);
        return true;
    }
}
