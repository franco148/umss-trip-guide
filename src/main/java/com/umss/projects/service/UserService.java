package com.umss.projects.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.umss.projects.domain.User;
import com.umss.projects.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	
	public UserService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	

	/**
	 * It returns all registered user in the system ordered by name.
	 * @return Collection of Users
	 */
	public Iterable<User> findAllSortedByName() {
		List<User> allUsersResponse = userRepository.findAll()
										.stream()
										.sorted(Comparator.comparing(User::getName))
										.collect(Collectors.toList());
		
		return allUsersResponse;
	}
	
	/**
	 * It returns the user with the specified ID
	 * @param userId is the user identifier
	 * @return User with the specified ID
	 */
	public User findById(Long userId) {
		Optional<User> foundUser = userRepository.findById(userId);
		
		if(!foundUser.isPresent() ) {
			String message = "User with ID=%s could not be found.";
			logger.error("Error ocurred: " + String.format(message, userId));
			throw new RuntimeException(String.format(message, userId));
		}
		
		return foundUser.get();
	}
	
	/**
	 * It saves a new user to the database
	 * @param user to be stored
	 * @return User stored in the DB.
	 */
	public User save(User user) {
		return userRepository.save(user);
	}
	
	/**
	 * It removes a specified user from DB.
	 * @param userId the user identifier
	 */
	public void delete(Long userId) {
		userRepository.deleteById(userId);
	}

}
