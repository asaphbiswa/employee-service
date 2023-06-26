package com.employeeapi.service;

import java.util.List;

import com.employeeapi.entity.Employee;
import com.employeeapi.enums.Department;
import com.employeeapi.model.DepartmentStructureResponse;

public interface EmployeeService {
	
	Employee findById(Long employeeId);
	
	List<Employee> findByDepartment(Department department);
	
	List<Employee> findByReportingManagerId(Long reportingManagerId);

	void remove (Long employeeId);
	
	boolean exists(Long employeeId);
	
	DepartmentStructureResponse listDepartmentStructure(Department department);
	
	Employee updateEmployee(Employee employee, Long employeeId);

	boolean departmentExists(Department department);


}
