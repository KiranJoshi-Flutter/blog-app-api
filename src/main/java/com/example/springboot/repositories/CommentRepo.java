package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
