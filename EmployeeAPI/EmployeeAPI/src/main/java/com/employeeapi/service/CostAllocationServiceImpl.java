package com.employeeapi.service;

import com.employeeapi.entity.Employee;
import com.employeeapi.enums.Department;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CostAllocationServiceImpl implements CostAllocationService {

	@Autowired
	EmployeeService employeeService;
	
	@Override
	public BigDecimal calculateCostAllocationByDepartment(Department department) {
		List<Employee> employeeList = employeeService.findByDepartment(department);
		
		return sumOfEmpSalaries(employeeList);
	}

	@Override
	public BigDecimal calculateCostAllocationByManager(Long employeeId) {
		List<Employee> employeeList = employeeService.findByReportingManagerId(employeeId);
		return sumOfEmpSalaries(employeeList);
	}
	
	private BigDecimal sumOfEmpSalaries(List<Employee> employeeList) {
		BigDecimal totalSalaries = BigDecimal.ZERO;
		for (Employee employee: employeeList) {
			totalSalaries = totalSalaries.add(employee.getSalary());
		}
		return totalSalaries;
	}

}
