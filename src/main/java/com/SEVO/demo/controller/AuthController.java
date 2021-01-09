package com.SEVO.demo.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.SEVO.demo.entity.Role;
import com.SEVO.demo.entity.User;
import com.SEVO.demo.service.RoleServiceImplementation;
import com.SEVO.demo.service.UserServiceImplementation;

@Controller
public class AuthController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleServiceImplementation roleservice;

	@Autowired
	private UserServiceImplementation userservice;

	@Autowired
	private UserServiceImplementation userService;

	@GetMapping("/login")
	public String getLoginPage() {
		return "login";

	}

	@GetMapping("/register")
	public String getRegisterPage(Model model) {

		User user = new User();
		model.addAttribute(user);
		return "register";

	}

	@GetMapping("/forgotPassword")
	public String getForgotPasswordPage () {
		return "forgotPassword";
	}
	
	
	@PostMapping("/register")
	public String setUser(@Valid @ModelAttribute("user") User userdata, BindingResult bindingResults) {

		if (bindingResults.hasErrors()) {
			System.out.println(bindingResults);
			return "register";
		} else {
			User tempUser = new User();
			tempUser.setEmailAddress(userdata.getEmailAddress());
			tempUser.setUserName(userdata.getUserName());
			tempUser.setPassword(passwordEncoder.encode(userdata.getPassword()));
			Set<Role> role = new HashSet<Role>();
			role.add(roleservice.findByRollName("CUSTOMER"));
			tempUser.setRolesset(role);

			userservice.saveUser(tempUser);

			return "redirect:/";
		}

	}

	@GetMapping("")
	public String showMainPage(Authentication authentication) {

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		String firstrole = authorities.iterator().next().getAuthority();

		if (firstrole.equals("CUSTOMER")) {
			String username = authentication.getName();
			User user = userService.findByUserName(username);
			if (user.isRegistrationStatus()) {
				return "redirect:/customer";
			} else {
				return "redirect:/customer/customerDetailPage";
			}
		} else {
			return "redirect:/admin";
		}

	}
}
