package com.SEVO.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SEVO.demo.entity.UserProducts;

public interface UserProductRepository extends JpaRepository<UserProducts, Integer> {
	
	

}
