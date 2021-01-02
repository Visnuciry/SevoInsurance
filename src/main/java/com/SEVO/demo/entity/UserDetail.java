package com.SEVO.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity

@Table (name = "user_details")

public class UserDetail {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private int id;
	@Column (name = "last_name")
	private String lastName;
	@Column (name = "sur_name")
	private String surName;
	@Column (name = "date_of_birth")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	@Column (name = "insurance_no")
	private int insuranceNo;
	@Column (name = "gender")
	private String gender;
	
	
	@OneToOne (fetch = FetchType.LAZY,optional = false)
	@JoinColumn (name = "user_id", nullable = false, updatable = true)
	
		
	private User user;


	public UserDetail() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getSurName() {
		return surName;
	}


	public void setSurName(String surName) {
		this.surName = surName;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public int getInsuranceNo() {
		return insuranceNo;
	}


	public void setInsuranceNo(int insuranceNo) {
		this.insuranceNo = insuranceNo;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
		

	
}
