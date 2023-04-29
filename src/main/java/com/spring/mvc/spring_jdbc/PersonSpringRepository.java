package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonSpringRepository {
    // 스프링 JDBC 활용 - 데이터 베이스 접속 설정 정보를
    // 설정 파일을 통해 불러와서 사용합니다.
    private final JdbcTemplate jdbcTemplate;


    // 저장 기능
    public void savePerson(Person person) {
        String sql = "INSERT INTO person (person_name, person_age) VALUES (?, ?)";
        jdbcTemplate.update(sql, person.getPersonName(), person.getPersonAge());
    }


    // 삭제 기능
    public void removePerson(long id) {
        String sql = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    // 수정 기능
    public boolean updatePerson(long id, String personName, int personAge) {
        String sql = "UPDATE person SET " +
                "person_name = ?, person_age = ? " +
                "WHERE id = ?";
        int result = jdbcTemplate.update(sql, personName, personAge, id);

        return result == 1;
    }


    // 전체 조회 기능
    public List<Person> findAllPerson() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, (rs, p) -> new Person(rs));
    }


    // 개별 조회
    public Person findOne(long id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, person) -> new Person(rs), id);
    }
}
