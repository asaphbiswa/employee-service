package com.employeeapi.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostAllocationByManagerResponse {
	
	private BigDecimal totalCostByManager;
	
	private Long managerId;
	
	private String managerName;

}
