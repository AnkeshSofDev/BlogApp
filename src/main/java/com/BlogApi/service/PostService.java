package com.BlogApi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.BlogApi.payload.PostDto;

public interface PostService {
	


public	PostDto createPost(PostDto postDto);

public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

public PostDto getPostByID(long id);

public PostDto UpdateOnePost(PostDto postdto, long id);

public void deleteOnePostById(long id);

}
