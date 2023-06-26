package com.employeeapi.validation;

import com.employeeapi.entity.Employee;
import com.employeeapi.exception.EmployeeNotFoundException;
import com.employeeapi.service.EmployeeService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class DepartmentValidator implements ConstraintValidator<DepartmentConstraint, Employee> {
	
	@Autowired
	EmployeeService employeeService;
	
	@Override
	public boolean isValid(Employee employee, ConstraintValidatorContext context) {
		boolean isValid = false;
		
		if(employee.getReportingManager() == null) {
			log.info("This employee with id: {} is not reporting to another manager, the same department validation is not required", employee.getId());
			isValid = true;
		} else {
			try {
				Employee manager = employeeService.findById(employee.getReportingManager().getId());
				employee.getReportingManager().setDepartment(manager.getDepartment());
				
				if (employee.getDepartment().equals(employee.getReportingManager().getDepartment())) {
					log.info("The department of employee is matching with department of direct reporting manager");
					isValid = true;
				} 
				
			} catch (EmployeeNotFoundException e) {
				log.warn("Reporting manager with id: {} is not found", employee.getReportingManager().getId(),e);
			}
		}
		
		
		return isValid;
		
		
	}

}
