package com.BlogApi.service;

import java.util.List;

import com.BlogApi.payload.CommentDto;

public interface CommentService {
	
	public CommentDto creatComment(long postId,CommentDto commentDto);
	List<CommentDto> getCommentsByPostId(long postId);
	CommentDto getCommentById(Long postId,Long commentId);

}
