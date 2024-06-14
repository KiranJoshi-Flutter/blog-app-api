package com.example.springboot.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entities.Category;
import com.example.springboot.exceptions.ResourceNotFoundException;
import com.example.springboot.payloads.CategoryDto;
import com.example.springboot.repositories.CategoryRepo;
import com.example.springboot.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = categoryRepo.save(dtoToEntity(categoryDto));
		return entityToDto(category);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category existingCategory = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		existingCategory.setTitle(categoryDto.getTitle());
		existingCategory.setContent(categoryDto.getContent());

		Category category = categoryRepo.save(existingCategory);

		return entityToDto(category);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		return entityToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		return entityToDto(categoryRepo.findAll());
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		categoryRepo.deleteById(categoryId);

	}

	public Category dtoToEntity(CategoryDto categoryDto) {
		Category category = new Category();

		category.setId(categoryDto.getId());
		category.setTitle(categoryDto.getTitle());
		category.setContent(categoryDto.getContent());

		return category;
	}

	public CategoryDto entityToDto(Category category) {
		CategoryDto categoryDto = new CategoryDto();

		categoryDto.setId(category.getId());
		categoryDto.setTitle(category.getTitle());
		categoryDto.setContent(category.getContent());

		return categoryDto;
	}

	private List<CategoryDto> entityToDto(List<Category> categories) {
		List<CategoryDto> categoryDtos = new ArrayList<>();

		categories.forEach((category) -> {
			CategoryDto categoryDto = entityToDto(category);
			categoryDtos.add(categoryDto);
		});

		return categoryDtos;

	}

}
