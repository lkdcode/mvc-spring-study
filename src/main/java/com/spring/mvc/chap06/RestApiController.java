package com.spring.mvc.chap06;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.springframework.http.ResponseEntity.*;

//@Controller
//@ResponseBody
@RestController // @Controller + @ResponseBody
@RequestMapping("/rests")
public class RestApiController {

    @GetMapping("/hello")
    @ResponseBody
    public ResponseEntity<?> hello() {
        return ok().body("안녕하세요");
    }

    @GetMapping("/foods")
    @ResponseBody
    public ResponseEntity<?> foods() {
        String[] foodList = {"탕수육", "족발", "마라탕"};
        return ok().body(foodList);
    }

    @GetMapping("/members")
    @ResponseBody
    public ResponseEntity<?> members() {
        List<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(124);
        test.add(12);
        test.add(126);
        test.add(127);
        test.add(128);
        return ok().body(test);
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<?> users() {
        Queue<String> queue = new LinkedList<>();
        queue.add("tset");
        queue.add("tset124");
        queue.add("ts15et124");
        queue.add("tset123524");
        queue.add("ts26et124");

        return ok().body(queue);
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<?> list() {
        List<User> users = new ArrayList<>();
        users.add(new User("twetwet", 25));
        users.add(new User("tweasfdtwet", 23525));
        users.add(new User("twetwadfet", 255));
        users.add(new User("twetweadsft", 2365));
        users.add(new User("twadfetwet", 2735));

        return ok().body(users);
    }

    @GetMapping("/bmi")
    public ResponseEntity<?> bmi(
            @RequestParam(required = false) Double cm,
            @RequestParam(required = false) Double kg) {
        if (cm == null || kg == null) {
//            return ResponseEntity.badRequest().build();
            return badRequest().body("키 또는 몸무게가 없음");
        }

        double bmi = kg / (cm / 100) * (cm / 100);
        HttpHeaders headers = new HttpHeaders();
        headers.add("fruits", "melon");
        headers.add("hobby", "baseball");

        return ok()
                .headers(headers)
                .body(bmi);
    }

    @Getter
    @Builder
    public class User {
        private String name;

        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

    }


}