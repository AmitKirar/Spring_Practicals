package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDTO;

@Service
public class UserServiceImpl implements UserServiceInt {

	@Autowired
	private UserDAOInt dao = null;

	public long add(UserDTO dto) {

		long pk = dao.add(dto);
		return pk;
	}

	public void delete(UserDTO dto) {
		dao.delete(dto);

	}

	public void update(UserDTO dto) {
		dao.update(dto);

	}

	public UserDTO findByPk(long id) {
		UserDTO dto = dao.findByPk(id);
		return dto;

	}

	public UserDTO findByLogin(String login) {
		UserDTO dto = dao.findByLogin(login);
		return dto;
	}

	public UserDTO authenticate(String login, String password) {
		UserDTO dto = dao.authenticate(login, password);
		return dto;
	}

	public List search(UserDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);

	}

}
