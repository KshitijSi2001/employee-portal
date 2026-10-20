package com.learnings.employee_portal.repository;

import com.learnings.employee_portal.entity.Employee;
import com.learnings.employee_portal.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findEmployeesByAge(int age);

    List<Employee> findEmployeesByDepartment_DepartmentId(int departmentId);

    List<Employee> findEmployeesByStatus(Status status);

    List<Employee> findEmployeesByDepartment_DepartmentName(String departmentName);
}
