package com.employeeapi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.employeeapi.entity.Employee;
import com.employeeapi.enums.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentStructureResponse {
	
	private Map<Department, List<Employee>> departmentStructure = new HashMap<>();

}
