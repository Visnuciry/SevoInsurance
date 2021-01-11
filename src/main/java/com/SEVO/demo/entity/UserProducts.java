package com.SEVO.demo.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity

@Table(name = "userproduct")
public class UserProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "userproduct_id")
	private Product userproductId;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "owner_id")
	private User ownerId;
	
	@NotBlank
	@Column(name = "productfee")
	private String productfee;
	
	@NotNull
	@Column(name = "productvalidfrom")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate productvalidfrom;
	
	@NotNull
	@Column(name = "productvalidto")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate productvalidto;
	

	
	public UserProducts() {

	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Product getUserproductId() {
		return userproductId;
	}



	public void setUserproductId(Product userproductId) {
		this.userproductId = userproductId;
	}



	public User getOwnerId() {
		return ownerId;
	}



	public void setOwnerId(User ownerId) {
		this.ownerId = ownerId;
	}



	public String getProductfee() {
		return productfee;
	}



	public void setProductfee(String productfee) {
		this.productfee = productfee;
	}



	public LocalDate getProductvalidfrom() {
		return productvalidfrom;
	}



	public void setProductvalidfrom(LocalDate productvalidfrom) {
		this.productvalidfrom = productvalidfrom;
	}



	public LocalDate getProductvalidto() {
		return productvalidto;
	}



	public void setProductvalidto(LocalDate productvalidto) {
		this.productvalidto = productvalidto;
	}




	
}
