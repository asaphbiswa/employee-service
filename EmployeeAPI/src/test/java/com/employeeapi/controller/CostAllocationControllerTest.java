package com.employeeapi.controller;

import com.employeeapi.entity.Employee;
import com.employeeapi.enums.Department;
import com.employeeapi.service.CostAllocationServiceImpl;
import com.employeeapi.service.EmployeeServiceImpl;
import com.employeeapi.util.EmployeeTestFactory;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CostAllocationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CostAllocationServiceImpl costAllocationService;

    @MockBean
    EmployeeServiceImpl employeeService;

    @SneakyThrows
    @Test
    void testCalculateCostsByDept() {
        Mockito.when(costAllocationService.calculateCostAllocationByDepartment(Department.IT)).thenReturn(BigDecimal.valueOf(800000.0));

        mockMvc.perform(get("/costapi/department/IT"))
                .andExpect(status().isOk())
                .andExpect(content().string((containsString("totalCostByDept"))))
                .andExpect(content().string((containsString("department"))));
    }

    @SneakyThrows
    @Test
    void testCalculateCostsByManager() {

        Employee manager = EmployeeTestFactory.buildManager(100L, Department.IT);
        Mockito.when(employeeService.findById(100L)).thenReturn(manager);
        Mockito.when(costAllocationService.calculateCostAllocationByManager(100L)).thenReturn(BigDecimal.valueOf(800000.0));

        mockMvc.perform(get("/costapi/manager/100"))
                .andExpect(status().isOk())
                .andExpect(content().string((containsString("managerId"))))
                .andExpect(content().string((containsString("managerName"))))
                .andExpect(content().string((containsString("totalCostByManager"))));
    }
}