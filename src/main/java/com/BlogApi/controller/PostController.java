package com.BlogApi.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApi.payload.PostDto;
import com.BlogApi.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;

	@PostMapping
	 public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto){
		  PostDto dto = postService.createPost(postdto);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
		 
	 }
	
	@GetMapping
	//http://localhost:8080/api/posts/?pageNo=0&pageSize=1&sortBy=title&sortDir=asc
	public List<PostDto> getAllPosts(
			@RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
			@RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,
			@RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue ="asc",required = false)String sortDir
			){		
		return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
		return new ResponseEntity<>(postService.getPostByID(id),HttpStatus.OK);
		}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> UpdateOnePost(@RequestBody PostDto postdto ,@PathVariable(name = "id") long id) {
		PostDto postResponse =postService.UpdateOnePost(postdto,id);
		return ResponseEntity.ok(postResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOnePostById(@PathVariable(name = "id") long id){
		 postService.deleteOnePostById(id);
		 return new ResponseEntity<String>("Post is Deleted!",HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
