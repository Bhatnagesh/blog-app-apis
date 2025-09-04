package com.nagesh.apis.cotrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagesh.apis.payloads.ApiResponse;
import com.nagesh.apis.payloads.CategoryDto;
import com.nagesh.apis.service.impl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	//POST
	
	
	
	@PostMapping("/")
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
	    CategoryDto categoryDto2 =categoryService.addCategory(categoryDto);
    	return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.CREATED);
    }
	
	//PUT
	@PutMapping("/{cid}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer cid)
	{
		CategoryDto updatedCategory=categoryService.updateCategory(categoryDto, cid);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
	//DELETE
	@DeleteMapping("/{cid}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer cid)
	{
		categoryService.deleteCategory(cid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user id "+cid+" deleted sucessfully",true),HttpStatus.OK);
	}
	
	//GET n
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
	  List<CategoryDto> categoryDto  =	categoryService.getAllCategory();
	  return ResponseEntity.ok(categoryDto);
	}
	
	//GET 1
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer id)
	{
		CategoryDto categoryDto = categoryService.getSingleCategory(id);
		
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}

}
