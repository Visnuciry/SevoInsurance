package com.SEVO.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.SEVO.demo.dao.ProductRepository;
import com.SEVO.demo.dao.UserProductRepository;
import com.SEVO.demo.dao.UserRepository;
import com.SEVO.demo.entity.Product;
import com.SEVO.demo.entity.User;
import com.SEVO.demo.entity.UserProducts;

@Controller
@RequestMapping(path = "/admin/usersproduct")
public class UserProductController {

	@Autowired
	private UserProductRepository userProductRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("")
	public ModelAndView ListUsers() {
		ModelAndView mav = new ModelAndView("admin/UserProductsList");
		List<User> users = userRepository.findAll();
		mav.addObject("users", users);
		return mav;
	}

	@RequestMapping(value = "/{userid}", method = RequestMethod.GET)
	public ModelAndView createUserProduct(@PathVariable @NotNull @DecimalMin("0") Integer userid) {
		Optional<User> user = userRepository.findById(userid);
		List<Product> products = productRepository.findAll();
		ModelAndView mav = new ModelAndView("admin/CreateUserProduct");
		UserProducts userproducts = new UserProducts();
		userproducts.setOwnerId(user.get());
		mav.addObject("userproducts", userproducts);
		mav.addObject("products",products);
		mav.addObject("customerName", user.get());

		return mav;
	}
	
	@PostMapping("createUserProduct")
	public String createUserProduct(@Valid @ModelAttribute("userproducts") UserProducts userproducts, BindingResult bindingResults) {

		if (bindingResults.hasErrors()) {
			return "admin/createProduct";
		} else {
			userProductRepository.saveAndFlush(userproducts);

			return "redirect:/admin/usersproduct";

		}

	}
}
