package com.umss.projects.service;

import org.springframework.stereotype.Service;

import com.umss.projects.domain.User;
import com.umss.projects.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	
	public UserService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

}
