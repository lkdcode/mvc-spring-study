package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 중간 처리 기능 자유롭게 사용

    public List<Board> findAll() {
        return boardRepository.findAll().stream()
                .sorted(Comparator.comparing(Board::getRegDateTime).reversed())
                .collect(Collectors.toList());
    }

    public Board findByBoardNo(int boardNo) {
        return boardRepository.findByBoardNo(boardNo);
    }

    public boolean save(Board board) {
        return boardRepository.save(board);
    }

    public boolean delete(int boardNo) {
        return boardRepository.delete(boardNo);
    }

    public boolean write(BoardRequestDTO dto) {
        return boardRepository.save(new Board(dto));
    }
}
