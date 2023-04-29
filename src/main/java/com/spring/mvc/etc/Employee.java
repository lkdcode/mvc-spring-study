package com.spring.mvc.etc;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private int empId; // 사번
    private String empName; // 사원명
    private String position; // 직급
    private LocalDate hireDate; // 입사 일자

    // 빌더 패턴 구현
    // 1. 내부 클래스를 외부와 같은 스펙으로 만듬
    public static class Builder {

        private int empId; // 사번
        private String empName; // 사원명
        private String position; // 직급
        private LocalDate hireDate; // 입사 일자

        // 2. 내부 클래스의 생성자를 private으로 제한해서
        // Employee 클래스 밖에서 생성 불가능하게 함.
        public Builder empId(int empId) {
            this.empId = empId;
            return this;
        }

        public Builder empName(String empName) {
            this.empName = empName;
            return this;
        }

        public Builder position(String position) {
            this.position = position;
            return this;
        }

        public Builder hireDate(LocalDate localDate) {
            this.hireDate = localDate;
            return this;
        }

        public Employee build() {
            return new Employee(this.empId, this.empName, this.position, this.hireDate);
        }

    }

    // 5. 빌더 호출 메서드
    public static Builder builder() {
        return new Builder();
    }

}