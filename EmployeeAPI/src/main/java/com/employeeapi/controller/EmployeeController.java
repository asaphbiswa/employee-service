package com.employeeapi.controller;

import com.employeeapi.entity.Employee;
import com.employeeapi.enums.Department;
import com.employeeapi.exception.EmployeeNotFoundException;
import com.employeeapi.model.DepartmentStructureResponse;
import com.employeeapi.respository.EmployeeRepository;
import com.employeeapi.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/empapi")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addNewEmployee(@Valid @RequestBody Employee employee) throws EmployeeNotFoundException {
        log.info("Add an employee");
        if (employee.getReportingManager() != null) {
            employee.setReportingManager(employeeService.findById(employee.getReportingManager().getId()));
        }

        employeeRepository.save(employee);
        log.info("Employee with id: {} added successfully", employee.getId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAllEmployees() {
        log.info("Listing all the employees");
        List<Employee> empList = new ArrayList<>();
        employeeRepository.findAll().forEach(empList::add);

        return new ResponseEntity<>(empList, HttpStatus.OK);
    }

    @PutMapping("/employees/{empId}")
    @ResponseStatus(HttpStatus.OK)
    public String updateEmployeeDetail(@Valid @PathVariable Long empId, @RequestBody Employee employee) throws EmployeeNotFoundException {
        log.info("Update an employee");
        this.employeeService.updateEmployee(employee, empId);
        return "Employee with id: " + empId + " got updated successfully";
    }

    @DeleteMapping("/employees/{empId}")
    @ResponseStatus(HttpStatus.OK)
    public String removeEmployee(@PathVariable Long empId) throws EmployeeNotFoundException {
        log.info("Remove an employee");
        this.employeeService.remove(empId);
        return "Employee with id: " + empId + " got deleted successfully";
    }


    @GetMapping("/department/{department}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentStructureResponse listDeptStructure(@PathVariable Department department) throws EmployeeNotFoundException {
        log.info("listing the Department Structure");
        if (employeeService.departmentExists(department)) {
            log.info("Department found.");
            return employeeService.listDepartmentStructure(department);
        }
        return null;

    }
}
