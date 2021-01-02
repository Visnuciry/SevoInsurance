package com.SEVO.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SEVO.demo.entity.Role;

public interface RolesRepository extends JpaRepository<Role, Integer> {
	
	public Role findByRollName (String rollname);
	
}
