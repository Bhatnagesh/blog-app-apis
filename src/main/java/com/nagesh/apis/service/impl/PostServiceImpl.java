package com.nagesh.apis.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.relation.RelationNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nagesh.apis.entities.Category;
import com.nagesh.apis.entities.Post;
import com.nagesh.apis.entities.User;
import com.nagesh.apis.exceptions.ResourceNotFoundException;
import com.nagesh.apis.payloads.PostDto;
import com.nagesh.apis.payloads.PostResponse;
import com.nagesh.apis.repositories.CategoryRepository;
import com.nagesh.apis.repositories.PostRepo;
import com.nagesh.apis.repositories.UserRepo;
import com.nagesh.apis.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
	User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("post "," id",userId));
	
	Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category "," id",categoryId));
	
	Post post= modelMapper.map(postDto,Post.class);
	
	post.setAddedDate(new Date());
	post.setImageName("default.png");
	post.setContent(postDto.getContent());
	post.setTitle(postDto.getTitle());
	post.setUser(user);
	post.setCategory(category);          
	
	 Post newPost = postRepo.save(post);
		
	return modelMapper.map(newPost,PostDto.class);
	}


	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
	
	Post post=	postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post ","postId ",postId));
	post.setContent(postDto.getContent());
	post.setTitle(postDto.getTitle());
	post.setImageName(postDto.getImageName());  
	postRepo.save(post);
		return modelMapper.map(post,PostDto.class);
	}


	@Override
	public void deletePost(Integer postId) {
		
		Post p = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", 1));
	    postRepo.delete(p);
		
	}


	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDire) {
		
		Sort sort=(sortDire.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		
		Pageable page=PageRequest.of(pageNumber,pageSize,sort);
		
		Page<Post> pagePosts = postRepo.findAll(page);
		
		List<Post> allPosts = pagePosts.getContent();
		
		List<PostDto> dtoPosts =	allPosts.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(dtoPosts);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());
		
		return postResponse; 
	}


	@Override
	public PostDto getPostById(Integer postId) {
		
	 Post post=	postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post ","post id",postId));
		
		return modelMapper.map(post,PostDto.class);
	}


	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category ", "categoryId ", categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}


	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		
	User user=	userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user ","userId ", userId));
	
	List<Post> posts= postRepo.findByUser(user);
	
	       
	
	
	List<PostDto> postDtos =posts.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
	
	return postDtos;
	}


	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		List<Post> posts  =	postRepo.findByTitleContaining(keyword);
		List<PostDto> postsDto=  posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postsDto;
	}
	
	

}
