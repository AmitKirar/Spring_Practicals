package com.rays.dao;

import com.rays.dto.UserDTO;

public interface UserDAOInt {
	
	public long add(UserDTO dto);
	
	public void delete(UserDTO dto);
	
	public void update(UserDTO dto);
	
	public UserDTO findyByLogin(String login);
	
	public UserDTO authenticate(String login,String password);
	
	public UserDTO findByPk(long Pk); 
	
	public UserDTO findByName(String name);
	
	public Integer nextPk();
	
	
}
