package com.umss.projects.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umss.projects.persistence.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
