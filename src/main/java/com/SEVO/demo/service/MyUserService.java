package com.SEVO.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.SEVO.demo.dao.UserRepository;
import com.SEVO.demo.entity.User;

public class MyUserService implements UserDetailsService {

	@Autowired
	private UserRepository userrepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userrepository.findByUserName(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		
		return new MyUserServiceImplementation(user);
	}

}
