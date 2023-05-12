package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.request.BoardListRequestDTO;
import com.spring.mvc.chap05.dto.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardRepository;

    // 중간 처리 기능 자유롭게 사용

    public List<BoardListRequestDTO> findAll(Search page) {
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

    public boolean write(BoardWriteRequestDTO dto, HttpSession session) {
        Board board = new Board(dto);
        board.setAccount(LoginUtil.getCurrentLoginMemberAccount(session));

        return boardRepository.save(board);
    }

    public int getCount(Search search) {
        return boardRepository.count(search);
    }

    public BoardDetailResponseDTO getDetail(int bno) {

        Board board = boardRepository.findByBoardNo(bno);
        // 조회수 상승 처리
//        board.setViewCount(board.getViewCount() + 1);
//        boardRepository.upViewCount(bno);

        return new BoardDetailResponseDTO(board);
    }


}
