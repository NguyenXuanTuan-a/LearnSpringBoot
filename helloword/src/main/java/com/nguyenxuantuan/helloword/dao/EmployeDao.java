package com.nguyenxuantuan.helloword.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nguyenxuantuan.helloword.model.EmployeDTO;

public interface EmployeDao {
	void add(EmployeDTO emp);
	List<EmployeDTO> getAll();
	void delete(int id);
	void update(EmployeDTO emp);
	EmployeDTO get(int id);
}

@org.springframework.transaction.annotation.Transactional // bao toan tinh toan ven cua du lieu
@Repository
class EmployeDaoImpl implements EmployeDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void add(EmployeDTO emp) {
		String sql = "insert into employe(name,role) value('"+emp.getName()+"','"+emp.getRole()+"')";
		jdbcTemplate.execute(sql);
	}
	@Override
	public List<EmployeDTO> getAll() {
		String sql = "select * from employe";
		return  jdbcTemplate.query(sql, new RowMapper<EmployeDTO>() {
			@Override
			public EmployeDTO mapRow(ResultSet rs ,int index )throws SQLException{
				EmployeDTO emp = new EmployeDTO();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setRole(rs.getString("role"));
				return emp;
			}
		});
		
		
	}
	@Override
	public void delete(int id) {
		String sql ="delete from employe where id = "+id+"";
		jdbcTemplate.execute(sql);
		
	}
	@Override
	public void update(EmployeDTO emp) {
		String sql = "update employe set name='"+emp.getName()+"' and role ='"+emp.getRole()+"' where id="+emp.getId()+"";
		jdbcTemplate.execute(sql);
	}
	
	public EmployeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeDTO e = new EmployeDTO();
		e.setId(rs.getInt("id"));
		e.setName(rs.getString("name"));
		e.setRole(rs.getString("role"));
		return e;
	}
	@Override
	public EmployeDTO get(int id) {
		String sql = "SELECT * FROM  employe  where id = ? ";
		@SuppressWarnings("deprecation")
		EmployeDTO e = jdbcTemplate.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<>(EmployeDTO.class));
		return e;

		/*jdbcTemplate.query(sql, new RowMapper<EmployeDTO>() {
			@Override
			public EmployeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				EmployeDTO e = new EmployeDTO();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setRole(rs.getString("role"));
				return e;
			}
		});*/
	}
	
}
