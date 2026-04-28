package com.spring.shorty.services;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.shorty.dto.ApiResponse;
import com.spring.shorty.dto.UserDTO;
import com.spring.shorty.entities.UserEntity;
import com.spring.shorty.exceptions.ConflictException;
import com.spring.shorty.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserEntity getUser(Long id) throws Exception{
		
		return userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	}
	
	public ApiResponse saveUser(UserDTO dto) throws Exception {
		
		if (dto.getUsername() == null || dto.getUsername().isEmpty()) {
	        throw new BadRequestException("Username is required");
	    }
		
		if(dto.getEmail() == null || dto.getEmail().isEmpty()) {
			throw new BadRequestException("Email is required");
		}

	    if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
	        throw new ConflictException("Email already exists");
	    }

	    UserEntity user = new UserEntity();
	    user.setUsername(dto.getUsername());
	    user.setEmail(dto.getEmail());
	    user.setPassword(passwordEncoder.encode(dto.getPassword()));

	    userRepository.save(user);

	    return new ApiResponse("User created sucefully!", 200);
	}
}
