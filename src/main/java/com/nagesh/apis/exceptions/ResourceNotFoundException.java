package com.nagesh.apis.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {
	
	private String resourseName;
	
	private String fieldName;
	
	private long fieldValueLong;

	public ResourceNotFoundException(String resourseName, String fieldName, long fieldValueLong) {
		super(String.format("%s not found with %s : %s",resourseName,fieldName,fieldValueLong));
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.fieldValueLong = fieldValueLong;
	}
	
	

}
