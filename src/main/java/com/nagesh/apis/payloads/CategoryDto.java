package com.nagesh.apis.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDto {
	
	private Integer categoryId;

	@NotBlank
	@Size(min=4,message="Min size of category title is 4")
	private String categoryTitle;

	@NotBlank
	@Size(max=10,message="min size of category desc is 10 ")
	private String categoryDescription;

}
