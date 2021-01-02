package com.SEVO.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SEVO.demo.dao.UserRepository;
import com.SEVO.demo.entity.User;

@Service
public class UserServiceImplementation implements UserService{

	private final UserRepository userRepository;
	@Autowired
	public UserServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	@Override
	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	
}
