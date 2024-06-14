package com.example.springboot.services;

import com.example.springboot.payloads.CommentDto;

public interface CommentService {

//	CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);
	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);
}
