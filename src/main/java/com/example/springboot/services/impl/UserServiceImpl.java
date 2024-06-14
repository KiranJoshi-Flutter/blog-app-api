package com.example.springboot.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entities.User;
import com.example.springboot.exceptions.ResourceNotFoundException;
import com.example.springboot.payloads.UserDto;
import com.example.springboot.repositories.UserRepo;
import com.example.springboot.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = userRepo.save(dtoToEntity(userDto));
		return entityToDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User existingUser = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

//		User existingUser = userRepo.findById(userId)
//				.orElseThrow(() -> new RuntimeException("This is a runtime exception"));

		existingUser.setName(userDto.getName());
		existingUser.setEmail(userDto.getEmail());
		existingUser.setPassword(userDto.getPassword());
		existingUser.setAbout(userDto.getAbout());

		User updatedUser = userRepo.save(existingUser);

		return entityToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		return entityToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = userRepo.findAll();
		return entityToDto(users);
	}

	@Override
	public void deleteUser(Integer userId) {

		userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		userRepo.deleteById(userId);
	}

	private User dtoToEntity(UserDto userDto) {
		User user = new User();

		user.setId(userDto.getId());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());

		return user;
	}

	private UserDto entityToDto(User user) {
		UserDto userDto = new UserDto();

		userDto.setId(user.getId());
		userDto.setAbout(user.getAbout());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());

		return userDto;
	}

	private List<UserDto> entityToDto(List<User> users) {
		List<UserDto> userDtos = new ArrayList<>();

		for (User user : users) {
			UserDto userDto = entityToDto(user);
			userDtos.add(userDto);
		}

		return userDtos;
	}

}
