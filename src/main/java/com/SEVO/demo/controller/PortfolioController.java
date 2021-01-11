package com.SEVO.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.SEVO.demo.dao.UserProductRepository;
import com.SEVO.demo.entity.User;
import com.SEVO.demo.entity.UserProducts;
import com.SEVO.demo.service.UserServiceImplementation;

@Controller
@RequestMapping(path = "/customer/portfolio")
public class PortfolioController {

	@Autowired
	private UserServiceImplementation userservice;

	@Autowired
	private UserProductRepository userProductRepository;

	@GetMapping("")
	public ModelAndView ListPortfolioProducts(Authentication authentication) {
		User currentUser = userservice.findByUserName(authentication.getName());

		ModelAndView mav = new ModelAndView("customer/portfolioList");
		List<UserProducts> products = userProductRepository.findAllByOwnerId(currentUser);
		mav.addObject("products", products);

		return mav;
	}
	
	@GetMapping(value = "/{productid}")
	public ModelAndView showProduct(@PathVariable @NotNull @DecimalMin("0") Integer productid, Authentication authentication) {
		User currentUser = userservice.findByUserName(authentication.getName());
		Optional<UserProducts> userProducts = userProductRepository.findById(productid);
		System.out.println(userProducts.get().getOwnerId().getUserName());
		System.out.println(currentUser.getUserName());
		if (userProducts.get().getOwnerId().getId()==currentUser.getId()) {
			ModelAndView mav = new ModelAndView("customer/Portfolio");
			mav.addObject("product", userProducts.get());
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("Error/403");
			
			return mav;
		}
	
	}
}
