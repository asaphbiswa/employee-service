package com.employeeapi.respository;

import com.employeeapi.entity.Employee;
import com.employeeapi.enums.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	List<Employee> findByDepartment(Department department);
	
	Employee findByEmployeeName(String employeeName);
	
	List<Employee> findByDepartmentAndReportingManagerIdIsNull (Department department);
	
	List<Employee> findByReportingManagerId(Long reportingManagerId);
		
}
