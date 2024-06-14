package com.example.springboot.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto categoryDto;
	private UserDto userDto;

	private Set<CommentDto> commentDtos = new HashSet<>();

}
