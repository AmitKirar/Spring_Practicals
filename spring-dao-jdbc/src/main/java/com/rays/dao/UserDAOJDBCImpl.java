package com.rays.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rays.dto.UserDTO;

@Repository
public class UserDAOJDBCImpl implements UserDAOInt {

	private JdbcTemplate jdbcTemplate;

	private DataSource dataSource = null;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public long add(UserDTO dto) {
		String sql = "insert into st_user values(?, ?, ?, ?, ?)";

		int pk = jdbcTemplate.update(sql, dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getLogin(),
				dto.getPassword());
		return pk;
	}

	public void delete(UserDTO dto) {

		String sql = "delete from st_user where id =?";

		jdbcTemplate.update(sql, dto.getId());

	}

	public void update(UserDTO dto) {
		String sql = "update st_user set firstName=?,lastName=?,login=?,password=? where id=?";

		jdbcTemplate.update(sql, dto.getFirstName(), dto.getLastName(), dto.getLogin(), dto.getPassword(), dto.getId());

	}

	public UserDTO findyByLogin(String login) {

		try {
			String sql = "select * from st_user where login =?";

			Object[] params = { login };

			UserDTO user = jdbcTemplate.queryForObject(sql, params, new UserMapper());

			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public UserDTO authenticate(String login, String password) {

		try {
			String sql = "select * from st_user where login =? and password=?";

			Object[] params = { login, password };

			UserDTO user = jdbcTemplate.queryForObject(sql, params, new UserMapper());

			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public UserDTO findByPk(long Pk) {

		try {
			String sql = "select * from st_user where id=?";
			Object[] params = { Pk };

			UserDTO user = jdbcTemplate.queryForObject(sql, params, new UserMapper());
			return user;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public UserDTO findByName(String name) {
		try {
			String sql = "select * from st_user where firstName=?";

			Object[] params = { name };

			UserDTO user = jdbcTemplate.queryForObject(sql, params, new UserMapper());
			return user;
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	public Integer nextPk() {
	    try {
	        String sql = "select max(id) from st_user";
	        
	        Integer pk = jdbcTemplate.queryForObject(sql, Integer.class);
	        
	        if (pk == null) {
	            pk = 0;
	        }
	        return pk + 1;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 1; // agar table empty hai ya koi error aaye
	    }
	}

}
