package com.example.thymeleaf.repository;

import com.example.thymeleaf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

	UserDetails findByEmail(String login);

	Optional<User> getByEmail(String email);
}