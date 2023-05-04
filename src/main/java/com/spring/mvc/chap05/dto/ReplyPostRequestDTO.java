package com.spring.mvc.chap05.dto;


import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
// RequestDTO 는 클라이언트가 제대로 값을 보냈는지 검증해야 함
public class ReplyPostRequestDTO {
    // 필드명은 클라이언트 개발자와 상의해야 함
    @NotBlank(message = "댓글 내용을 입력해주세요") // 필수 값
    private String text; // 댓글 내용
    @NotBlank(message = "댓글 작성자 명을 입력해주세요") // 필수 값
    @Size(min = 2, max = 8, message = "크기가 2이상 8이하여야 합니다.")
    private String author; // 댓글 작성자 명

    /**
     * @NotNull : Null 을 허용하지 않음
     * @NotBlank : Null + "" 을 허용하지 않음
     */
    @NotNull(message = "원본 글 번호를 입력해주세요") // 필수 값
    @PositiveOrZero(message = "0 이상의 양수만 허용합니다") //
    private Long bno; // 원본 글 번호

    // dto를 entity로 바꿔서 리턴하는 메서드
    public Reply toEntity() {
        return Reply.builder()
                .replyText(this.text)
                .replyWriter(this.author)
                .boardNo(this.bno)
                .build();
    }
}
