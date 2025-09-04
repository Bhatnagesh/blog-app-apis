package com.nagesh.apis.cotrollers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nagesh.apis.config.AppConstants;
import com.nagesh.apis.payloads.ApiResponse;
import com.nagesh.apis.payloads.PostDto;
import com.nagesh.apis.payloads.PostResponse;
import com.nagesh.apis.service.FileService;
import com.nagesh.apis.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService; 
	
	@Value("${project.image}")
	private String path;
	
	//CREATE POST
	
	@PostMapping("/user/{userid}/category/{categoryid}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userid,
			@PathVariable Integer categoryid
			
			)
	{
	  PostDto newPost=postService.createPost(postDto,userid,categoryid);
	  return new ResponseEntity<PostDto>(newPost,HttpStatus.CREATED);
	}
	
	//GET BY USER
	
	@GetMapping("/user/{uid}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer uid)
	{
	
	  List<PostDto>	 postDtos=postService.getPostsByUser(uid);
	  return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	//GET BY CATEGORY
	@GetMapping("/category/{cid}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer cid)
	{
		List<PostDto> postDtos=postService.getPostsByCategory(cid);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
		
	}
	//GET ALL POSTS
	@GetMapping("/")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(defaultValue =AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			                                         @RequestParam(defaultValue =AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			                                         @RequestParam(defaultValue=AppConstants.SORT_BY,required=false)  String sortBy,
			                                         @RequestParam(defaultValue=AppConstants.SORT_DIR,required=false)  String sortDire
			                                       )
	{
	     PostResponse postResponse=	postService.getAllPost(pageNumber,pageSize,sortBy,sortDire);
		 return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	//GET SINGLE POST BY ID
	@GetMapping("post/{pid}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer pid)
	{
		PostDto post=postService.getPostById(pid);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	//DELETE POST BY ID 
	@DeleteMapping("post/{pid}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer pid)
	{
	   	postService.deletePost(pid);
	   	return new  ResponseEntity<ApiResponse>(new ApiResponse("post id "+pid+" deleted",true ),HttpStatus.OK);
	}
	
	//UPDATE POST BY ID
	@PutMapping("/post/{pid}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer pid)
	{
		PostDto newPostDto= postService.updatePost(postDto, pid);
		return new ResponseEntity<PostDto>(newPostDto,HttpStatus.OK);
	}
	
	//Search
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword)
	{
		List<PostDto> postDtos= postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam MultipartFile image,
			@PathVariable Integer postId
			) throws Exception
	{
	PostDto postDto =  postService.getPostById(postId);
	String fileName =  fileService.uploadImage(path, image);
	System.out.println("--------------");
	System.out.println(fileName);
	System.out.println("--------------");
    postDto.setImageName(fileName);
    PostDto updatedPost =   postService.updatePost(postDto, postId);
    return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	//method to serve files
	@GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE )
	public void downloadImage(
			@PathVariable String imageName,
			HttpServletResponse response
			)throws IOException
	{
	InputStream resource  =	 fileService.getResource(path, imageName);
	response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	StreamUtils.copy(resource,response.getOutputStream());
	}
	
	
	
	
	
	
	
	

}
