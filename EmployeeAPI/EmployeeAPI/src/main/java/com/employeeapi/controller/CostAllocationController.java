package com.employeeapi.controller;

import com.employeeapi.entity.Employee;
import com.employeeapi.enums.Department;
import com.employeeapi.enums.EmployeePosition;
import com.employeeapi.exception.EmployeeNotFoundException;
import com.employeeapi.model.CostAllocationByDepartmentResponse;
import com.employeeapi.model.CostAllocationByManagerResponse;
import com.employeeapi.service.CostAllocationService;
import com.employeeapi.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/costapi")
public class CostAllocationController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CostAllocationService costAllocationService;
	
	@GetMapping(path ="department/{department}")
	public CostAllocationByDepartmentResponse calculateCostsByDept(@PathVariable Department department) throws EmployeeNotFoundException{
		log.info("Calculate Cost Allocation By Department");
		CostAllocationByDepartmentResponse costAllocationResponse = new CostAllocationByDepartmentResponse();

		if(employeeService.departmentExists(department)){
			BigDecimal totalCostAllocationByDept = costAllocationService.calculateCostAllocationByDepartment(department);
			costAllocationResponse.setDepartment(department);
			costAllocationResponse.setTotalCostByDept(totalCostAllocationByDept);
		}

		return costAllocationResponse;
	}

	@GetMapping(path ="manager/{employeeId}")
	public CostAllocationByManagerResponse calculateCostsByManager(@PathVariable Long employeeId) throws EmployeeNotFoundException {

		log.info("Calculate Cost Allocation By Manager");
		Employee manager = employeeService.findById(employeeId);
		BigDecimal totalCostAllocationByManager;

		if(manager != null && manager.getEmployeePosition().equals(EmployeePosition.Manager) ){
			totalCostAllocationByManager = costAllocationService.calculateCostAllocationByManager(employeeId);
		} else {
			throw new EmployeeNotFoundException("Manager with id:" + employeeId + " not found");
		}

		CostAllocationByManagerResponse costAllocationByManagerResponse = new CostAllocationByManagerResponse();
		costAllocationByManagerResponse.setManagerId(employeeId);
		costAllocationByManagerResponse.setManagerName(manager.getEmployeeName());
		costAllocationByManagerResponse.setTotalCostByManager(totalCostAllocationByManager);
		
		return costAllocationByManagerResponse;
	}

}


