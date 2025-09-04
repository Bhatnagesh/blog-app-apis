
package com.nagesh.apis.service.impl;
                                                               
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.nagesh.apis.entities.User;
import com.nagesh.apis.exceptions.ApiException;
import com.nagesh.apis.exceptions.ResourceNotFoundException;
import com.nagesh.apis.jwt.service.JwtService;
import com.nagesh.apis.payloads.UserDto;
import com.nagesh.apis.repositories.UserRepo;
import com.nagesh.apis.service.UserService;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	                                             
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User saveUser=userRepo.save(user);
		
		return this.userToDto(saveUser);
	}                          

	@Override
	public UserDto upadteUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", " id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=userRepo.save(user);
		return this.userToDto(updatedUser);
	}
	public UserDto getUserById(Integer userId) {
	    User user = userRepo.findById(userId)
	        .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

	    // Force initialize lazy collections
	  //  user.getPosts().size();
	   // user.getRoles().size();
	    
	    return this.userToDto(user);
	}



	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
	  User user  =	userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "id",userId));
	  userRepo.delete(user);	
		
	}                           
	
	public User dtoToUser(UserDto userDto)
	{
		User user=modelMapper.map(userDto,User.class);
	//	  user.setId(null);
		 user.setId(null);
		return user;
	}
	
	public UserDto userToDto(User user)
	{
		UserDto userDto=modelMapper.map(user,UserDto.class);
		
		return userDto;
	}

	public String verify(UserDto userDto) {
		 try
		 {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(),userDto.getPassword()));
		
		if(authentication.isAuthenticated())
		{
			return jwtService.genrateToken(userDto.getEmail());
		}
		 }
		 catch (Exception e) {
		  throw new ApiException("Invalid user name and Password");
		}
		return "fail";
	}
	
	

}
