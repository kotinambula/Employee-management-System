package com.example.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@RestController
public class EmployeeRestController {

	@Autowired
	private EmployeeRepository repo;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);
	
	@PostMapping("/save")
	public String saveEmployee(@RequestBody Employee employee)
	{
		logger.info("Recieved a request to save employee : {} ",employee);
		try {
		repo.saveEmployee(employee);
		logger.info("Employee Added Successfully : {}",employee);
		return "Employee Added Successfully";
		}
		catch (Exception e) {
			logger.error("Error while saving employee : {} ",employee,e);
			return "Failed Add Employee";
		}
	}
	
	@PutMapping("/update/{id}")
	public String updateEmployee(@PathVariable int id,@RequestBody Employee employee)
	{
		employee.setId(id);
		logger.info("Recieved a request to update employee by id : {}",id);
		try {
		repo.updateEmployee(employee);
		logger.info("updating employee : {},with id : {} is successfull",employee,id);
		return "Employee Updated Successfully";
		}
		catch (Exception e) {
			logger.error("updating employee : {}, with id : {} failed",employee,id,e);
			throw e;
		}
	}
	
	@GetMapping("/employee/{id}")
	public Employee findById(@PathVariable int id)
	{
		logger.info("Request to get employee by id : {}",id);
		try
		{
		Employee employee = repo.findById(id);
		logger.info("Founded employee with id : {},{}",id,employee);
		return employee;
	}
		catch (Exception e) {
			logger.error("Error while getting employee with id : {}",id,e);
			throw e;
		}
	}
	
	
	@DeleteMapping("/{id}")
	public String  deleteById(@PathVariable int id)
	{
		logger.info("Request to delete employee with id : {}",id);
		try
		{
		repo.deleteById(id);
		return "Employee Deleted Successfully";
		}
		catch (Exception e) {
			logger.error("Error occured while deleting employee with id : {}",id,e);
			throw e;
		}
	}
	
	@GetMapping("/employee")
	public List<Employee> findAll()
	{
		logger.info("Recieved a request to get list of employees");
		try {
		return repo.findAll();
		}
		catch (Exception e) {
			logger.error("Error occured while fetching list of employees",e);
			throw e;
		}
		
	}
}



















