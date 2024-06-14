package com.example.springboot.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.springboot.entities.Category;
import com.example.springboot.entities.Post;
import com.example.springboot.entities.User;
import com.example.springboot.exceptions.ResourceNotFoundException;
import com.example.springboot.payloads.CategoryDto;
import com.example.springboot.payloads.CommentDto;
import com.example.springboot.payloads.PostDto;
import com.example.springboot.payloads.PostResponse;
import com.example.springboot.payloads.UserDto;
import com.example.springboot.repositories.CategoryRepo;
import com.example.springboot.repositories.PostRepo;
import com.example.springboot.repositories.UserRepo;
import com.example.springboot.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Post createPost(PostDto postDto, Integer userId, Integer categoryId) {

		Category existingCategory = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		User existingUser = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("check.png");
		post.setAddedDate(new Date(0));
		post.setCategory(existingCategory);
		post.setUser(existingUser);

		Post newPost = postRepo.save(post);
//		PostDto postDto2 = modelMapper.map(newPost, PostDto.class);
//		return modelMapper.map(newPost, PostDto.class);
//		return postDto2;
		return newPost;

	}

//	@Override
//	public List<PostDto> getAllPostByUser(Integer userId) {
//
//		List<Post> posts = postRepo.findAllByUserId(userId);
//
//		return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//
//	}

	@Override
	public PostResponse getAllPostByUser(Integer userId, Integer pageNumebr, Integer pageSize) {

		PostResponse postResponse = new PostResponse();
		Pageable pageable = PageRequest.of(pageNumebr, pageSize);

//		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
//
//		postRepo.findByUser(user);

		Page<Post> posts = postRepo.findAllByUserId(userId, pageable);

		postResponse.setContent(
				posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList()));
		postResponse.setPageNumber(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLastPage(posts.isLast());

		return postResponse;

	}

//	@Override
//	public List<PostDto> getAllPostByCategory(Integer categoryId) {
//
//		List<Post> posts = postRepo.findAllByCategoryId(categoryId);
//
//		return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//
//	}

	@Override
	public PostResponse getAllPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {

		PostResponse postResponse = new PostResponse();
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<Post> posts = postRepo.findAllByCategoryId(categoryId, pageable);

		postResponse.setContent(
				posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList()));
		postResponse.setPageNumber(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLastPage(posts.isLast());

		return postResponse;

	}

//	@Override
//	public List<PostDto> getAllPost() {
//
//		List<Post> posts = postRepo.findAll();
//
//		return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//	}

//	@Override
//	public List<PostDto> getAllPost(Integer pageNumber, Integer pageSize) {
//
////		Integer pageSize = 5;
////		Integer pageNumber = 3;
//
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//
//		Page<Post> pagePost = postRepo.findAll(pageable);
//
//		return pagePost.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

		PostResponse postResponse = new PostResponse();

		Sort sort = sortOrder.equals("ascending") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = postRepo.findAll(pageable);

		postResponse.setContent(
				pagePost.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList()));
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

//	@Override
//	public PostDto getPostById(Integer postId) {
//
//		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
//
////		PostDto postDto = new PostDto();
////
////		BeanUtils.copyProperties(post, postDto);
////
////		return postDto;
//
//		PostDto postDto = new PostDto();
//
//		postDto.setPostId(post.getPostId());
//		postDto.setTitle(post.getTitle());
//		postDto.setAddedDate(post.getAddedDate());
//		postDto.setContent(post.getContent());
//		postDto.setImageName(post.getImageName());
//
////		CategoryDto categoryDto = new CategoryDto();
//
//		postDto.setCategoryDto(new CategoryDto(post.getCategory().getId(), post.getCategory().getTitle(),
//				post.getCategory().getContent()));
//
////		postDto.getCategoryDto().setContent(post.getCategory().getContent());
////		postDto.getCategoryDto().setId(post.getCategory().getId());
////		postDto.getCategoryDto().setTitle(post.getCategory().getTitle());
//
////		PostDto postDto = modelMapper.map(post, PostDto.class);
////
////		postDto.setCategoryDto(post.getCategory());
//
////		return modelMapper.map(post, PostDto.class);
//		return postDto;
//	}

	@Override
	public PostDto getPostById(Integer postId) {

		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		PostDto postDto = modelMapper.map(post, PostDto.class);

		CategoryDto categoryDto = modelMapper.map(post.getCategory(), CategoryDto.class);
		postDto.setCategoryDto(categoryDto);

		UserDto userDto = modelMapper.map(post.getUser(), UserDto.class);
		postDto.setUserDto(userDto);

		Set<CommentDto> commentDtos = post.getComments().stream()
				.map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toSet());
		postDto.setCommentDtos(commentDtos);

		return postDto;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post existingPost = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", postId));

		BeanUtils.copyProperties(postDto, existingPost);
		Post updatedPost = postRepo.save(existingPost);
		PostDto updatedPostDto = new PostDto();
		BeanUtils.copyProperties(updatedPost, updatedPostDto);

		return updatedPostDto;
	}

	@Override
	public void deletePost(Integer postId) {
		postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		postRepo.deleteById(postId);

	}

	@Override
	public List<PostDto> searchPost(String keyword) {

//		List<Post> posts = postRepo.findByTitleContaining(keyword);
		List<Post> posts = postRepo.searchByTitle(keyword);

		List<PostDto> postDtos = new ArrayList<>();

		posts.forEach((post) -> {
			PostDto postDto = new PostDto();
			BeanUtils.copyProperties(post, postDto);
			postDtos.add(postDto);

		});

		return postDtos;
	}

}
