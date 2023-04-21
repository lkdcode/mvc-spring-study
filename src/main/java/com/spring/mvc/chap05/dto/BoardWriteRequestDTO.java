package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Board;
import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardWriteRequestDTO {
    private String title;
    private String content;

    public BoardWriteRequestDTO(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
