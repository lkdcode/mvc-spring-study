package com.spring.mvc.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    PersonRepository repository;

    @Test
    @DisplayName("사람의 이름과 나이 정보를 DB person table에 잘 삽입해야 한다.")
    void save_test() {

        // given
        Person person = new Person();
        person.setPersonName("라이언");
        person.setPersonAge(87);

        // when
        repository.save(person);

        // then
    }

    @Test
    @DisplayName("사람의 이름과 나이 정보를 DB person table 에 잘 수정해야 한다.")
    void update_test() {
        // given
        Person person = new Person();
        person.setPersonName("수정수정");
        person.setPersonAge(99);
        person.setId(1L);

        // when
        repository.update(person);


        // then
    }

    @Test
    @DisplayName("사람의 이름과 나이 정보를 DB person table 에 잘 삭제해야 한다.")
    void remove_test() {
        // given
        long id = 2L;

        // when
        repository.remove(id);


        // then
    }


    @Test
    @DisplayName("전체 조회를 한다.")
    void find_all_test() {
        // given
        repository.findAll().forEach(System.out::println);

        // when
        // then
    }


    @Test
    @DisplayName("한 명을 조회한다.")
    void find_one_test() {
        // given
        Person p = repository.findOne(3L);
        System.out.println("p = " + p);
        // when
        // then
    }

}