package com.afroz.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.afroz.app.entity.User;
import com.afroz.app.jwt.JwtService;
import com.afroz.app.repo.UserRepo;



@RestController
public class UserController {
	
	
	@Autowired
	private UserRepo repo;

	@Autowired
	private JwtService jwtService;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("register")
	public ResponseEntity<User> register(@RequestBody User user) {
		
		User existingUser=repo.findByUsername(user.getUsername());
		if(existingUser != null) return new ResponseEntity<User>(existingUser,HttpStatus.TOO_MANY_REQUESTS);
		
		user.setPassword(encoder.encode(user.getPassword()));
		repo.save(user);
		
		return new ResponseEntity<User>(repo.findByUsername(user.getUsername()),HttpStatus.OK);
	}

	@PostMapping("login")
	public String login(@RequestBody User user){

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		if(authentication.isAuthenticated())
			return jwtService.generateToken(user.getUsername());
		else
			return "Login Failed";

	}
	
}
