package com.example.springboot.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	private Integer id;

	@NotBlank
	@Size(min = 4, message = "Minimun size of category title is 4")
	private String title;

	@NotBlank
	@Size(min = 10)
	private String content;

}
