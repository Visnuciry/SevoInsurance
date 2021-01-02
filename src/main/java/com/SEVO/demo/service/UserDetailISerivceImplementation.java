package com.SEVO.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SEVO.demo.dao.UserDetailRepository;
import com.SEVO.demo.entity.UserDetail;

@Service
public class UserDetailISerivceImplementation implements UserDetailService{

	private final UserDetailRepository userDetailRepository;
	
	@Autowired
	public UserDetailISerivceImplementation(UserDetailRepository userDetailRepository) {
		this.userDetailRepository = userDetailRepository;
	}


	@Override
	public void saveUserDetail(UserDetail userDetail) {
		userDetailRepository.save(userDetail);
		
	}
	
	

}
