package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원 가입 처리 서비스
    public void join(SignUpRequestDTO dto) {

        // dto 를 entity 로 변환

        // 매퍼에게 회원정보 전달해서 저장 명령리
        dto.setPassword(encoder.encode(dto.getPassword()));
        boolean isJoin = memberMapper.save(dto.toEntity());
    }


}
