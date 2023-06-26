package com.employeeapi;

import com.employeeapi.entity.Employee;
import com.employeeapi.enums.Department;
import com.employeeapi.model.DepartmentStructureResponse;
import com.employeeapi.respository.EmployeeRepository;
import com.employeeapi.service.EmployeeServiceImpl;
import com.employeeapi.util.EmployeeTestFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private EmployeeServiceImpl employeeService;
	
	@MockBean
	private EmployeeRepository employeeRepository;

	@SneakyThrows
	@Test
	void testFindAllEmployees() {
		List<Employee> employees = Arrays.asList(EmployeeTestFactory.buildDeveloper(100L, 200L, Department.IT),
				EmployeeTestFactory.buildQATester(101L, 200L, Department.IT),
				EmployeeTestFactory.buildDeveloper(100L, 200L, Department.IT),
				EmployeeTestFactory.buildManager(200L,  Department.IT));
		
		when(employeeRepository.findAll()).thenReturn(employees);
		
		mockMvc.perform(get("/empapi/employees"))
			.andExpect(status().isOk())
			.andExpect(content().string((containsString("Developer"))))
			.andExpect(content().string((containsString("IT"))))
			.andExpect(content().string((containsString("Manager"))))
			.andExpect(content().string((containsString("QA_Tester"))));
			

	}

	@SneakyThrows
	@Test
	void testAddNewEmployee() {
		
		Employee employee = EmployeeTestFactory.buildDeveloper(100L, 200L, Department.IT);
		when(employeeRepository.save(employee)).thenReturn(employee);
		
		mockMvc.perform(post("/empapi/employees")
				.content(objectMapper.writeValueAsString(employee))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated());
	}

	@SneakyThrows
	@Test
	void testRemoveEmployee() {
		Mockito.when(employeeService.exists(100L)).thenReturn(true);
		Mockito.doNothing().when(employeeRepository).deleteById(100L);
		mockMvc.perform(delete("/empapi/employees/100"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Employee with id")));
	}

	@SneakyThrows
	@Test
	void testListDeptStructure() {
		List<Employee> departmentManagers = Arrays.asList(
				EmployeeTestFactory.buildManager(100L,  Department.IT),
				EmployeeTestFactory.buildManager(200L,  Department.IT));

		DepartmentStructureResponse departmentStructureResponse = new DepartmentStructureResponse();
		HashMap<Department, List<Employee>> departmentListHashMap = new HashMap<>();
		departmentListHashMap.put(Department.IT, departmentManagers);
		departmentStructureResponse.setDepartmentStructure(departmentListHashMap);

		Mockito.when(employeeService.listDepartmentStructure(Department.IT)).thenReturn(departmentStructureResponse);
		Mockito.when(employeeService.departmentExists(Department.IT)).thenReturn(true);

		mockMvc.perform(get("/empapi/department/IT"))
				.andDo(print())
				.andExpect(content().string(containsString("departmentStructure")))
				.andExpect(content().string(containsString("IT")))
				.andExpect(content().string(containsString("directReports")));

	}
	
	@SneakyThrows
	@Test
	void testUpdateEmployee() {

		Employee employee = EmployeeTestFactory.buildDeveloper(100L, null, Department.IT);
		Employee updatedEmployee = EmployeeTestFactory.buildDeveloper(100L, 200L, Department.Finance);
		Mockito.when(employeeService.updateEmployee(employee, 100L)).thenReturn(updatedEmployee);
		mockMvc.perform(put("/empapi/employees/100")
				.content(objectMapper.writeValueAsString(updatedEmployee))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Employee with id: 100 got updated successfully")));

	}
}
