package com.example.thymeleaf.service;

import com.example.thymeleaf.dto.RegisterDto;
import com.example.thymeleaf.exception.AlreadyExistsException;
import com.example.thymeleaf.model.User;
import com.example.thymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthorizationService implements UserDetailsService {

	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findByLogin(username);
	}

	public UserDetails findByLogin(String userName) throws UsernameNotFoundException {
        return repository.findByEmail(userName);
	}

	public void save(RegisterDto registerUser) throws AlreadyExistsException {
		if (findByLogin(registerUser.getEmail()) != null) {
			throw new AlreadyExistsException("Already exist an user with this e-mail.");
		}

		String encryptedPassword = new BCryptPasswordEncoder().encode(registerUser.getPassword());
		User newUser = new User(registerUser.getUsername(), registerUser.getEmail(), encryptedPassword, registerUser.getRole());

		repository.save(newUser);
	}

	public static boolean isEmailInvalid(String email) {
		String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);

		return !matcher.matches();
	}

	public static boolean isPasswordInvalid(String password){
		String passwordPattern =
				"^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";

		Pattern pattern = Pattern.compile(passwordPattern);
		Matcher matcher = pattern.matcher(password);
		return !matcher.matches();
	}

}
