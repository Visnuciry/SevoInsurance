package com.SEVO.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SEVO.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserName (String username);
	public boolean existsByEmailAddress (String emailadress);
	
	
}
