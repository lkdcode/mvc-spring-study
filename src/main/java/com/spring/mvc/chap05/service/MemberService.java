package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.dto.response.LoginUserResponseDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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

    public void maintainLoginState(HttpSession session, String account) {

        // 로그인이 성공하면 세션에 로그인한 회원의 정보들을 저장
        /**
         *  로그인시 클라이언트에게 전달할 회원 정보
         *  - 닉네임
         *  - 프로필 사진
         *  - 마지막 로그인 시간
         */

        // 현재 로그인한 사람의 모든 정보
        Member member = getMember(account);

        // 화면에 보여줄 일부 정보
        LoginUserResponseDTO dto = LoginUserResponseDTO.builder()
                .account(member.getAccount())
                .nickName(member.getName())
                .email(member.getEmail())
                .build();

        // 그 정보를 세션에 저장
        session.setAttribute("login", dto);
        log.info("{}",dto);


        // 세션의 수명을 설정 (기본값 30분)
        session.setMaxInactiveInterval(60 * 60); // 1 시간
    }

    // 멤버 정보를 가져오는 서비스기능
    public Member getMember(String account) {
        return memberMapper.findMember(account);
    }
}
