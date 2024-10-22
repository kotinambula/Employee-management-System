package com.example.repository;

import java.util.List;

import com.example.model.Employee;

public interface EmployeeRepository {

	int saveEmployee(Employee employee );
	int updateEmployee(Employee employee);
    Employee findById(int id);
    int deleteById(int id);
    List<Employee> findAll();
	
}
