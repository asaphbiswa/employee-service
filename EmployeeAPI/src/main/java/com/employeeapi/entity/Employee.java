package com.employeeapi.entity;


import com.employeeapi.enums.Department;
import com.employeeapi.enums.EmployeePosition;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="EMPLOYEES")
@ToString(exclude="directReports")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="employee_name")
	private String employeeName;
	
	@Column(name="date_of_birth")
	private LocalDate  dateOfBirth;
	
	@Column(name="email_address", unique =true)
	private String emailAddress;
	
	@Column(name="salary")
	private BigDecimal salary;
	
	@Enumerated(EnumType.STRING)
	@Nonnull
	@Column(name="department")
	private Department department;
	
	@Enumerated(EnumType.STRING)
	@Nonnull
	@Column(name="position")
	private EmployeePosition employeePosition;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee reportingManager;
	
	@JsonManagedReference
	@OneToMany(mappedBy= "reportingManager", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Employee> directReports;



}
