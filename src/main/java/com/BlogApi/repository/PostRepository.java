package com.BlogApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApi.Entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
