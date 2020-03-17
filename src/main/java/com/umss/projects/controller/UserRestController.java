package com.umss.projects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umss.projects.domain.User;
import com.umss.projects.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	

	@GetMapping
	public ResponseEntity<Iterable<User>> getAllUser() {
		Iterable<User> usersResponse = userService.findAll();
		return ResponseEntity.ok(usersResponse);
	}
	
	
}
