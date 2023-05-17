package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.spring.mvc.chap05.service.LoginResult.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {


    @Autowired
    private MemberService memberService;


    @Test
    @DisplayName("SignUpDTO 를 전달하면 회원가입에 성공해야 한다.")
    void joinTest() {
        // given
        SignUpRequestDTO dto = new SignUpRequestDTO();
        dto.setAccount("ididasdfid");
        dto.setPassword("pwasdfpwpw");
        dto.setName("naasdfme");
        dto.setEmail("easdfe@mai.l");
        // when
//        memberService.join(dto, savePath);


        // then
    }

    @Test
    @DisplayName("계정명이 abc1234인 회원의 로그인시도시 결과 검증을 상황별로 수행해야 한다.")
    void loginTest() {
        // given
//        LoginRequestDTO dto = new LoginRequestDTO();
//        dto.setAccount("abc1234");
//        dto.setPassword("1234!");
//
//        // when
//        LoginResult result = memberService.authenticate(dto);
//
//        // then
//        assertEquals(SUCCESS, result);
//
//        dto.setPassword("12345!");
//        result = memberService.authenticate(dto);
//        assertEquals(NO_PW, result);
//
//        dto.setAccount("abc12345");
//        result = memberService.authenticate(dto);
//        assertEquals(NO_ACC, result);


    }


}