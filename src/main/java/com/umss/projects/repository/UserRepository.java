package com.umss.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umss.projects.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
