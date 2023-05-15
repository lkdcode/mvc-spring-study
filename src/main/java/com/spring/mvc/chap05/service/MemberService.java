package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.AutoLoginDTO;
import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.dto.response.LoginUserResponseDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.spring.mvc.chap05.service.LoginResult.*;
import static com.spring.mvc.util.LoginUtil.*;

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
    public LoginResult authenticate(LoginRequestDTO dto
            , HttpSession session
            , HttpServletResponse response) {
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

        // 자동 로그인 체크 여부 확인
        if (dto.isAutoLogin()) {
            // 1. 쿠키 생성
            // - 쿠키 값에 세션 아이디를 저장한다.
            // - key : value = value 는 session ID
            Cookie autoLoginCookie = new Cookie(AUTO_LOGIN_COOKIE, session.getId());


            // 2. 쿠키 셋팅 - 수명이랑 사용 경로
            int limitTime = 60 * 60 * 24 * 90;
            autoLoginCookie.setMaxAge(limitTime);
            autoLoginCookie.setPath("/"); // 전체 경로에서 사용할 것이다.

            // 3. 쿠키를 클라이언트에 응답 전송
            response.addCookie(autoLoginCookie);

            // 4. DB 에도 쿠키에 저장된 값과 수명을 저장해야 한다
            memberMapper.saveAutoLogin(
                    AutoLoginDTO.builder()
                            .account(dto.getAccount())
                            // 년월일 시분초로 저장해야함.
                            // 지금 시간 + 90일
                            .limitTime(LocalDateTime.now().plusDays(90))
                            .sessionId(session.getId())
                            .build()
            );

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
                .auth(member.getAuth().toString())
                .build();

        // 그 정보를 세션에 저장
        session.setAttribute(LOGIN_KEY, dto);
        log.info("{}", dto);


        // 세션의 수명을 설정 (기본값 30분)
        session.setMaxInactiveInterval(60 * 60); // 1 시간
    }

    // 멤버 정보를 가져오는 서비스기능
    public Member getMember(String account) {
        return memberMapper.findMember(account);
    }

    public void autoLoginClear(HttpServletRequest request, HttpServletResponse response) {
        // 자동 로그인 해제하는 방법
        // 1. 자동 로그인 쿠키를 가져온다
        Cookie c = WebUtils.getCookie(request, AUTO_LOGIN_COOKIE);

        // 2.
        // -> 쿠키의 수명을 0초로 만들어서 다시 클라이언트에게 응답
        if (c != null) {
            c.setMaxAge(0);
            response.addCookie(c);
        }

        // 3. 데이터 베이스에서도 자동로그인을 해제한다.
        memberMapper.saveAutoLogin(
                AutoLoginDTO.builder()
                        .sessionId("none")
                        .limitTime(LocalDateTime.now())
                        .account(LoginUtil.getCurrentLoginMemberAccount(request.getSession()))
                        .build()
        );

    }
}
