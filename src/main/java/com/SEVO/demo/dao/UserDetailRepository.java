package com.SEVO.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SEVO.demo.entity.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
	
	

}
