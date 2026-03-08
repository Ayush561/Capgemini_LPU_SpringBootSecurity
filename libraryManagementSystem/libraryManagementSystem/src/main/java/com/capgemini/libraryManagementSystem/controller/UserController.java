package com.capgemini.libraryManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.libraryManagementSystem.entity.User;
import com.capgemini.libraryManagementSystem.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository repo;

	@GetMapping
	public List<User> getUsers() {
		return repo.findAll();
	}

	@PostMapping
	public User addUser(@RequestBody User u) {
		return repo.save(u);
	}

}
