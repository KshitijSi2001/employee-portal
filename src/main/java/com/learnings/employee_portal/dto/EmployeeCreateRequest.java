package com.learnings.employee_portal.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class EmployeeCreateRequest {

    @NotBlank(message = "First Name shouldn't be blank!")
    private String firstName;


    private String lastName;

    @NotNull
    @Min(value = 20, message = "Minimum age is 20!")
    private Integer age;

    @NotNull
    @PositiveOrZero(message = "Salary can't be negative")
    private Long salary;
}


//@NotBlank for Strings and @NotNull for integers/numerals