package com.learnings.employee_portal.repository;

import com.learnings.employee_portal.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findByDepartmentName(String departmentName);
}
