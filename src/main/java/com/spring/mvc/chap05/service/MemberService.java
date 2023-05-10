package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.spring.mvc.chap05.service.LoginResult.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원 가입 처리 서비스
    public boolean join(final SignUpRequestDTO dto) {

        // dto 를 entity 로 변환

        // 매퍼에게 회원정보 전달해서 저장 명령리
        dto.setPassword(encoder.encode(dto.getPassword()));

        return memberMapper.save(dto.toEntity());
    }

    // 중복 검사 서비스 처리
    public boolean checkSignUpValue(String type, String keyword) {
        return memberMapper.isDuplicate(type, keyword) != 0;
    }


    // 로그인 검증
    public LoginResult authenticate(LoginRequestDTO dto) {
        Member foundMember = memberMapper.findMember(dto.getAccount());

        if (foundMember == null) {
            log.info("{} - 회원가입 안했음ㅋ", dto.getAccount());
            return NO_ACC;
        }

        // 비밀번호 일치 확인

        if (!encoder.matches(dto.getPassword(), foundMember.getPassword())) {
            log.info("{} - 비밀번호 틀렸음", dto.getPassword());
            return NO_PW;
        }

        log.info("{} 님 로그인 성공!", foundMember.getName());
        return SUCCESS;
    }
}
