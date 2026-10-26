package com.learnings.employee_portal.service;

import com.learnings.employee_portal.dto.DepartmentRequest;
import com.learnings.employee_portal.dto.DepartmentResponse;
import com.learnings.employee_portal.entity.Department;
import com.learnings.employee_portal.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentResponse> findAllDepartments() {
        List<Department> departments =  departmentRepository.findAll();
        List<DepartmentResponse> result = new ArrayList<>();
        for(Department d : departments){
            result.add(toResponse(d));
        }
        return result;
    }

    public DepartmentResponse findDepartmentById(int departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found, hence can't proceed!!!"));
        return toResponse(department);
    }

    public DepartmentResponse postDepartment(DepartmentRequest request) {

        //return departmentRepository.save(department);
       // return ResponseEntity.status(HttpStatus.OK).body(savedDepartment);
        Department department = toEntity(request);
        departmentRepository.save(department);
        return toResponse(department);
    }

    private DepartmentResponse toResponse(Department department){
        DepartmentResponse response = new DepartmentResponse();
        response.setDepartmentId(department.getDepartmentId());
        response.setDepartmentName(department.getDepartmentName());
        response.setDepartmentLocation(department.getDepartmentLocation());

        return response;
    }

    private Department toEntity(DepartmentRequest request){
        Department department = new Department();
        department.setDepartmentName(request.getDepartmentName());
        department.setDepartmentLocation(request.getDepartmentLocation());
        return department;
    }
}
