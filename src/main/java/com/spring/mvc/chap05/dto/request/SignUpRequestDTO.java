package com.spring.mvc.chap05.dto.request;


import com.spring.mvc.chap05.entity.Member;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Validated
public class SignUpRequestDTO {

    @NotBlank(message = "아이디를 입력하세요")
    private String account;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    @NotBlank(message = "이메일을 입력하세요")
    private String email;

    @NotBlank(message = "이을 입력하세요")
    private String name;

    public Member toEntity() {
        return Member.builder()
                .account(this.account)
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .build();
    }
}
