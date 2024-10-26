package com.example.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	private JdbcTemplate template;
	
	private static final Logger logger =LoggerFactory.getLogger(EmployeeRepositoryImpl.class);
	@Override
	public int saveEmployee(Employee employee) {
		String sql = "insert into employee(name,department,salary) values (?,?,?)";	
		logger.debug("Execute SQL Query {}: with values : name={},department={},salary={}",sql,employee.getName(),employee.getDepartment(),employee.getSalary());
		try {
			
		 int rowsEffected = template.update(sql,employee.getName(),employee.getDepartment(),employee.getSalary());
		 logger.info("saved employee :  {} , Rows Effected : {}",employee,rowsEffected);
		 return rowsEffected;
		}
		catch (Exception e) {
			logger.error("Error saving employee : {}",employee,e);
			throw e;
		}
	}

	@Override
	public int updateEmployee(Employee employee) {
		String sql = "update employee set name=?,department=?,salary=? where id=? ";
		logger.debug("Execute Update Query {} :,to update name={},department={},salary={},with id : {}",sql,employee.getName(),employee.getDepartment(),employee.getSalary(),employee.getId());
		try
		{
		int rowsEffected = template.update(sql, employee.getName(),employee.getDepartment(),employee.getSalary(),employee.getId());
		logger.info("updating employee : {}, with id : {} successfull and effected rows : {}",employee,employee.getId(),rowsEffected);
		return rowsEffected;
		}
		catch (Exception e) {
			logger.error("Error while updating employee : {},with id : {} failed",employee,employee.getId(),e);
			throw e;
		}
	}

	@Override
	public Employee findById(int id) {
		String sql = "select * from employee where id=?";
		logger.debug("Execute a query to get employee {} by id : {}",sql,id);
		try {
		Employee employee = template.queryForObject(sql, new EmployeeRowMapper(), id);
		logger.info("Found employee : {},with id : {}",employee,id);
		return employee;
		}
		catch (Exception e) {
			logger.error("Error  while getting employee by id : {} failed",id,e);
			throw e;
		}
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from employee where id=?";
		logger.debug("Execute a Query to delete employee {} with id : {}",sql,id);
		try {
		int rowsEffected = template.update(sql, id);
		logger.info("Deleting employee with id : {} successfull",id);
		return rowsEffected;
		}
		catch (Exception e) {
			logger.error("Error occured while deleting employee with id : {}",id,e);
			throw e;
		}
	}

	@Override
	public List<Employee> findAll() {
		String sql = "select * from employee";
		logger.debug("Execute Query to fetch list of employees from database {}",sql);
		try {
		List<Employee> employees = template.query(sql,new EmployeeRowMapper());
		logger.info("Found {} employees.",employees.size());
		return employees;
		}
		catch (Exception e) {
			logger.error("Error occured while fetching all employees",e);
			throw e;
		}
	}
		
  static class EmployeeRowMapper implements RowMapper<Employee> {

      @Override
      public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
          Employee employee = new Employee(
                  rs.getInt("id"),
                  rs.getString("name"),
                  rs.getString("department"),
                  rs.getDouble("salary"));
          logger.debug("Mapped Employee: {}", employee);
          return employee;
      }
  }
		
	
}





















