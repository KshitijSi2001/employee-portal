package com.learnings.employee_portal.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class EmployeeUpdateRequest {

    private String firstName;

    private String lastName;

    @Min(value = 20, message = "Minimum required age is 20")
    private Integer age;

    @PositiveOrZero
    private Long salary;
}
