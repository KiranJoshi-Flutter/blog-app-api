package com.example.springboot.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot.entities.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {

//	List<Post> findAllByUserId(Integer userId);
//	Page<Post> findAllByUserId(Integer userId);
	Page<Post> findAllByUserId(Integer userId, Pageable pageable);

//	List<Post> findAllByCategoryId(Integer categoryId);
	Page<Post> findAllByCategoryId(Integer categoryId, Pageable pageable);

//	List<Post> findByTitleContaining(String keyword);
	@Query("SELECT p FROM Post p WHERE p.title LIKE %?1%")
	List<Post> searchByTitle(String keyword);

}
