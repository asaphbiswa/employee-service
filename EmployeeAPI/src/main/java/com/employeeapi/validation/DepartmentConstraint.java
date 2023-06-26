package com.employeeapi.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartmentValidator.class)
@Documented
public @interface DepartmentConstraint {
	
	String message() default "The direct report of a Manager must belong to the same Department of the Manager";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
