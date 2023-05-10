package com.spring.mvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Member {
    private String account;
    private String password;
    private String name;
    private String email;
    private Auth auth;
    private LocalDateTime rageDate;
}