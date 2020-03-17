package com.umss.projects.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.umss.projects.common.dto.request.UserRegistrationDto;
import com.umss.projects.common.dto.response.UserResponseDto;
import com.umss.projects.domain.User;
import com.umss.projects.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	
	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
		
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}
	

	/**
	 * It returns all registered user in the system ordered by name.
	 * @return Collection of Users
	 */
	public Iterable<UserResponseDto> findAllSortedByName() {
		List<UserResponseDto> allUsersResponse = userRepository.findAll()
										.stream()
										.sorted(Comparator.comparing(User::getName))
										.map(usr -> {
											UserResponseDto response = modelMapper.map(usr, UserResponseDto.class);
											return response;
										})
										.collect(Collectors.toList());
		
		return allUsersResponse;
	}
	
	/**
	 * It returns the user with the specified ID
	 * @param userId is the user identifier
	 * @return User with the specified ID
	 */
	public UserResponseDto findById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		
		if(!user.isPresent() ) {
			String message = "User with ID=%s could not be found.";
			logger.error("Error ocurred: " + String.format(message, userId));
			throw new RuntimeException(String.format(message, userId));
		}
		UserResponseDto foundUser = modelMapper.map(user.get(), UserResponseDto.class);
		
		return foundUser;
	}
	
	/**
	 * It saves a new user to the database
	 * @param user to be stored
	 * @return User stored in the DB.
	 */
	public UserResponseDto save(UserRegistrationDto userDto) {
		User converted = modelMapper.map(userDto, User.class);

        User persistedUser = userRepository.save(converted);
        UserResponseDto userResponse = modelMapper.map(persistedUser, UserResponseDto.class);
        
        return userResponse;
	}
	
	/**
	 * It removes a specified user from DB.
	 * @param userId the user identifier
	 */
	public void delete(Long userId) {
		userRepository.deleteById(userId);
	}

}
