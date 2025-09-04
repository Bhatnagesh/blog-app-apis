package com.nagesh.apis.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagesh.apis.entities.Comment;
import com.nagesh.apis.entities.Post;
import com.nagesh.apis.exceptions.ResourceNotFoundException;
import com.nagesh.apis.payloads.CommentDto;
import com.nagesh.apis.repositories.CommentRepository;
import com.nagesh.apis.repositories.PostRepo;
import com.nagesh.apis.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	 
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
	Post post =	postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","post Id" ,postId));
	
	Comment comment=modelMapper.map(commentDto,Comment.class);

	comment.setPost(post);  
	
	Comment savedcomment = commentRepo.save(comment);
	
	return modelMapper.map(savedcomment,CommentDto.class);
	}
	
	@Override
	public void deleteComment(Integer commentId) {
	
		
	}

}
