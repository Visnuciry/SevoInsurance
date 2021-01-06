package com.SEVO.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SEVO.demo.entity.User;
import com.SEVO.demo.entity.UserDetail;
import com.SEVO.demo.service.UserDetailISerivceImplementation;
import com.SEVO.demo.service.UserServiceImplementation;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {
	@Autowired
	private UserServiceImplementation userservice;

	@Autowired
	private UserDetailISerivceImplementation userDetailservice;

	

	@GetMapping(value = "")
	public String showMainPage(Authentication authentication) {
		String username = authentication.getName();
		User user = userservice.findByUserName(username);
		if (user.isRegistrationStatus()) {
			return "homePageCustomer";
		} else {
			return "redirect:/customer/customerDetailPage";
		}

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
			if (userDetail.getInsuranceNo() != null) {
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


}
