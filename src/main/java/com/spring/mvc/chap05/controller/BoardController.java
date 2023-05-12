package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.page.PageMaker;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.service.BoardService;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(
            Search page
            , Model model
            , HttpServletRequest request // 요청 정보
    ) {
        boolean flag = false;

        // 세션을 확인
        Object login = request.getSession().getAttribute("login");

        if (login != null) {
            flag = true;
        }

        // 쿠키 확인
//        Cookie[] cookies = request.getCookies();
//        for (Cookie c : cookies) {
//            if (c.getName().equals("login")) {
//                flag = true;
//                break;
//            }
//        }

        if (!flag) {
            return "redirect:/members/sign-in";
        }


        log.info("/board/list : GET");
        log.info("page : {}", page);


        // 페이징 알고리즘 작동
        PageMaker maker = new PageMaker(page, boardService.getCount(page));

        model.addAttribute("boardList", boardService.findAll(page));
        model.addAttribute("maker", maker);
        model.addAttribute("s", page);

        return "chap05/list";
    }

    @GetMapping("/detail")
    public String detail(int boardNo, Model model, @ModelAttribute("s") Search search) {
//        model.addAttribute("board", boardService.findByBoardNo(boardNo));
        model.addAttribute("board", boardService.getDetail(boardNo));

        return "chap05/detail";
    }

    @GetMapping("/write")
    public String write(HttpSession session) {

        if (!LoginUtil.isLogin(session)) {
            return "redirect:/members/sign-in";
        }

        return "chap05/write";
    }

    @PostMapping("/write")
    public String write(BoardWriteRequestDTO dto, HttpSession session) {
        boardService.write(dto, session);
        return "redirect:/board/list";
    }

    @GetMapping("/delete")
    public String delete(int boardNo) {
        boardService.delete(boardNo);

        return "redirect:/board/list";
    }

}
