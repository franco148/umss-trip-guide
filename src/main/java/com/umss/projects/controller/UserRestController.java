package com.umss.projects.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@GetMapping
	public String helloWorld() {
		return "Hello world from my first spring boot project";
	}
}
