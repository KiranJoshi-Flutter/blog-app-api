package com.example.springboot.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entities.Comment;
import com.example.springboot.entities.Post;
import com.example.springboot.exceptions.ResourceNotFoundException;
import com.example.springboot.payloads.CommentDto;
import com.example.springboot.repositories.CommentRepo;
import com.example.springboot.repositories.PostRepo;
import com.example.springboot.repositories.UserRepo;
import com.example.springboot.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
//	public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		Post existingPost = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

//		User existingUser = userRepo.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Comment comment = new Comment();
		BeanUtils.copyProperties(commentDto, comment);
		comment.setPost(existingPost);

		Comment newComment = commentRepo.save(comment);

		CommentDto returnCommentDto = new CommentDto();
		BeanUtils.copyProperties(newComment, returnCommentDto);

		return returnCommentDto;
	}

	@Override
	public void deleteComment(Integer commentId) {

		commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		commentRepo.deleteById(commentId);

	}

}
