package com.employeeapi.model;

import java.math.BigDecimal;

import com.employeeapi.enums.Department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostAllocationByDepartmentResponse {
	
	private BigDecimal totalCostByDept;
	
	private Department department;

}
