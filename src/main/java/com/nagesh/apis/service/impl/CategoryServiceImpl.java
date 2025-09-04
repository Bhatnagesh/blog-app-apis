package com.nagesh.apis.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagesh.apis.entities.Category;
import com.nagesh.apis.exceptions.ResourceNotFoundException;
import com.nagesh.apis.payloads.CategoryDto;
import com.nagesh.apis.repositories.CategoryRepository;
import com.nagesh.apis.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		
		Category category=modelMapper.map(categoryDto,Category.class);
    	Category newCategory=  categoryRepository.save(category);
		return modelMapper.map(newCategory,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryID) {
	    
	        Category category= categoryRepository.findById(categoryID).orElseThrow(()-> new ResourceNotFoundException("category "," id",categoryID));
	        category.setCategoryDescription(categoryDto.getCategoryDescription());
	        category.setCategoryTitle(categoryDto.getCategoryTitle());
	        Category updatedCategory=categoryRepository.save(category);
	               
	        return modelMapper.map(updatedCategory,CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer id) {
		
		Category category= categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category "," id",id));
		
		categoryRepository.delete(category);
		
		
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
	    List<Category> categories= categoryRepository.findAll();
	    List<CategoryDto> categoryy=categories.stream().map(category->modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
		
		return categoryy;
	}

	@Override
	public CategoryDto getSingleCategory(Integer id) {

		Category category= categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category "," id",id));
		
     	return modelMapper.map(category,CategoryDto.class);
	}
	

}
