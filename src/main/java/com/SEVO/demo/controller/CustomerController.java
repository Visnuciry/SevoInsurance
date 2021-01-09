package com.SEVO.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SEVO.demo.dto.UserDetailUpdate;
import com.SEVO.demo.entity.User;
import com.SEVO.demo.entity.UserDetail;
import com.SEVO.demo.service.UserDetailISerivceImplementation;
import com.SEVO.demo.service.UserServiceImplementation;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserServiceImplementation userservice;

	@Autowired
	private UserDetailISerivceImplementation userDetailservice;

	@GetMapping("")
	public String showMainPage(Authentication authentication) {
		String username = authentication.getName();
		User user = userservice.findByUserName(username);
		if (user.isRegistrationStatus()) {
			return "customer/HomePageCustomer";
		} else {
			return "redirect:/customer/customerDetailPage";
		}

	}

	@GetMapping("/customerDetailPage")
	public String getMainCustomerPage(Model model) {
		UserDetail userDetail = new UserDetail();
		model.addAttribute(userDetail);
		return "customer/customerDetailPage";
	}
	
	
	@GetMapping("/Portfolio")
	public String getPortfolio() {
		return "customer/Portfolio";
	}
	
	@GetMapping("/Contact")
	public String getContactPage() {
		return "customer/Contact";
	}

	

	@PostMapping("/customerDetailPage")
	public String setCustomerDetail(@Valid @ModelAttribute("userDetail") UserDetail userDetail,
			BindingResult bindingResults, Authentication authentication) {
		if (bindingResults.hasErrors()) {
			return "customer/customerDetailPage";
		} else {
			User currentUser = userservice.findByUserName(authentication.getName());

			UserDetail tempUserDetail = new UserDetail();
			tempUserDetail.setDateOfBirth(userDetail.getDateOfBirth());
			tempUserDetail.setGender(userDetail.getGender());
			tempUserDetail.setLastName(userDetail.getLastName());
			tempUserDetail.setSurName(userDetail.getSurName());
			if (userDetail.getInsuranceNo() != null) {
				tempUserDetail.setInsuranceNo(userDetail.getInsuranceNo());
			}
			tempUserDetail.setStreet(userDetail.getStreet());
			tempUserDetail.setHousenr(userDetail.getHousenr());
			tempUserDetail.setPostcode(userDetail.getPostcode());
			tempUserDetail.setCity(userDetail.getCity());
			tempUserDetail.setPhonenr(userDetail.getPhonenr());
			tempUserDetail.setSsnNo(passwordEncoder.encode(userDetail.getSsnNo()));
			currentUser.setRegistrationStatus(true);
			tempUserDetail.setUser(currentUser);
			userDetailservice.saveUserDetail(tempUserDetail);
			System.out.println("\n\\n\\nhello");
			return "redirect:/";

		}

	}

	@GetMapping("/profilePage")
	public String getProfilePage(Authentication authentication, Model model) {
		String username = authentication.getName();

		User user = userservice.findByUserName(username);
		UserDetail userDetail = userDetailservice.findByUser(user);
		model.addAttribute(userDetail);
		return "customer/profilePage";

	}
	@PostMapping("/profilePage")
	public String updateCustomerDetail(@Valid @ModelAttribute("userDetail") UserDetailUpdate userDetail,
			BindingResult bindingResults, Authentication authentication) {
		
		if (bindingResults.hasErrors()) {
			
			return "redirect:/customer/profilePage";
			
		} else {
			String username = authentication.getName();

			User user = userservice.findByUserName(username);
			UserDetail tempUserDetail = userDetailservice.findByUser(user);

			tempUserDetail.setId(userDetail.getId());
			tempUserDetail.setStreet(userDetail.getStreet());
			tempUserDetail.setHousenr(userDetail.getHousenr());
			tempUserDetail.setPostcode(userDetail.getPostcode());
			tempUserDetail.setCity(userDetail.getCity());
			tempUserDetail.setPhonenr(userDetail.getPhonenr());

			userDetailservice.saveUserDetail(tempUserDetail);
			return "redirect:/";
			
		}


	}

}
