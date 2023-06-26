package com.employeeapi.service;

import com.employeeapi.entity.Employee;
import com.employeeapi.enums.Department;
import com.employeeapi.exception.EmployeeNotFoundException;
import com.employeeapi.respository.EmployeeRepository;
import com.employeeapi.util.EmployeeTestFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeServiceImplTest {

    @Autowired
    EmployeeServiceImpl employeeService;

    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    void testRemoveEmployeeWhenEmployeeIdExists() {
        Long employeeId = 100L;
        Mockito.when(employeeRepository.existsById(employeeId)).thenReturn(true);
        Mockito.doNothing().when(employeeRepository).deleteById(employeeId);
        assertDoesNotThrow(() -> employeeService.remove(employeeId));
    }

    @Test
    void testRemoveWhenNoEmployeeIdExists() throws EmployeeNotFoundException {
        Long employeeId = 100L;
        Mockito.when(employeeRepository.existsById(employeeId)).thenReturn(false);

        EmployeeNotFoundException exceptionThrown = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.remove(employeeId);
        });
        String expectedErrorMessage = String.format("Employee with id: %s to be deleted is not found", employeeId);
        assertEquals(expectedErrorMessage, exceptionThrown.getMessage());
    }

    @Test
    void updateEmployee() {
        Employee existingEmployee = EmployeeTestFactory.buildDeveloper(100L, null, Department.IT);
        Employee replacingEmployee = EmployeeTestFactory.buildQATester(100L, null, Department.Finance);

        Mockito.when(employeeRepository.existsById(100L)).thenReturn(true);
        Mockito.when(employeeRepository.findById(100L)).thenReturn(Optional.of(existingEmployee));

        Employee returnedUpdatedEmployee = employeeService.updateEmployee(replacingEmployee, 100L);
        Assertions.assertEquals(returnedUpdatedEmployee.getDepartment(), replacingEmployee.getDepartment());
        Assertions.assertEquals(returnedUpdatedEmployee.getEmployeePosition(), replacingEmployee.getEmployeePosition());
    }

    @Test
    void testWhenEmployeeExistsTrue() {
        Mockito.when(employeeRepository.existsById(100L)).thenReturn(true);

        assertTrue(employeeService.exists(100L));
    }

    @Test
    void testWhenEmployeeExistsFalse() {
        Mockito.when(employeeRepository.existsById(100L)).thenReturn(false);

        assertFalse(employeeService.exists(100L));
    }

}