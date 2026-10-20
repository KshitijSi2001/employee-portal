package com.learnings.employee_portal.controller;

import com.learnings.employee_portal.entity.Employee;
import com.learnings.employee_portal.model.Status;
import com.learnings.employee_portal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/id/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") int employeeId){

        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(employeeId));


    }

    @GetMapping("/employees/age/{age}")
    public List<Employee> getEmployeesByAge(@PathVariable("age") int age){
        return employeeService.getEmployeesByAge(age);
    }

    @GetMapping("/employees/departmentId/{departmentId}")
    public ResponseEntity<List<Employee>> findEmployeesByDepartment_DepartmentId(@PathVariable("departmentId") int departmentId){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeesByDepartment_DepartmentId(departmentId));
    }

    @GetMapping("/employees/departmentName/{departmentName}")
    public ResponseEntity<List<Employee>> findEmployeesByDepartment_DepartmentName(@PathVariable("departmentName") String departmentName){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeesByDepartment_DepartmentName(departmentName));
    }

    @GetMapping("/employees/status/{status}")
    public List<Employee> findEmployeesByStatus(@PathVariable("status") Status status){
        return employeeService.findEmployeesByStatus(status);
    }

    @PostMapping("/employees")
    public void addEmployees(@RequestBody Employee employee){

        employeeService.addEmployees(employee);
    }

    @PutMapping("/employees/department")
    public ResponseEntity<Employee> assignDepartmentToEmployee(@RequestParam int departmentId, @RequestParam int employeeId){
        Employee savedEmployee = employeeService.assignDepartmentToEmployee(departmentId, employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(savedEmployee);
    }

    @PutMapping("/employees")
    public void updateEmployees(@RequestParam int employeeId, @RequestBody Employee employee){
        employeeService.updateEmployees(employeeId, employee);
    }



    //this is a soft delete method, as the organization won't delete the data of employees
    //in any short time, when the employee will leave the organization,
    //so it's better to update their status to inactive.
    @PutMapping("/employees/status")
    public void updateStatusOfEmployees(@RequestParam int employeeId){
        employeeService.updateStatusOfEmployees(employeeId);
    }

}
