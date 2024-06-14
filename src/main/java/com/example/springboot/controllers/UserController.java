package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.payloads.ApiResponse;
import com.example.springboot.payloads.UserDto;
import com.example.springboot.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		UserDto createdUserDto = userService.createUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("id") Integer userId) {
		UserDto updatedUserDto = userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUserDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer userId) {
		UserDto userDto = userService.getUserById(userId);
		return userDto != null ? ResponseEntity.ok(userDto) : ResponseEntity.notFound().build();
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> usersDto = userService.getAllUsers();
		return ResponseEntity.ok(usersDto);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer userId) {
		userService.deleteUser(userId);
//		return ResponseEntity.ok(Map.of("message", "Resource deleted successfully"));
		return ResponseEntity.ok(new ApiResponse("Resource deleted successfully", true));
	}

}
