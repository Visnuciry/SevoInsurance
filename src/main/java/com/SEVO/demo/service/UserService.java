package com.SEVO.demo.service;

import java.util.List;

import com.SEVO.demo.entity.User;

public interface UserService {

	public List<User> findAll ();

	public void saveUser (User user);
	
	public User findByUserName (String username);
}
