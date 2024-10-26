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

















//
//
//
//
//package com.example.restcontroller;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.model.Employee;
//import com.example.repository.EmployeeRepository;
//
//@RestController
//@RequestMapping("/api/employees") 
//public class EmployeeRestController {
//
//    // Initialize Logger
//    private static final Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);
//
//    @Autowired
//    private EmployeeRepository repo;
//
//    
//    @PostMapping("/save")
//    public String saveEmployee(@RequestBody Employee employee) {
//        logger.info("Received request to save employee: {}", employee);
//
//        try {
//            repo.saveEmployee(employee);
//            logger.info("Employee saved successfully: {}", employee);
//            return "Employee Added Successfully";
//        } catch (Exception e) {
//            logger.error("Error saving employee: {}", employee, e);
//            return "Failed to Add Employee";
//        }
//    }
//
//    /**
//     * Update an existing Employee by ID
//     */
//    @PutMapping("/update/{id}")
//    public String updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
//        logger.info("Received request to update employee with id {}: {}", id, employee);
//
//        try {
//            employee.setId(id);
//            repo.updateEmployee(employee);
//            logger.info("Employee updated successfully with id {}: {}", id, employee);
//            return "Employee Updated Successfully";
//        } catch (Exception e) {
//            logger.error("Error updating employee with id {}: {}", id, e);
//            return "Failed to Update Employee";
//        }
//    }
//
//    /**
//     * Find Employee by ID
//     */
//    @GetMapping("/{id}")
//    public Employee findById(@PathVariable int id) {
//        logger.info("Received request to find employee with id {}", id);
//
//        try {
//            Employee employee = repo.findById(id);
//            logger.info("Found employee with id {}: {}", id, employee);
//            return employee;
//        } catch (Exception e) {
//            logger.error("Error finding employee with id {}", id, e);
//            throw e;  // Re-throw the exception to allow Spring to handle it
//        }
//    }
//
//    /**
//     * Delete Employee by ID
//     */
//    @DeleteMapping("/{id}")
//    public String deleteById(@PathVariable int id) {
//        logger.info("Received request to delete employee with id {}", id);
//
//        try {
//            repo.deleteById(id);
//            logger.info("Employee deleted successfully with id {}", id);
//            return "Employee Deleted Successfully";
//        } catch (Exception e) {
//            logger.error("Error deleting employee with id {}", id, e);
//            return "Failed to Delete Employee";
//        }
//    }
//
//    /**
//     * Find All Employees
//     */
//    @GetMapping
//    public List<Employee> findAll() {
//        logger.info("Received request to fetch all employees");
//
//        try {
//            List<Employee> employees = repo.findAll();
//            logger.info("Found {} employees", employees.size());
//            return employees;
//        } catch (Exception e) {
//            logger.error("Error fetching all employees", e);
//            throw e;
//        }
//    }
//}

