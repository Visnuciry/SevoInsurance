package com.SEVO.demo.data;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.SEVO.demo.dao.RolesRepository;
import com.SEVO.demo.entity.Role;
import com.SEVO.demo.entity.User;
import com.SEVO.demo.service.UserServiceImplementation;

@Component
public class InitData {

	@Autowired
	private UserServiceImplementation userRepository;
	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener
	public void loadData(ApplicationReadyEvent event) {

		if (rolesRepository.count() == 0) {
			
			Role adminrole = new Role();
			adminrole.setRollName("ADMIN");
			rolesRepository.save(adminrole);
			
			User adminUser = new User();
			adminUser.setUserName("Admin");
			adminUser.setEmailAddress("Sevo@hotmail.ch");
			adminUser.setPassword(passwordEncoder.encode("Sevo1234"));
			Set<Role> role = new HashSet <Role>();
			role.add(adminrole);
			adminUser.setRolesset(role);
			userRepository.saveUser(adminUser);
		
			
			
			Role adminrole1 = new Role();
			adminrole1.setRollName("CUSTOMER");
			rolesRepository.save(adminrole1);
			
			
			
			

		}
	}
}
