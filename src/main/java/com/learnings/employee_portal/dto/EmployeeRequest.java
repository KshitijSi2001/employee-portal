package com.learnings.employee_portal.dto;

import lombok.Data;

@Data
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private int age;
    private int salary;

}
