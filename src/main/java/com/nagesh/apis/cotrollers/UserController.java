package com.nagesh.apis.cotrollers;
                                                              
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagesh.apis.entities.User;
import com.nagesh.apis.payloads.ApiResponse;
import com.nagesh.apis.payloads.UserDto;
import com.nagesh.apis.service.impl.UserServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserServiceImp userService; 
	
	//POST-create user
	@PostMapping("/")
	public ResponseEntity<UserDto> craeteUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto userDto1= userService.createUser(userDto);
		return ResponseEntity.ok(userDto1);
	}
	
	//POST-login user
	@PostMapping("/login")
	public String loginUser(@RequestBody UserDto user)
	{
		return userService.verify(user);
	}
	                                  
	//PUT- update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId)
	{
		UserDto updatedUser=userService.upadteUser(userDto, userId);
		
		return ResponseEntity.ok(updatedUser);
	}
	
	//DELETE-delete user
	@DeleteMapping("/{userId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid)
	{
		userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted suceessfully", true),HttpStatus.OK);
	}
	
	//GET - get all user
	@GetMapping("/")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<UserDto>> getAlluser()
	{
		List<UserDto> users=	userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	//GET - user get
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(userService.getUserById(userId)); 
	}
}
