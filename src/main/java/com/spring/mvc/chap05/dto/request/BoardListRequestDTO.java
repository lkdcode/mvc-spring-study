package com.spring.mvc.chap05.dto.request;

import com.spring.mvc.chap05.entity.Board;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardListRequestDTO {
    private final int TITLE_SIZE = 7;
    private final int CONTENT_SIZE = 30;
    private final String TIME_FORMAT = "yyyy-MM-dd HH:mm";

    private int boardNo;
    private String shortTitle;
    private String shortContent;
    private String date;
    private int viewCount;

    public BoardListRequestDTO(Board board) {
        this.boardNo = board.getBoardNo();
        this.shortTitle = changeString(board.getTitle(), TITLE_SIZE);
        this.shortContent = changeString(board.getContent(), CONTENT_SIZE);
        this.date = board.getRegDateTime().format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    private String changeString(String text, int size) {
        return text.length() > size ? text.substring(0, size) + ".".repeat(3) : text;
    }
}
