package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.service.LoginResult;
import com.spring.mvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.spring.mvc.chap05.service.LoginResult.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 회원 가입 요청
    // 회원 가입 양식 요청
    @GetMapping("/sign-up")
    public String signUp() {
        log.info("/members/sign-up GET - forwarding to jsp");


        return "members/sign-up";
    }

    // 회원 가입 처리 요청
    @PostMapping("/sign-up")
    public String signUp(SignUpRequestDTO dto) {
        log.info("/members/sign-up POST ! - {}", dto);

        boolean isJoin = memberService.join(dto);

        return "redirect:/board/list";
    }

    // 아이디, 이메일 중복 검사 REST
    @GetMapping("/check")
    // @ResponseBody : 이 메서드만 REST
    @ResponseBody
    public ResponseEntity<?> check(String type, String keyword) {
        log.info("/members/check?type={}&keyword={} ASYNC GET!", type, keyword);

        boolean flag = memberService.checkSignUpValue(type, keyword);

        return ResponseEntity.ok().body(flag);
    }

    // 로그인 양식 요청
    @GetMapping("/sign-in")
    public String signIn() {
        log.info("/members/sign-in GET - forwarding to jsp");

        return "members/sign-in";
    }

    // 로그린 검증 요청
    @PostMapping("/sign-in")
    public String signIn(LoginRequestDTO dto
                         // model은 리다이렉트에 담으면 사라짐.. 다음 요청에 없어짐
                         // 리다이렉션할 때 2번째 응답에 데이터를 보내기 위함
                         // Model model
            , RedirectAttributes ra
                         // 응답할 때 정보를 담아서 보낸다
            , HttpServletResponse response
            , HttpServletRequest request
    ) {
        log.info("/members/sign-in POST ! - {}", dto);

        LoginResult result = memberService.authenticate(dto);


        if (result == SUCCESS) {

            memberService.maintainLoginState(request.getSession(), dto.getAccount());

            // 서버에서 세션에 로그인 정보를 저장
//            HttpSession session = request.getSession();
//            session.setAttribute("login", "메롱");


//            // 쿠키 만들기
//            Cookie loginCookie = new Cookie("login", "name");
//
//            // 쿠키 셋팅
//            loginCookie.setPath("/");
//            loginCookie.setMaxAge(60 * 60 * 24);
//
//            // 쿠키를 응답시에 실어서 클라이언트에게 전송
//            response.addCookie(loginCookie);

//            ra.addAttribute("flag", true);
            return "redirect:/";
        }
        // 1회용으로 쓰고 버릴 데이터
        ra.addFlashAttribute("msg", result);

        return "redirect:/members/sign-in";
    }

}
