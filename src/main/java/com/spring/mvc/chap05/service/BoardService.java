package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardListRequestDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardRepository;

    // 중간 처리 기능 자유롭게 사용

    public List<BoardListRequestDTO> findAll(Page page) {
        return boardRepository.findAll(page).stream()
                .sorted(comparing(Board::getBoardNo).reversed())
                .map(BoardListRequestDTO::new)
                .collect(toList());
    }

    public BoardWriteRequestDTO findByBoardNo(int boardNo) {
        return new BoardWriteRequestDTO(boardRepository.findByBoardNo(boardNo));
    }

    public boolean save(Board board) {
        return boardRepository.save(board);
    }

    public boolean delete(int boardNo) {
        return boardRepository.delete(boardNo);
    }

    public boolean write(BoardWriteRequestDTO dto) {
        return boardRepository.save(new Board(dto));
    }
}
