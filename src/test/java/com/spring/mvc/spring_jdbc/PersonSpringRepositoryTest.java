package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonSpringRepositoryTest {

    @Autowired
    PersonSpringRepository repository;

    @Test
    @DisplayName("디비에 추가될 것이다.")
    void save_person_test() {
        // given
        Person p = new Person();
        p.setPersonName("스프링");
        p.setPersonAge(2);

        // when
        repository.savePerson(p);

        // then

    }

    @Test
    @DisplayName("디비에 삭제될 것이다.")
    void remove_person_test() {
        // given
        long id = 3L;
        // when
        repository.removePerson(id);

        // then
    }


    @Test
    @DisplayName("디비에 저장된 회원의 이름과 나이가 바뀔 것이다.")
    void update_person_name_test() {
        // given
        long id = 6L;
        String changeName = "언이라";
        int changeAge = 9;
        // when
        boolean result = repository.updatePerson(id, changeName, changeAge);

        // then
        assertThat(result).isEqualTo(true);
        assertTrue(result);
    }

    @Test
    @DisplayName("디비에 저장된 모든 회원들의 정보가 조회될 것이다.")
    void find_all_test() {
        // given
        // when
        List<Person> list = repository.findAllPerson();

        // then
        assertEquals(list.size(), 5);
    }

    @Test
    @DisplayName("디비에 저장된 회원들 중 아이디로 한 회원을 조회할 것이다.")
    void find_one_test() {
        // given
        long id = 5L;

        // when
        Person person = repository.findOne(id);

        // then
        assertEquals(person.getPersonName(), "춘식");
        assertEquals(person.getPersonAge(), 51);
    }
}