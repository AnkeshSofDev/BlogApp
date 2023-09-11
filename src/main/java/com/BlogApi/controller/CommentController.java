package com.BlogApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApi.payload.CommentDto;
import com.BlogApi.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> SaveComment(@PathVariable (value = "postId") long postId,@RequestBody CommentDto commenDto){
		
		return new ResponseEntity<>(commentService.creatComment(postId, commenDto),HttpStatus.CREATED);
	}
	
	//http://localhost:8080/api/posts/1/comments - give comments with post no
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentByPostId(@PathVariable(value = "postId")long postId,@RequestBody CommentDto commenDto){
	return commentService.getCommentsByPostId(postId);

}
	//http://localhost:8080/api/posts/1/comments/{1}
		@GetMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> getCommentsByid(@PathVariable (value = "postId") long postId,
			@PathVariable (value = "id") long commentId){
			CommentDto commentById = commentService.getCommentById(postId, commentId);
				return new ResponseEntity<>(commentById,HttpStatus.OK);
		
	}
	
	}
