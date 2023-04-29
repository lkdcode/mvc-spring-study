package com.spring.mvc.etc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeTest {

    @Test
    @DisplayName("Builder Test")
    void builder_test() {
        // given

        // when
        Employee e = Employee.builder()
                .position("대리")
                .empName("홍길동")
                .build();

        // then
        Actor actor = Actor.builder()
                .actorName("홍길동")
                .hasPhone(false)
                .actorAge(40)
                .build();
        System.out.println("actor = " + actor);
    }

}