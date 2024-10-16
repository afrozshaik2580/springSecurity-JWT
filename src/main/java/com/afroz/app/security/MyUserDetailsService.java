package com.afroz.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.afroz.app.entity.User;
import com.afroz.app.repo.UserRepo;


@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		User user=userRepo.findById(username).get();
		
		if(user==null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new UserDetailsImpl(user);
	}

}
