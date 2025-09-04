package com.nagesh.apis.service;

import java.util.List;

import com.nagesh.apis.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto addCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryID);
	
	void deleteCategory(Integer id);
	
	List<CategoryDto>  getAllCategory();
	
    CategoryDto getSingleCategory(Integer id);
	
	

}
