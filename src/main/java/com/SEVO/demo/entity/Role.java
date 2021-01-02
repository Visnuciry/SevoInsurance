package com.SEVO.demo.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "rolls")
public class Role {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "rolls_id")
	private int id;
	@Column (name = "roll_name")
	private String rollName;
	
	
	@ManyToMany (mappedBy = "rolesSet")
	private Collection<User> user;
	
	//Constructor
	public Role() {
		
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}


	public String getRollName() {
		return rollName;
	}



	public void setRollName(String rollName) {
		this.rollName = rollName;
	}



	public Collection<User> getUser() {
		return user;
	}
	public void setUser(Collection<User> user) {
		this.user = user;
	}
	
	
}
