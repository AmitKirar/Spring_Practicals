package com.rays.service;

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
}
