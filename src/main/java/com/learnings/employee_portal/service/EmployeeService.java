package com.learnings.employee_portal.service;


import com.learnings.employee_portal.dto.EmployeeRequest;
import com.learnings.employee_portal.dto.EmployeeResponse;
import com.learnings.employee_portal.entity.Department;
import com.learnings.employee_portal.entity.Employee;
import com.learnings.employee_portal.model.Status;
import com.learnings.employee_portal.repository.DepartmentRepository;
import com.learnings.employee_portal.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    //done
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return helper(employees);
    }

    //done
    public EmployeeResponse getEmployeeById(int employeeId) {
        Employee employee =  employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND ,"Employee not found!!"));
        return toResponse(employee);
    }


    public void addEmployees(EmployeeRequest request) {
        Employee employee = toEntity(request);

        employeeRepository.save(employee);
    }


    //done
    public List<EmployeeResponse> getEmployeesByAge(int age) {
        List<Employee> employees =  employeeRepository.findEmployeesByAge(age);
        List<EmployeeResponse> result = new ArrayList<>();

        return helper(employees);
    }

    //done
    public List<EmployeeResponse> findEmployeesByDepartment_DepartmentId(int departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "department id not found!! So can't proceed!!"));

        List<Employee> employees =  employeeRepository.findEmployeesByDepartment_DepartmentId(departmentId);
        return helper(employees);
    }

    public List<EmployeeResponse> findEmployeesByStatus(Status status) {
        List<Employee> employees =  employeeRepository.findEmployeesByStatus(status);
        return helper(employees);
    }

    public void updateEmployees(int employeeId, EmployeeRequest request) {
        Employee obj = employeeRepository.findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found, hence can't proceed!!!"));
        obj.setFirstName(request.getFirstName());
        obj.setLastName(request.getLastName());
        obj.setAge(request.getAge());
        obj.setSalary(request.getSalary());
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
    public EmployeeResponse assignDepartmentToEmployee(int departmentId, int employeeId) {
        Department fetchedDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found!!"));
        Employee employee = employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!"));


        employee.setDepartment(fetchedDepartment);
        employeeRepository.save(employee);
        return toResponse(employee);
    }

    public  List<EmployeeResponse> findEmployeesByDepartment_DepartmentName(String departmentName) {
        Department department = departmentRepository.findByDepartmentName(departmentName);
        if(department == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "department name not found, hence can't proceed");
        }
        List<Employee> employees =  employeeRepository.findEmployeesByDepartment_DepartmentName(departmentName);
        return helper(employees);
    }

    //the mapper method, which will actually filter, what to send as
    //response to the client from an employee object.₹
    private EmployeeResponse toResponse(Employee employee){
        EmployeeResponse response = new EmployeeResponse();
        response.setEmployeeId(employee.getEmployeeId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setAge(employee.getAge());
        response.setStatus(employee.getStatus());
        Department department = employee.getDepartment();
        if(department == null){
            response.setDepartmentName("Not assigned to any department yet!");
        }
        else{
            response.setDepartmentName(department.getDepartmentName());

        }

        return response;
    }

    private Employee toEntity(EmployeeRequest request){
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setAge(request.getAge());
        employee.setSalary(request.getSalary());
        employee.setStatus(Status.ACTIVE);
        //employeeRepository.save(employee);
        return employee;
    }

    private List<EmployeeResponse> helper(List<Employee> employees){
        List<EmployeeResponse> result = new ArrayList<>();

        for(Employee e : employees){
            result.add(toResponse(e));
        }
        return result;
    }
}
