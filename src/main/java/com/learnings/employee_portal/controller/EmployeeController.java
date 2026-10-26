package com.learnings.employee_portal.controller;

import com.learnings.employee_portal.dto.EmployeeCreateRequest;
import com.learnings.employee_portal.dto.EmployeeResponse;
import com.learnings.employee_portal.dto.EmployeeUpdateRequest;
import com.learnings.employee_portal.model.Status;
import com.learnings.employee_portal.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;



    //done
    @GetMapping("/employees")
    public List<EmployeeResponse> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //done
    @GetMapping("/employees/id/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("employeeId") int employeeId){

        //return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(employeeId));


        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(employeeId));


    }

    //done
    @GetMapping("/employees/age/{age}")
    public List<EmployeeResponse> getEmployeesByAge(@PathVariable("age") int age){
        return employeeService.getEmployeesByAge(age);
    }

    //done
    @GetMapping("/employees/departmentId/{departmentId}")
    public ResponseEntity<List<EmployeeResponse>> findEmployeesByDepartment_DepartmentId(@PathVariable("departmentId") int departmentId){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeesByDepartment_DepartmentId(departmentId));
    }

    @GetMapping("/employees/departmentName/{departmentName}")
    public ResponseEntity<List<EmployeeResponse>> findEmployeesByDepartment_DepartmentName(@PathVariable("departmentName") String departmentName){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeesByDepartment_DepartmentName(departmentName));
    }

    @GetMapping("/employees/status/{status}")
    public List<EmployeeResponse> findEmployeesByStatus(@PathVariable("status") Status status){
        return employeeService.findEmployeesByStatus(status);
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponse> addEmployees(@Valid @RequestBody EmployeeCreateRequest employee){

        //employeeService.addEmployees(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployees(employee));
    }

    @PutMapping("/employees/department")
    public ResponseEntity<EmployeeResponse> assignDepartmentToEmployee(@RequestParam int departmentId, @RequestParam int employeeId){
        EmployeeResponse savedEmployee = employeeService.assignDepartmentToEmployee(departmentId, employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(savedEmployee);
    }

    @PutMapping("/employees")
    public ResponseEntity<EmployeeResponse> updateEmployees( @RequestParam int employeeId, @Valid @RequestBody EmployeeUpdateRequest request){
       return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployees(employeeId, request));
    }



    //this is a soft delete method, as the organization won't delete the data of employees
    //in any short time, when the employee will leave the organization,
    //so it's better to update their status to inactive.
    @PutMapping("/employees/status")
    public ResponseEntity<EmployeeResponse> updateStatusOfEmployees(@RequestParam int employeeId){
        //employeeService.updateStatusOfEmployees(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateStatusOfEmployees(employeeId));
    }

}
