package com.SEVO.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SEVO.demo.entity.User;
import com.SEVO.demo.entity.UserProducts;

public interface UserProductRepository extends JpaRepository<UserProducts, Integer> {
	 
	public List<UserProducts> findAllByOwnerId (User user);

}
