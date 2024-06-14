package com.example.springboot.services;

import java.util.List;

import com.example.springboot.entities.Post;
import com.example.springboot.payloads.PostDto;
import com.example.springboot.payloads.PostResponse;

public interface PostService {

	Post createPost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	PostDto getPostById(Integer postId);

//	List<PostDto> getAllPostByCategory(Integer categoryId);
	PostResponse getAllPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);

//	List<PostDto> getAllPostByUser(Integer userId);
	PostResponse getAllPostByUser(Integer userId, Integer pageNumber, Integer pageSize);

//	List<PostDto> getAllPost();
//	List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	void deletePost(Integer postId);

	List<PostDto> searchPost(String keyword);
}
