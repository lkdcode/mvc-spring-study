package com.spring.mvc.chap05.controller.api;

import com.spring.mvc.chap05.dto.ReplyListResponseDTO;
import com.spring.mvc.chap05.dto.ReplyPostRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
@Slf4j
public class ReplyController {
    private final ReplyService replyService;

    // 댓글 목록 조회 요청
    // URL : /api/v1/replies/3/page/1
    //                3번/게시물 댓글목록/1페이지
    @GetMapping("/{boardNo}/pages/{pageNo}")
    public ResponseEntity<?> list(
            @PathVariable long boardNo,
            @PathVariable int pageNo
    ) {
        log.info("/api/v1/replies/{}/page/{} : GET !!", boardNo, pageNo);
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setAmount(10);

        ReplyListResponseDTO list = replyService.getList(boardNo, page);

        return ResponseEntity.ok().body(list);
    }

    // 댓글 등록 요청

    @PostMapping
//    @ResponseBody 내가 서버에서 만든 데이터를 JSON 에 담아서 보내준다
    public ResponseEntity<?> create(
            // @RequestBody 요청 메시지 바디에 JSON 으로 담아서 보내주세요
            // @Validated 요청 값 검증
            @Validated @RequestBody ReplyPostRequestDTO dto,
            BindingResult result // 검증 결과를 가진 객체
    ) {
        // 입력값 검증에 걸리면 4xx 상태 코드 리턴

        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(result.toString());
        }


        log.info("/api/v1/replies : POST! ");
        log.info("param ?: {} ", dto);

        // 서비스에 비즈니스 로직 처리 위임
        try {
            ReplyListResponseDTO responseDTO = replyService.register(dto);

            // 성공시 클라이언트에 응답하기
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            // 문제 발생 상황을 클라이언트에게 전달
            log.warn("500 Status code response!! caused by: {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }


    // 댓글 삭제 요청

    @DeleteMapping("/{replyNo}")
    public ResponseEntity<?> remove(
            // 이름이 다를경우 괄호안에 이름을 적어준다
            // /로 받기 때문에 @PathVariable
            // null 체크를 위해 랩퍼로
            @PathVariable(required = false) Long replyNo
    ) {

        if (replyNo == null) return ResponseEntity.badRequest().body("댓글 번호를 보내주세요!");

        log.info("/api/v1/replies/{} DELETE !", replyNo);

        try {
            ReplyListResponseDTO responseDTO = replyService.delete(replyNo);

            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }


}
