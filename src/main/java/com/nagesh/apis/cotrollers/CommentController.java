package com.nagesh.apis.cotrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagesh.apis.payloads.CommentDto;
import com.nagesh.apis.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(
			@RequestBody CommentDto commentDto,
			@PathVariable("postId") Integer postId
			)
	{
	    CommentDto createComment = commentService.createComment(commentDto, postId);
		return new  ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}
	
	@GetMapping("/message")
	public ResponseEntity<String> getResponse()
	{
		return ResponseEntity.ok("HWEELO COMMENT");
	}

}
