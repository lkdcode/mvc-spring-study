package com.spring.mvc.chap05.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class SnsLoginService {

    // 카카오 로그인 처리
    public void kakaoService(Map<String, String> requestMap) {
        // 인가코드를 통해 토큰 발급받기
        getKakaoAccessToken(requestMap);
    }

    private void getKakaoAccessToken(Map<String, String> requestMap) {

        log.info("requestMap : {}", requestMap);

        // 요청 uri
        String requestUri = "https://kauth.kakao.com/oauth/token";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 요청 파라미터 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", requestMap.get("appKey"));
        params.add("redirect_uri", requestMap.get("redirect"));
        params.add("code", requestMap.get("code"));

        // 카카오 서버로 post 통신
        // server to server 때 사용
        RestTemplate template = new RestTemplate();

        HttpEntity<Object> requestEntity = new HttpEntity<>(params, headers);

        // 통신을 보내면서 응답 데이터를 리턴
        // param1: 요청 URL
        // param2: 요청 메서드
        // param3: 헤더와 요청 파라미터 정보 entity
        // param4: 응답 데이터를 받을 객체의 타입 (ex: dto, map)

        ResponseEntity<Map> responseEntity = template.exchange(requestUri, HttpMethod.POST, requestEntity, Map.class);

        // 응답데이터에서 필요한 정보를 가져오기
        Map<String, Object> responseData = (Map<String, Object>) responseEntity.getBody();
        log.info("토큰 요청 응답데이터 : {}", responseData);


    }

}