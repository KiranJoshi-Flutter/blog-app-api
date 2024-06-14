package com.example.springboot.controllers;

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

import com.example.springboot.entities.Post;
import com.example.springboot.payloads.ApiResponse;
import com.example.springboot.payloads.PostDto;
import com.example.springboot.payloads.PostResponse;
import com.example.springboot.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostService postService;

	@PostMapping("/userId/{userId}/categoryId/{categoryId}/post")
	public ResponseEntity<Post> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId) {
		Post createdPostDto = postService.createPost(postDto, userId, categoryId);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPostDto);

	}

//	@GetMapping("/user/{userId}/posts")
//	public ResponseEntity<List<PostDto>> getAllByUserId(@PathVariable("userId") Integer userId) {
//		List<PostDto> postsDto = postService.getAllPostByUser(userId);
//		return ResponseEntity.ok(postsDto);
//
//	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getAllByUserId(@PathVariable("userId") Integer userId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
		PostResponse postResponse = postService.getAllPostByUser(userId, pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}

//	@GetMapping("/category/{categoryId}/posts")
//	public ResponseEntity<List<PostDto>> getAllByCategoryId(@PathVariable("categoryId") Integer categoryId) {
//		List<PostDto> postsDto = postService.getAllPostByCategory(categoryId);
//		return ResponseEntity.ok(postsDto);
//
//	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getAllByCategoryId(@PathVariable("categoryId") Integer categoryId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
		PostResponse postResponse = postService.getAllPostByCategory(categoryId, pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}

//	@GetMapping("/allPosts")
//	public ResponseEntity<List<PostDto>> getAllPost() {
//
//		List<PostDto> postDtos = postService.getAllPost();
//		return ResponseEntity.ok(postDtos);
//	}

//	@GetMapping("/allPosts")
//	public ResponseEntity<List<PostDto>> getAllPost(
//			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
//			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
//
//		List<PostDto> postDtos = postService.getAllPost(pageNumber, pageSize);
////		return ResponseEntity.ok(postDtos);
//		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
//
//	}

	@GetMapping("/allPosts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortOrder", defaultValue = "ascending", required = false) String sortOrder) {

		PostResponse postResponse = postService.getAllPost(pageNumber, pageSize, sortBy, sortOrder);
//		return ResponseEntity.ok(postDtos);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId) {

		PostDto postDto = postService.getPostById(postId);
		return ResponseEntity.ok(postDto);
	}

	@PutMapping("/update/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer postId) {

		PostDto updatedPostDto = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
	}

	@DeleteMapping("/post/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer postId) {
		postService.deletePost(postId);
		return ResponseEntity.ok(new ApiResponse("Resource deleted successfully", true));
	}

//	@GetMapping("/post/search/{keyword}")
//	public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keyword") String keyword) {
//		return new ResponseEntity<List<PostDto>>(postService.searchPost(keyword), HttpStatus.OK);
//	}

	@GetMapping("/post/search")
	public ResponseEntity<List<PostDto>> searchPost(@RequestParam(value = "keyword", required = true) String keyword) {
		return new ResponseEntity<List<PostDto>>(postService.searchPost(keyword), HttpStatus.OK);
	}

}
