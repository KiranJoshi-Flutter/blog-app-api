package com.example.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.payloads.ApiResponse;
import com.example.springboot.payloads.CommentDto;
import com.example.springboot.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping("/postId/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
			@PathVariable("postId") Integer postId) {
		CommentDto createdCommentDto = commentService.createComment(commentDto, postId);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCommentDto);

	}

	@DeleteMapping("/comment/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer commentId) {
		commentService.deleteComment(commentId);
		return ResponseEntity.ok(new ApiResponse("Resource deleted successfully", true));
	}
}
