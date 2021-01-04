package com.SEVO.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.SEVO.demo.entity.User;
import com.SEVO.demo.service.UserServiceImplementation;

@Controller
public class HomeController {

	@Autowired
	private UserServiceImplementation userService;

	@GetMapping(value = "/")
	public String showMainPage(Authentication authentication) {
		String username = authentication.getName();
		User user = userService.findByUserName(username);
		if (user.isRegistrationStatus()) {
			return "homePageCustomer";
		} else {
			return "redirect:/customerDetailPage";
		}

	}

}
