package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    PasswordEncoder encoder;

    @Test
    @DisplayName("회원가입에 성공해야 한다.")
    void saveTest() {
        // given
        Member member = Member.builder()
                .account("lion")
                .password(encoder.encode("aaa1234"))
                .name("사자")
                .email("lion@naver.com")
                .build();

        // when
        boolean save = memberMapper.save(member);

        // then
        assertTrue(save);
    }

    @Test
    @DisplayName("peach 라는 계정명으로 회원을 조회하면" +
            " 그 회원의 이름이 천도복숭아여야 한다.")
    @Transactional
    @Rollback
    void findMemberTest() {
        // given
        String account = "peach";

        // when
        Member member = memberMapper.findMember(account);

        // then
        assertThat(member.getAccount()).isEqualTo(account);
        assertThat(member.getName()).isEqualTo("천도복숭아");
    }

    @Test
    @DisplayName("계정명이 peach인 경우 중복임")
    void account_duplicate_Test() {
        // given
        String account = "peach";

        // when
        int duplicate = memberMapper.isDuplicate("account", account);

        // then
        assertThat(duplicate).isEqualTo(1);
    }

    @Test
    @DisplayName("계정명이 peach인 경우 중복임")
    void email_duplicate_Test() {
        // given
        String email = "peach@naver.com";

        // when
        int duplicate = memberMapper.isDuplicate("email", email);

        // then
        assertThat(duplicate).isEqualTo(1);
    }

    @Test
    @DisplayName("비밀번호가 암호화 되어야 한다.")
    void encodingTest() {
        // given
        // 인코딩 전 패스워드
        String rawPassword = "abc1234";

        // when
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("encodedPassword = " + encodedPassword);

        // then
        assertNotEquals(rawPassword, encodedPassword);
    }

    @Test
    @DisplayName("비밀번호 확인")
    void passwordTest() {
        // given
        String lionPassword = "aaa1234";
        String account = "banana";
        // when
        Member lion = memberMapper.findMember(account);

        boolean isLogin = encoder.matches(encoder.encode(lionPassword), lion.getPassword());

//        encoder.matches()
        // then
        assertTrue(isLogin);
    }


}