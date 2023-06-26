package com.employeeapi.service;

import java.math.BigDecimal;

import com.employeeapi.enums.Department;

public interface CostAllocationService {
	
	BigDecimal calculateCostAllocationByDepartment(Department department);
	
	BigDecimal calculateCostAllocationByManager(Long employeeId);

}
