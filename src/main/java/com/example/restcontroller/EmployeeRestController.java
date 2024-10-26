package com.example.restcontroller;

import java.util.List;

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
	
	@PostMapping("/save")
	public String saveEmployee(@RequestBody Employee employee)
	{
		repo.saveEmployee(employee);
		return "Employee Added Successfully";
	}
	
	@PutMapping("/update/{id}")
	public String updateEmployee(@PathVariable int id,@RequestBody Employee employee)
	{
		employee.setId(id);
		repo.updateEmployee(employee);
		return "Employee Updated Successfully";
	}
	
	@GetMapping("/employee/{id}")
	public Employee findById(@PathVariable int id)
	{
		return repo.findById(id);
	}
	
	
	@DeleteMapping("/{id}")
	public String  deleteById(@PathVariable int id)
	{
		repo.deleteById(id);
		return "Employee Deleted Successfully";
	}
	
	@GetMapping("/employee")
	public List<Employee> findAll()
	{
		return repo.findAll();
	}
}
