package com.nagesh.apis;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BlogAppApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
		
		System.out.println(new BCryptPasswordEncoder().encode("8520"));
	}
	
	
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

}
