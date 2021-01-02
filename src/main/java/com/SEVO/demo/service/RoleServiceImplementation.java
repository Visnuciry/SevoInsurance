package com.SEVO.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SEVO.demo.dao.RolesRepository;
import com.SEVO.demo.entity.Role;

@Service
public class RoleServiceImplementation implements RolesService {

	private final RolesRepository rolesrepository;
	@Autowired
		public RoleServiceImplementation(RolesRepository rolesrepository) {
		this.rolesrepository = rolesrepository;
	}
	
	
	@Override
	public Role findByRollName(String rollname) {
		
		return rolesrepository.findByRollName(rollname);
	}

	
	

	
}
