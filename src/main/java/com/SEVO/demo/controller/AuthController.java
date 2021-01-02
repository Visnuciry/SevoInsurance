package com.SEVO.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.PostRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SEVO.demo.entity.Role;
import com.SEVO.demo.entity.User;
import com.SEVO.demo.entity.UserDetail;
import com.SEVO.demo.service.RoleServiceImplementation;
import com.SEVO.demo.service.UserDetailISerivceImplementation;
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
	private UserDetailISerivceImplementation userDetailservice;

	@GetMapping("/login")
	public String getLoginPage() {
		return "login";

	}

	
	@GetMapping("/register") 
	public String getRegisterPage (Model model){

		User user = new User();
		model.addAttribute(user);
		return "register";

	}

	@GetMapping("/customerDetailPage")
	public String getMainCustomerPage(Model model) {
		UserDetail userDetail = new UserDetail();

		model.addAttribute(userDetail);
		return "customerDetailPage";
	}

	@PostMapping("/customerDetailPage")
	public String setCustomerDetail(@ModelAttribute("userDetail") UserDetail userDetail,
			Authentication authentication) {

		User currentUser = userservice.findByUserName(authentication.getName());

		UserDetail tempUserDetail = new UserDetail();
		tempUserDetail.setDateOfBirth(userDetail.getDateOfBirth());
		tempUserDetail.setGender(userDetail.getGender());
		tempUserDetail.setLastName(userDetail.getLastName());
		tempUserDetail.setSurName(userDetail.getSurName());
		if (userDetail.getInsuranceNo() != 0) {
			tempUserDetail.setInsuranceNo(userDetail.getInsuranceNo());
		}
		tempUserDetail.setUser(currentUser);
		userDetailservice.saveUserDetail(tempUserDetail);

		return "redirect:/";
	}

//	@GetMapping("/homePageCustomer")
//	public String getHomePageCustomer ()
//	{
//		return "homePageCustomer";
//	}

	@PostMapping("/register")
	public String setUser(@ModelAttribute("user") User userdata) {

		User tempUser = new User();
		tempUser.setEmailAddress(userdata.getEmailAddress());
		tempUser.setUserName(userdata.getUserName());
		tempUser.setPassword(passwordEncoder.encode(userdata.getPassword()));
		Set<Role> role = new HashSet<Role>();
		role.add(roleservice.findByRollName("CUSTOMER"));
		tempUser.setRolesset(role);

		// Safe to database
		userservice.saveUser(tempUser);

		return "login";
	}

	@GetMapping("/404")
	public String getErrorPage() {
		return "404";

	}

}
