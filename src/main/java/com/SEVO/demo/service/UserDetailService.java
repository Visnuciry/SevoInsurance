package com.SEVO.demo.service;

import com.SEVO.demo.entity.User;
import com.SEVO.demo.entity.UserDetail;

public interface UserDetailService {

	public void saveUserDetail (UserDetail userDetail);
	
	public UserDetail findByUser (User user); 
	
}
