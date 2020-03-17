package com.umss.projects.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umss.projects.common.dto.request.UserRegistrationDto;
import com.umss.projects.common.dto.response.UserResponseDto;
import com.umss.projects.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	

	@Secured("ROLE_USER")
	@GetMapping
	public ResponseEntity<Iterable<UserResponseDto>> getAllUser() {
		Iterable<UserResponseDto> usersResponse = userService.findAllSortedByName();
		return ResponseEntity.ok(usersResponse);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> findById(@PathVariable("id") final Long userId) {
		UserResponseDto userResponse = userService.findById(userId);
		return ResponseEntity.ok(userResponse);
	}
	
	@PostMapping
	public ResponseEntity<UserResponseDto> save(@Valid @RequestBody final UserRegistrationDto userDto) {
		UserResponseDto persistedUser = userService.save(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(persistedUser);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") final Long userId) {
		userService.delete(userId);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}
}
