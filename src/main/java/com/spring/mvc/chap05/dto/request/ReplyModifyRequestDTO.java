package com.spring.mvc.chap05.dto.request;


import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyModifyRequestDTO {

    @NotNull(message = "원본 글 번호 입력하세요")
    @PositiveOrZero(message = "원본 글 번호는 0이상의 양수만 가능")
    private Long bno;

    @NotNull(message = "댓글 번호 입력하세요")
    @PositiveOrZero(message = "댓글 번호는 0이상의 양수만 가능")
    private Long rno;

    @NotBlank(message = "수정할 내용 입력하세요")
    private String text;
    // 원본 글 번호
    // 댓글 번호
    // 수정 텍스트

    public Reply toEntity() {
        return Reply.builder()
                .replyText(this.text)
                .boardNo(this.bno)
                .replyNo(this.rno)
                .replyDate(LocalDateTime.now())
                .build();
    }
}
