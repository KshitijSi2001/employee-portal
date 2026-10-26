package com.learnings.employee_portal.controller;

import com.learnings.employee_portal.dto.DepartmentRequest;
import com.learnings.employee_portal.dto.DepartmentResponse;
import com.learnings.employee_portal.service.DepartmentService;
import jakarta.validation.Valid;
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
    public List<DepartmentResponse> findAllDepartments(){
        return departmentService.findAllDepartments();
    }

    @GetMapping("/departments/{departmentId}")
    public DepartmentResponse findDepartmentById(@PathVariable("departmentId") int departmentId){
        return departmentService.findDepartmentById(departmentId);
    }

    @PostMapping("/departments")
    public ResponseEntity<DepartmentResponse> postDepartment(@Valid @RequestBody DepartmentRequest request){

        //return ResponseEntity.created(URI.create(String.valueOf(HttpStatus.CREATED))).body(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.postDepartment(request));
    }
}
