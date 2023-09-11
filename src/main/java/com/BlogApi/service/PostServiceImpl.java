package com.BlogApi.service;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.BlogApi.Entity.Post;
import com.BlogApi.payload.PostDto;
import com.BlogApi.repository.PostRepository;
import Exception.ResourceNotFoundException;


@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;

	@Override
	public PostDto createPost(PostDto postDto) {

		/*
		 * Post post = new Post(); post.setId(postDto.getId());
		 * post.setTitle(postDto.getTitle());
		 * post.setDescription(postDto.getDescription());
		 * post.setContent(postDto.getContent());
		 * 
		 * 
		 * Post newPost = postRepo.save(post);
		 * 
		 * PostDto dto = new PostDto(); dto.setId(newPost.getId());
		 * dto.setTitle(newPost.getTitle());
		 * dto.setDescription(newPost.getDescription());
		 * dto.setContent(newPost.getContent());
		 */
		
		Post post = maptoEntity(postDto);
		Post newPost = postRepo.save(post);
		PostDto dto = maptoDto(newPost);		
		return dto;
	}

	@Override
	public List<PostDto> getAllPosts(int pageNo,int pageSize,String sortBy, String sortDir) {
		Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
				Sort.by(sortBy).ascending():
					Sort.by(sortBy).descending();
				
				
		PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
		 Page<Post> posts = postRepo.findAll(pageable);
		 List<Post> content = posts.getContent();  //convert page to list
		return content.stream().map(post->maptoDto(post)).collect(Collectors.toList());
		
	}
	
	
	@Override
	public PostDto getPostByID(long id) {
		Post post = postRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Post","Id",id)
				);		
		return maptoDto(post);
	}
	
	
	@Override
	public PostDto UpdateOnePost(PostDto postdto, long id) {
		Post post =postRepo.findById(id).orElseThrow(
				  ()-> new ResourceNotFoundException("Post", "Id", id)
				);
		post.setTitle(postdto.getTitle());
		post.setDescription(postdto.getDescription());
		post.setContent(postdto.getContent());
		
		Post updatePost = postRepo.save(post);
		return maptoDto(updatePost);
	}
	
	
	@Override
	public void deleteOnePostById(long id) {
		Post post =postRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Post", "Id", id)
				);
	    postRepo.deleteById(id);
		
	}
	
	
	PostDto maptoDto(Post post) {
	PostDto dto = new PostDto();
	dto.setId(post.getId());
	dto.setTitle(post.getTitle());
	dto.setDescription(post.getDescription());
	dto.setContent(post.getContent());
	return dto;
	}

	  Post maptoEntity(PostDto dto) {
		Post post = new Post();
		post.setTitle(dto.getTitle());
		  post.setDescription(dto.getDescription());
		  post.setContent(dto.getContent());
		return post;
		
	}





}
