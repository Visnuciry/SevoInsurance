package com.SEVO.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.PostRemove;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	public String getRegisterPage(Model model) {

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
	public String setCustomerDetail(@Valid @ModelAttribute("userDetail") UserDetail userDetail,
			 BindingResult bindingResults, Authentication authentication) {
		if (bindingResults.hasErrors()) {
			return "customerDetailPage";
		} else {
			User currentUser = userservice.findByUserName(authentication.getName());

			UserDetail tempUserDetail = new UserDetail();
			tempUserDetail.setDateOfBirth(userDetail.getDateOfBirth());
			tempUserDetail.setGender(userDetail.getGender());
			tempUserDetail.setLastName(userDetail.getLastName());
			tempUserDetail.setSurName(userDetail.getSurName());
			if (userDetail.getInsuranceNo() != 0) {
				tempUserDetail.setInsuranceNo(userDetail.getInsuranceNo());
			}
			tempUserDetail.setStreet(userDetail.getStreet());
			tempUserDetail.setHousenr(userDetail.getHousenr());
			tempUserDetail.setPostcode(userDetail.getPostcode());
			tempUserDetail.setCity(userDetail.getCity());
			tempUserDetail.setPhonenr(userDetail.getPhonenr());
			currentUser.setRegistrationStatus(true);
			tempUserDetail.setUser(currentUser);
			userDetailservice.saveUserDetail(tempUserDetail);
			System.out.println("\n\\n\\nhello");
			return "redirect:/";

		}

	}

//	@GetMapping("/homePageCustomer")
//	public String getHomePageCustomer ()
//	{
//		return "homePageCustomer";
//	}

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

	

}
