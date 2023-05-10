package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        dto.setAccount("ididid");
        dto.setPassword("pwpwpw");
        dto.setName("name");
        dto.setEmail("ee@mai.l");
        // when
        memberService.join(dto);


        // then


    }


}