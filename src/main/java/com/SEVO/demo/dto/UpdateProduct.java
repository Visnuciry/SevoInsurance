package com.SEVO.demo.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateProduct {

	@NotNull
	private Integer productId;
	
	@NotBlank
	@Column(name = "product_description",columnDefinition = "text")
	private String productDescription;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}





}
