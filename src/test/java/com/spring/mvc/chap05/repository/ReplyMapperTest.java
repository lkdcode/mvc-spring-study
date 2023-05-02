package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyMapperTest {
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    ReplyMapper replyMapper;

    @Test
    @DisplayName("게시물 300개를 등록하고 각 게시물에 랜덤으로 1000개의 댓글을 나눠서 등록해야 한다.")
    @Transactional
    @Rollback
    void bulk_insert_test() {
        // given
        for (int i = 1; i <= 300; i++) {
            boardMapper.save(
                    Board.builder()
                            .title(i + "번 제목")
                            .content(i + "번 게시물 내용")
                            .build()
            );
        }

        // when
        // then
        assertEquals(300, boardMapper.count(new Search()));

        for (int i = 1; i <= 1000; i++) {
            replyMapper.save(
                    Reply.builder()
                            .replyWriter(i + "번 유저")
                            .replyText(i + "번 내용")
                            .boardNo((long) (Math.random() * 300 + 1))
                            .build()
            );
        }
    }

    @Test
    @DisplayName("댓글을 3번 게시물에 등록하면 3번 게시물의 총 댓글 수는 4개여야 한다")
    @Transactional
    @Rollback
        // 테스트 끝난 이 후 롤백해라
    void reply_save_test() {
        // given
        long boardNo = 3L;
        Reply testReply = Reply.builder()
                .replyWriter("테스트 유저5")
                .replyText("테스트 댓글5")
                .boardNo(boardNo)
                .build();

        // when
        boolean flag = replyMapper.save(testReply);

        // then
        assertTrue(flag);
        assertEquals(4, replyMapper.count(boardNo));
    }

    @Test
    @DisplayName("댓글 번호가 457번인 댓글을 삭제하면 3번 게시물의 총 댓글 수가 1이어야 한다.")
    @Transactional
    @Rollback
    void reply_delete_test() {
        // given
        long replyNo = 457;
        long boardNo = 3L;

        // when
        boolean flag = replyMapper.deleteOne(replyNo);

        // then
        assertTrue(flag);
        assertEquals(0, replyMapper.count(boardNo));
    }

    @Test
    @DisplayName("21번 게시물의 댓글을 수정하면 댓글 내용이 '수정내용'으로 바뀔 것이다")
    @Transactional
    @Rollback
    void reply_modify_test() {
        // given
        long replyNo = 222;
        Reply modifyReply = Reply.builder()
                .replyNo(replyNo)
                .replyText("수정내용")
                .build();

        // when
        boolean flag = replyMapper.modify(modifyReply);

        // then
        assertTrue(flag);
        assertEquals("수정내용", replyMapper.findOne(replyNo).getReplyText());
    }

    @Test
    @DisplayName("362번 댓글을 조회하면 작성자는 '362번 유저' 내용은 '362번 내용'일 것이다.")
    @Transactional
    @Rollback
    void reply_findOne_test() {
        // given
        long replyNo = 362L;

        // when
        Reply findOneReply = replyMapper.findOne(replyNo);

        // then
        assertEquals(findOneReply.getReplyText(), "362번 내용");
        assertEquals(findOneReply.getReplyWriter(), "362번 유저");
    }


    @Test
    @DisplayName("23번 게시글을 조회하면 댓글 수는 4개가 나올 것이다.")
    @Transactional
    @Rollback
    void reply_findAll_test() {
        // given
        long boardNo = 23L;

        // when
        List<Reply> replyList = replyMapper.findAll(boardNo, new Page());

        // then
        assertEquals(4, replyList.size());
        assertEquals("572번 유저", replyList.get(2).getReplyWriter());
        assertEquals("629번 내용", replyList.get(3).getReplyText());
    }

    @Test
    @DisplayName("20번 게시물의 댓글 수를 조회하면 10개가 나올 것이다.")
    @Transactional
    @Rollback
    void count_test() {
        // given
        long boardNo = 20L;

        // when
        int count = replyMapper.count(boardNo);

        // then
        assertEquals(10, count);
    }

}