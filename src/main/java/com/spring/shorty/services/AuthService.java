package com.spring.shorty.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shorty.entities.UserEntity;
import com.spring.shorty.exceptions.UnauthorizedException;
import com.spring.shorty.repository.UserRepository;
import com.spring.shorty.responses.LoginResponse;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;

	public LoginResponse login(String email, String password) throws Exception {

		UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
		
		if(!user.getPassword().equals(password))
			throw new UnauthorizedException("Invalid Credentials");
		
		
		return new LoginResponse(user.getId(), user.getUsername(), user.getEmail());
	}

}
