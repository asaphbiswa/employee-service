package com.employeeapi.util;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.employeeapi.entity.Employee;
import com.employeeapi.enums.Department;
import com.employeeapi.enums.EmployeePosition;

public class EmployeeTestFactory {

    public static Employee buildEmployee(Long employeeId, EmployeePosition employeePosition, Long managerId, Department department) {

        return Employee.builder()
                .employeeName("Robert Watson")
                .id(employeeId)
                .dateOfBirth(LocalDate.of(1989, 1, 5))
                .employeePosition(employeePosition)
                .department(department)
                .salary(BigDecimal.valueOf(100000.0))
                .emailAddress("test@email.com")
                .reportingManager(buildManager(managerId, department))
                .build();
    }

    public static Employee buildDeveloper(Long employeeId, Long managerId, Department department) {

        return buildEmployee(employeeId, EmployeePosition.Developer, managerId, department);
    }

    public static Employee buildQATester(Long employeeId, Long managerId, Department department) {

        return buildEmployee(employeeId, EmployeePosition.QA_Tester, managerId, department);
    }

    public static Employee buildManager(Long managerId, Department department) {

        Employee manager = null;
        if (managerId != null) {
            manager = Employee.builder()
                    .id(managerId)
                    .employeeName("Robin Boss")
                    .dateOfBirth(LocalDate.of(1980, 1, 1))
                    .employeePosition(EmployeePosition.Manager)
                    .department(department)
                    .salary(BigDecimal.valueOf(200000.0))
                    .emailAddress("test@email.com").build();
        }
        return manager;
    }

}
