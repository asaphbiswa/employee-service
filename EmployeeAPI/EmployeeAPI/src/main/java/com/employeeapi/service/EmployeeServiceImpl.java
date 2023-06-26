package com.employeeapi.service;

import com.employeeapi.entity.Employee;
import com.employeeapi.enums.Department;
import com.employeeapi.exception.EmployeeNotFoundException;
import com.employeeapi.model.DepartmentStructureResponse;
import com.employeeapi.respository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void remove(Long empId) {
        if (exists(empId)) {
            employeeRepository.deleteById(empId);
        } else {
            throw new EmployeeNotFoundException("Employee with id: " + empId + " to be deleted is not found");
        }
    }

    @Override
    public Employee updateEmployee(Employee employee, Long empId) {
        if (exists(empId)) {
            Employee updatedEmployee = findById(empId);
            updatedEmployee.setEmailAddress(employee.getEmailAddress());
            updatedEmployee.setDateOfBirth(employee.getDateOfBirth());
            updatedEmployee.setEmployeeName(employee.getEmployeeName());
            updatedEmployee.setSalary(employee.getSalary());
            updatedEmployee.setDepartment(employee.getDepartment());
            updatedEmployee.setEmployeePosition(employee.getEmployeePosition());
            if (employee.getReportingManager() != null) {
                updatedEmployee.setReportingManager(findById(employee.getReportingManager().getId()));

            }

            employeeRepository.save(updatedEmployee);
            return updatedEmployee;

        } else {

            throw new EmployeeNotFoundException("Employee with id: " + empId + " is not found.");
        }
    }

    @Override
    public boolean exists(Long employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    @Override
    public DepartmentStructureResponse listDepartmentStructure(Department department) {
        DepartmentStructureResponse response = new DepartmentStructureResponse();
        List<Employee> departmentManagers = employeeRepository.findByDepartmentAndReportingManagerIdIsNull(department);
        response.getDepartmentStructure().put(department, departmentManagers);
        return response;
    }

    @Override
    public List<Employee> findByDepartment(Department department) {
        return employeeRepository.findByDepartment(department);
    }

    @Override
    public List<Employee> findByReportingManagerId(Long reportingManagerId) {
        return employeeRepository.findByReportingManagerId(reportingManagerId);
    }

    @Override
    public Employee findById(Long employeeId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()){
            return employee.get();
        }
        return null;
    }

    public boolean departmentExists(Department department) {
        boolean departmentExists ;
        List<Employee> employeeList = findByDepartment(department);
        if (employeeList.size() == 0){
            throw new EmployeeNotFoundException("The Department: "+ department +" is not found");
        }
        departmentExists = true;

        return departmentExists;
    }
}
