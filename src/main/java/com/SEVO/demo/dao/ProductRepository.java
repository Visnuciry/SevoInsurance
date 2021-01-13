package com.SEVO.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.SEVO.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public boolean existsByProductName(String productName);
	
	@Modifying
	@Transactional
	@Query("update Product e set e.productDescription=:productDescription where e.productId=:productId")
	public void updateDescription(Integer productId, String productDescription);
}
