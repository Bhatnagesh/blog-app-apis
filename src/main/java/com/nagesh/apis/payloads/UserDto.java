package com.nagesh.apis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {
	
	private Integer id;
	
	@NotEmpty
	@Size(min = 4,message = "User name must be minimun 4 charcter's")
	private String name;
	
	@NotEmpty
	@Email(message = "Email Address is not valid")
	private String email;
	
	
	@NotEmpty
	@Size(min=3,max=10,message="Password must be min of 3 chars and max of 10 chars !!")
	private String password;
	
	@NotEmpty
	private String about;

}
