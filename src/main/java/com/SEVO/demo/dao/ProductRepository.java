package com.SEVO.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SEVO.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public boolean findByProductName(String productName);
}
