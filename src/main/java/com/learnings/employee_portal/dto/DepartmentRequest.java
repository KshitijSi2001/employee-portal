package com.learnings.employee_portal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentRequest {

    @NotBlank
    private String departmentName;

    @NotBlank
    private String departmentLocation;
}
