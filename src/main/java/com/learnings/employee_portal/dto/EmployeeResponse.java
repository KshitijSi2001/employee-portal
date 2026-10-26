package com.learnings.employee_portal.dto;

import com.learnings.employee_portal.model.Status;
import lombok.Data;

@Data
public class EmployeeResponse {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Status status;
    private String departmentName;


}
