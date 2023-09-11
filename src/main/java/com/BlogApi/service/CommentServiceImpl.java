package com.BlogApi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.BlogApi.Entity.Comment;
import com.BlogApi.Entity.Post;
import com.BlogApi.payload.CommentDto;
import com.BlogApi.repository.CommentRepository;
import com.BlogApi.repository.PostRepository;

import Exception.BlogApiException;
import Exception.ResourceNotFoundException;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private PostRepository postRepo;

	@Override
	public CommentDto creatComment(long postId, CommentDto commentDto) {
		 Post post = postRepo.findById(postId).orElseThrow(
				  ()-> new ResourceNotFoundException("Post","id",postId)
				  );
			 Comment comment = mapToEntity(commentDto) ;
			 comment.setPost(post);
			 Comment save = commentRepo.save(comment);
			CommentDto dto= mapTODto(save);
		return dto;
		
		
	}
	private CommentDto mapTODto(Comment comment) {
		CommentDto dto = new CommentDto(); 
		dto.setId(comment.getId());
		dto.setName(comment.getName());
		dto.setEmail(comment.getEmail());
		dto.setBody(comment.getBody());	  
		return dto;
	}
	private Comment mapToEntity(CommentDto commentDto){
		Comment comment = new Comment();
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		return comment;
		
		
	}
	@Override
	public List<CommentDto> getCommentsByPostId(long postId) {
		List<Comment> comments = commentRepo.findByPostId(postId);
		return comments.stream().map(comment->mapTODto(comment)).collect(Collectors.toList());
		
	}
	
	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		Post post= postRepo.findById(postId).orElseThrow(
				 ()-> new ResourceNotFoundException("Post", "id", postId)
				);
		
		Comment comment =commentRepo.findById(commentId).orElseThrow(
				 ()->new ResourceNotFoundException("Comment","id",commentId)
				);
		
		if (comment.getPost().getId()!=(post.getId())) {
		     throw new BlogApiException(HttpStatus.BAD_REQUEST,"Connemts not exist:");
		}


				 
		
				/*
				 * if (comment.getPost() == null || post == null ||
				 * !comment.getPost().getId().equals(post.getId())) { throw new
				 * BlogApiException(HttpStatus.BAD_REQUEST, "Comment Does not Exist"); }
				 */
		
		return mapTODto(comment);
	}

}
