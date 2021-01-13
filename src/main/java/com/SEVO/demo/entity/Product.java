package com.SEVO.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.SEVO.demo.validator.UniqueProductName;


@Entity

@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;
	
	
	@NotBlank(message = "Product Name could not be null")
	@UniqueProductName
	@Column(name = "product_name")
	private String productName;
	
	@NotBlank
	@Column(name = "product_description",columnDefinition = "text")
	private String productDescription;
	
	@OneToMany(mappedBy = "userproductId", cascade = CascadeType.DETACH)
	private List<UserProducts> userproducts;

	public Product() {
		
	}
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public List<UserProducts> getUserproducts() {
		return userproducts;
	}

	public void setUserproducts(List<UserProducts> userproducts) {
		this.userproducts = userproducts;
	}

	
}
