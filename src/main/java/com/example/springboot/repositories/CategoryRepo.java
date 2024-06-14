package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
