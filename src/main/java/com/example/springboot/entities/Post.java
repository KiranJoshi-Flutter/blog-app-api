package com.example.springboot.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	@Column(length = 100, nullable = false)
	private String title;

	@Column(length = 10000)
	private String content;

	private String imageName;
	private Date addedDate;

	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
//	@JsonBackReference
	@JsonIgnoreProperties
	private Category category;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
//	@JsonManagedReference
	@JsonBackReference
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
	private Set<Comment> comments = new HashSet<>();

}
