package com.application.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.portal.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
