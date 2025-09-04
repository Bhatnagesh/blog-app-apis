package com.nagesh.apis.service;

import java.util.List;

import com.nagesh.apis.payloads.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto userDto);
	
	public UserDto upadteUser(UserDto userDto,Integer userId);
	
	public UserDto getUserById(Integer userId);
	
	public List<UserDto> getAllUsers();
	
	public void deleteUser(Integer userId);

}
