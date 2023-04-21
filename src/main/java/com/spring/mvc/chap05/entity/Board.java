package com.spring.mvc.chap05.entity;

import com.spring.mvc.chap05.dto.BoardRequestDTO;
import com.spring.mvc.chap05.service.BoardService;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private int boardNo; // 게시글 번호
    private String title; // 제목
    private String content; // 내용
    private int viewCount; // 조회수
    private LocalDateTime regDateTime; // 작성 일자 시간

    public Board(BoardRequestDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.regDateTime = LocalDateTime.now();
    }

    public String getTime() {
        return this.regDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}