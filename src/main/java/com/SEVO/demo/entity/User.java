package com.SEVO.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.SEVO.demo.validator.EmailValidator;
import com.SEVO.demo.validator.UniqueEmailadress;
import com.SEVO.demo.validator.UniqueUserName;

@Entity

@Table(name = "users")


public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	@Column(name = "username")
	@NotBlank
	@UniqueUserName
	private String userName;
	@UniqueEmailadress
	@EmailValidator
	@NotBlank
	@Column(name = "email_address")
	private String emailAddress;
	@Column(name = "password")
	@NotBlank
	private String password;
	@Column (name = "registrationstatus")
	private boolean registrationStatus;
	

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "userrolls", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "roll_id", referencedColumnName = "rolls_id"))
	private Set<Role> rolesSet;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")

	private UserDetail userDetail;

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRolesset() {
		return rolesSet;
	}

	public void setRolesset(Set<Role> rolesset) {
		this.rolesSet = rolesset;
	}

	public Set<Role> getRolesSet() {
		return rolesSet;
	}

	public void setRolesSet(Set<Role> rolesSet) {
		this.rolesSet = rolesSet;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public boolean isRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(boolean registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	


}
