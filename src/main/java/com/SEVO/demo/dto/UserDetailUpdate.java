package com.SEVO.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailUpdate {
	
	private int id;
	
	
	@NotBlank
	private String street;

	@NotNull
	private Integer housenr;

	@NotNull
	private Integer postcode;

	@NotBlank
	private String city;

	@NotBlank
	@Size(min = 10, max = 10)
	private String phonenr;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getHousenr() {
		return housenr;
	}

	public void setHousenr(Integer housenr) {
		this.housenr = housenr;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhonenr() {
		return phonenr;
	}

	public void setPhonenr(String phonenr) {
		this.phonenr = phonenr;
	}

	
	
}
