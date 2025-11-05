package com.rays.service;

import java.util.List;

import com.rays.dto.UserDTO;

public interface UserServiceInt {

	public long add(UserDTO dto) throws Exception;
	
	public void delete(UserDTO dto);
	
	public void update(UserDTO dto);
	
	public UserDTO findyByLogin(String login);
	
	public UserDTO authenticate(String login, String password);
	
	public UserDTO findByPk(long Pk);
	
	public UserDTO findByName(String name);
	
	public Integer nextPk();
	
	public List search(UserDTO dto);
	
	public List search(UserDTO dto, int pageNo,int pageSize);
}
