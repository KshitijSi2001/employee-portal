package com.learnings.employee_portal.service;

import com.learnings.employee_portal.entity.Department;
import com.learnings.employee_portal.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department findDepartmentById(int departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found!!"));
    }

    public Department postDepartment(Department department) {

        return departmentRepository.save(department);
       // return ResponseEntity.status(HttpStatus.OK).body(savedDepartment);

    }
}
