package com.nagesh.apis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

	@Configuration
	public class SwaggerConfig {
	    @Bean
	    public OpenAPI customOpenAPI() {
	        return new OpenAPI()
	            .info(new Info()
	                .title("Spring Boot Blog APi")
	                .version("1.0")
	                .description("API Documentation for My Application")
	                .termsOfService("https://example.com/terms")
	                .contact(new Contact()
	                    .name("Nagesh")
	                    .email("nagesh@example.com")
	                    .url("https://example.com"))
	                .license(new License()
	                    .name("Apache 2.0")
	                    .url("https://www.apache.org/licenses/LICENSE-2.0")));
	    }
	}


