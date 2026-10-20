package com.learnings.employee_portal.controller;

import com.learnings.employee_portal.entity.Department;
import com.learnings.employee_portal.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public List<Department> findAllDepartments(){
        return departmentService.findAllDepartments();
    }

    @GetMapping("/departments/{departmentId}")
    public Department findDepartmentById(@PathVariable("departmentId") int departmentId){
        return departmentService.findDepartmentById(departmentId);
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> postDepartment(@RequestBody Department department){
        Department savedDepartment = departmentService.postDepartment(department);
        //return ResponseEntity.created(URI.create(String.valueOf(HttpStatus.CREATED))).body(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }
}
