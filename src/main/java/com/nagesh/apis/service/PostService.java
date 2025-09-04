package com.nagesh.apis.service;

import java.util.List;

import com.nagesh.apis.entities.Post;
import com.nagesh.apis.payloads.PostDto;
import com.nagesh.apis.payloads.PostResponse;

public interface PostService {
	
	//CREATE
	 PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	 
	 //UPDATE
	 PostDto updatePost(PostDto postDto,Integer postId);
	 
	 //DELETE
	 void deletePost(Integer postId);
	 
	 //GET ALL POST
	 PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String postDire);
	 
	 //GET SINGLE POST
	 PostDto getPostById(Integer postId);
	 
	 //GET ALL POSTS BY CATEGORY
	 List<PostDto> getPostsByCategory(Integer categoryId);
	 
	 //GET ALL POSTS BY USER
	 List<PostDto> getPostsByUser(Integer userId);
	 
	//Search Post
	 List<PostDto> searchPosts(String keyword);

}
