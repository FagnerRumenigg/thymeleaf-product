package com.example.thymeleaf.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.example.thymeleaf.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret")
	private String secret;

	public String generateToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			return JWT.create()
					.withIssuer("entertainMe-api")
					.withSubject(user.getEmail())
					.withExpiresAt(generateExpirationDate())
					.sign(algorithm);
		}catch(JWTCreationException jwtCreationException) {
			throw new RuntimeException("Error while generatin token", jwtCreationException);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			return JWT.require(algorithm)
					.withIssuer("entertainMe-api")
					.build()
					.verify(token)
					.getSubject();
			
		}catch(JWTVerificationException jwtVerificationException) {
			return "";
		}
	}
	
	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00"));
	}
}