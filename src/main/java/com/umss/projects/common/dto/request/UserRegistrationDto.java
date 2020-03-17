package com.umss.projects.common.dto.request;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.umss.projects.domain.Role;

public class UserRegistrationDto {

	@Email(message = "Email should be a valid e-mail address.")
    @Size(min = 5, max = 50, message = "Email should have at least 5 characters and at most 50.")
	private String account;
	@NotBlank(message = "Password can not be null or empty.")
	@Size(min = 6, max = 30, message = "Password should contain between 6 and 30 characters.")
	private String password;
	@NotBlank(message = "Name can not be empty.")
    @Size(max = 20, message = "Name must have at most 30 characters.")
	private String name;
	@NotBlank(message = "Last Name can not be empty.")
    @Size(max = 30, message = "Last Name must have at most 30 characters.")
	private String lastName;
	@NotEmpty(message = "At least one role is required.")
	private Set<Role> roles = new HashSet<>();	
	
	
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
