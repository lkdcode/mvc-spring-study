package com.spring.mvc.chap05.dto.page;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PageMakerTest {

    @Test
    @DisplayName(" ")
    void pageTest() {
        // 클라이언트의 페이지 정보
        Page page = new Page();
        page.setPageNo(27);
        page.setAmount(10);

        PageMaker pageMaker = new PageMaker(page, 284);
        System.out.println("pageMaker = " + pageMaker);
    }

}