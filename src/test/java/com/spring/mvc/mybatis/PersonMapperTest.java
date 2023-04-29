package com.spring.mvc.mybatis;

import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonMapperTest {

    @Autowired
    PersonMapper mapper;

    @Test
    @DisplayName("마이바티스 메퍼로 사람 정보 저장에 성공해야 한다.")
    void save_test() {
        // given
        Person person = Person.builder()
                .personAge(49)
                .personName("김마바")
                .build();

        // when
        boolean isSave = mapper.save(person);

        // then
        assertTrue(isSave);
    }


    @Test
    @DisplayName("마이바티스 메퍼로 사람 정보 수정에 성공해야 한다.")
    void update_test() {
        // given
        Person person = Person.builder()
                .personName("마바수정")
                .personAge(88)
                .id(10L)
                .build();

        // when
        boolean isUpdate = mapper.change(person);

        // then
        assertTrue(isUpdate);
    }

    @Test
    @DisplayName("마이바티스 메퍼로 사람 정보 삭제에 성공해야 한다.")
    void remove_test() {
        // given
        long id = 11L;

        // when
        boolean isRemove = mapper.remove(id);

        // then
        assertTrue(isRemove);
    }

    @Test
    @DisplayName("마이바티스 메퍼로 사람 정보 전체 조회에 성공해야 한다.")
    void find_all_test() {
        // given
        // when
        List<Person> list = mapper.findAll();
        list.forEach(System.out::println);
        // then
        assertEquals(list.size(), 8);
    }

    @Test
    @DisplayName("마이바티스 메퍼로 한 사람의 정보 조회에 성공해야 한다.")
    void find_one_test() {
        // given
        long id = 7L;

        // when
        Person person = mapper.findOne(id);

        // then
        System.out.println("person = " + person);
        assertEquals(person.getPersonName(), "스프링");
        assertEquals(person.getPersonAge(), 2);
        assertNotNull(person);
    }
}