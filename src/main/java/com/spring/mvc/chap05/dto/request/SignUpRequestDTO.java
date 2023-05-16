package com.spring.mvc.chap05.dto.request;


import com.spring.mvc.chap05.entity.Member;
import lombok.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 2, max = 6, message = "이름은 2~6 글자 여야 합니다")
    @NotBlank(message = "이름을 입력하세요")
    @Email
    private String name;

    private MultipartFile profileImage;

    public Member toEntity() {
        return Member.builder()
                .account(this.account)
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .build();
    }
}
