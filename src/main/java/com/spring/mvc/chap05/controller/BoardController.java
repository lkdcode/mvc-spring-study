package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardRequestDTO;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("boardList", boardService.findAll());

        return "chap05/list";
    }

    @GetMapping("/detail")
    public String detail(int boardNo, Model model) {
        model.addAttribute("board", boardService.findByBoardNo(boardNo));

        return "chap05/detail?boardNo" + boardNo;
    }

    @GetMapping("/write")
    public String write() {
        return "chap05/write";
    }

    @PostMapping("/write")
    public String write(BoardRequestDTO dto) {
        boardService.write(dto);
        return "redirect:/board/list";
    }

    @GetMapping("/delete")
    public String delete(int boardNo) {
        System.out.println("dddd");
        boardService.delete(boardNo);
        return "redirect:/board/list";
    }

}
