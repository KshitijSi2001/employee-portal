package com.learnings.employee_portal.service;


import com.learnings.employee_portal.entity.Department;
import com.learnings.employee_portal.entity.Employee;
import com.learnings.employee_portal.model.Status;
import com.learnings.employee_portal.repository.DepartmentRepository;
import com.learnings.employee_portal.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND ,"Employee not found!!"));
    }


    public void addEmployees(Employee employee) {
        employeeRepository.save(employee);
    }


    public List<Employee> getEmployeesByAge(int age) {
        return employeeRepository.findEmployeesByAge(age);
    }

    public List<Employee> findEmployeesByDepartment_DepartmentId(int departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "department id not found!! So can't proceed!!"));

        return employeeRepository.findEmployeesByDepartment_DepartmentId(departmentId);

    }

    public List<Employee> findEmployeesByStatus(Status status) {
        return employeeRepository.findEmployeesByStatus(status);
    }

    public void updateEmployees(int employeeId, Employee employee) {
        Employee obj = employeeRepository.findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!!"));
        obj.setFirstName(employee.getFirstName());
        obj.setLastName(employee.getLastName());
        obj.setAge(employee.getAge());
        obj.setSalary(employee.getSalary());
        obj.setDepartment(employee.getDepartment());
        employeeRepository.save(obj);
    }

    public void updateStatusOfEmployees(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not present!!"));
        employee.setStatus(Status.INACTIVE);
        employeeRepository.save(employee);
    }

    //always try to fetch that part of db, which needs to be modified,
    //don't take entire object as the request body, as in such cases,
    //if the client modifies the body, then the modified object, will be saved
    //in the db, which might not be consistent with the original db structure/data.
    public Employee assignDepartmentToEmployee(int departmentId, int employeeId) {
        Department fetchedDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found!!"));
        Employee employee = employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!"));

        employee.setDepartment(fetchedDepartment);
        employeeRepository.save(employee);
        return employee;
    }

    public  List<Employee> findEmployeesByDepartment_DepartmentName(String departmentName) {
        Department department = departmentRepository.findByDepartmentName(departmentName);
        if(department == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "department name not found, hence can't proceed");
        }
        return employeeRepository.findEmployeesByDepartment_DepartmentName(departmentName);
    }
}
