package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String userNo) throws UsernameNotFoundException {
		
//		return new UserDetails().
		
		
		return null;
	}

}
