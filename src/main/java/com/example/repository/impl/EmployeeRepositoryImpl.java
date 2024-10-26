package com.example.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
