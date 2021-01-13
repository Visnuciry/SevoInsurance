package com.SEVO.demo.entity;

import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

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
	@Column(name = "email_address")
	private String emailAddress;
	
	@Size(min = 8, message = "Password must contain atleast 8 character")
	@Column(name = "password")
	@NotBlank
	private String password;
	@Column (name = "registrationstatus")
	private boolean registrationStatus;
	

	@Column(name = "deleteFlag")
	@Value("${false}")
	private boolean deleteFlag;
	
	@OneToMany(mappedBy = "ownerId", cascade = CascadeType.REMOVE)
	private List<UserProducts> userproducts;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "userrolls", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "roll_id", referencedColumnName = "rolls_id"))
	private Set<Role> rolesSet;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")

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

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public List<UserProducts> getUserproducts() {
		return userproducts;
	}

	public void setUserproducts(List<UserProducts> userproducts) {
		this.userproducts = userproducts;
	}

	


}
