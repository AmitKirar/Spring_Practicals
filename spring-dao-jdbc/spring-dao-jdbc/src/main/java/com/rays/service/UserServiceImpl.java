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
	private UserDAOInt dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(UserDTO dto) throws Exception {

		UserDTO existdto = findyByLogin(dto.getLogin());
		if (existdto != null) {
			throw new Exception("duplicate record exception");
		}

		dto.setId(nextPk());
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(UserDTO dto) {
		dao.delete(dto);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(UserDTO dto) {
		dao.update(dto);

	}

	public UserDTO findyByLogin(String login) {
		UserDTO dto = dao.findyByLogin(login);
		return dto;
	}

	public UserDTO authenticate(String login, String password) {
		UserDTO dto = dao.authenticate(login, password);
		return dto;
	}

	public UserDTO findByPk(long Pk) {
		UserDTO dto = dao.findByPk(Pk);
		return dto;
	}

	public UserDTO findByName(String name) {
		UserDTO dto = dao.findByName(name);
		return dto;
	}

	public Integer nextPk() {
		int pk = dao.nextPk();
		return pk;
	}

	public List search(UserDTO dto) {
		List l = dao.search(dto);
		return l;

	}

	public List search(UserDTO dto, int pageNo, int pageSize) {
		List l = dao.search(dto, pageNo, pageSize);
		return l;
	}

}
