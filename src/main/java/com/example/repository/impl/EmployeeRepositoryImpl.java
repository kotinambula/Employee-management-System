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
		
		return template.update(sql,employee.getName(),employee.getDepartment(),employee.getSalary());
	}

	@Override
	public int updateEmployee(Employee employee) {
		String sql = "update employee set name=?,department=?,salary=? where id=? ";
		
		return template.update(sql, employee.getName(),employee.getDepartment(),employee.getSalary(),employee.getId());
	}

	@Override
	public Employee findById(int id) {
		String sql = "select * from employee where id=?";
		return template.queryForObject(sql, new EmployeeRowMapper(), id);
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from employee where id=?";
		return template.update(sql, id);
	}

	@Override
	public List<Employee> findAll() {
		String sql = "select * from employee";
		return template.query(sql,new EmployeeRowMapper());
	}
	
	static class EmployeeRowMapper implements RowMapper<Employee> {

		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Employee(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("department"),
					rs.getDouble("salary"));
		}

}
		
	
}



















//package com.example.repository.impl;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Repository;
//
//import com.example.model.Employee;
//import com.example.repository.EmployeeRepository;
//
//@Repository
//public class EmployeeRepositoryImpl implements EmployeeRepository {
//
//    @Autowired
//    private JdbcTemplate template;
//
//
//    private static final Logger logger = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);
//
//    @Override
//    public int saveEmployee(Employee employee) {
//        String sql = "INSERT INTO employee(name, department, salary) VALUES (?, ?, ?)";
//        logger.debug("Executing SQL: {} with values: name={}, department={}, salary={}", 
//                      sql, employee.getName(), employee.getDepartment(), employee.getSalary());
//
//        try {
//            int rowsAffected = template.update(sql, employee.getName(), employee.getDepartment(), employee.getSalary());
//            logger.info("Saved employee: {}. Rows affected: {}", employee, rowsAffected);
//            return rowsAffected;
//        } catch (DataAccessException ex) {
//            logger.error("Error saving employee: {}", employee, ex);
//            throw ex;  // Let Spring handle the exception.
//        }
//    }
//
//    @Override
//    public int updateEmployee(Employee employee) {
//        String sql = "UPDATE employee SET name=?, department=?, salary=? WHERE id=?";
//        logger.debug("Executing SQL: {} with values: name={}, department={}, salary={}, id={}", 
//                      sql, employee.getName(), employee.getDepartment(), employee.getSalary(), employee.getId());
//
//        try {
//            int rowsAffected = template.update(sql, employee.getName(), employee.getDepartment(), employee.getSalary(), employee.getId());
//            logger.info("Updated employee: {}. Rows affected: {}", employee, rowsAffected);
//            return rowsAffected;
//        } catch (DataAccessException ex) {
//            logger.error("Error updating employee: {}", employee, ex);
//            throw ex;
//        }
//    }
//
//    @Override
//    public Employee findById(int id) {
//        String sql = "SELECT * FROM employee WHERE id=?";
//        logger.debug("Executing SQL: {} with id={}", sql, id);
//
//        try {
//            Employee employee = template.queryForObject(sql, new EmployeeRowMapper(), id);
//            logger.info("Found employee with id {}: {}", id, employee);
//            return employee;
//        } catch (DataAccessException ex) {
//            logger.error("Error finding employee with id={}", id, ex);
//            throw ex;
//        }
//    }
//
//    @Override
//    public int deleteById(int id) {
//        String sql = "DELETE FROM employee WHERE id=?";
//        logger.debug("Executing SQL: {} with id={}", sql, id);
//
//        try {
//            int rowsAffected = template.update(sql, id);
//            logger.info("Deleted employee with id {}. Rows affected: {}", id, rowsAffected);
//            return rowsAffected;
//        } catch (DataAccessException ex) {
//            logger.error("Error deleting employee with id={}", id, ex);
//            throw ex;
//        }
//    }
//
//    @Override
//    public List<Employee> findAll() {
//        String sql = "SELECT * FROM employee";
//        logger.debug("Executing SQL: {}", sql);
//
//        try {
//            List<Employee> employees = template.query(sql, new EmployeeRowMapper());
//            logger.info("Found {} employees.", employees.size());
//            return employees;
//        } catch (DataAccessException ex) {
//            logger.error("Error retrieving all employees", ex);
//            throw ex;
//        }
//    }
//
//    // RowMapper to map ResultSet to Employee object
//    static class EmployeeRowMapper implements RowMapper<Employee> {
//
//        @Override
//        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Employee employee = new Employee(
//                    rs.getInt("id"),
//                    rs.getString("name"),
//                    rs.getString("department"),
//                    rs.getDouble("salary"));
//            logger.debug("Mapped Employee: {}", employee);
//            return employee;
//        }
//    }
//}

