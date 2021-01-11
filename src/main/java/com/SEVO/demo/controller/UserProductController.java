package com.SEVO.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		mav.addObject("formStatus", false);

		return mav;
	}
	
	@PostMapping("createUserProduct")
	public String createUserProduct(@Valid @ModelAttribute("userproducts") UserProducts userproducts, BindingResult bindingResults, Model model) {
		
		
		
		if (bindingResults.hasErrors()) {
			List<Product> products = productRepository.findAll();
			model.addAttribute("products", products);
			if (userproducts.getId()==0) {
				model.addAttribute("formStatus",false);
			} else {
				model.addAttribute("formStatus",true);
			}
			
			model.addAttribute("customerName",userproducts.getOwnerId());
			System.out.println(bindingResults);
			return "admin/CreateUserProduct";
			
		} else {
			UserProducts tempUserProduct = new UserProducts();
			tempUserProduct.setId(userproducts.getId());
			tempUserProduct.setOwnerId(userproducts.getOwnerId());
			tempUserProduct.setProductfee(userproducts.getProductfee());
			tempUserProduct.setProductvalidfrom(userproducts.getProductvalidfrom());
			tempUserProduct.setProductvalidto(userproducts.getProductvalidto());
			tempUserProduct.setUserproductId(userproducts.getUserproductId());
			userProductRepository.saveAndFlush(tempUserProduct);
			return "redirect:/admin/usersproduct";

		}

	}
	@GetMapping(value = "/user/{userId}")
	public ModelAndView userProductsForUsers(@PathVariable @NotNull @DecimalMin("0") Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		List<UserProducts> userProducts = userProductRepository.findAllByOwnerId(user.get());
		ModelAndView mav = new ModelAndView("admin/customerProductList");
		mav.addObject("userProducts", userProducts);
		return mav;
	}

	@GetMapping(value = "/product/{userProductId}")
	public ModelAndView customerProduct(@PathVariable @NotNull @DecimalMin("0") Integer userProductId) {
		Optional<UserProducts> userProduct = userProductRepository.findById(userProductId);
		ModelAndView mav = new ModelAndView("admin/CreateUserProduct");
		List<Product> products = productRepository.findAll();
		mav.addObject("userproducts", userProduct);
		mav.addObject("products", products);
		mav.addObject("customerName", userProduct.get().getOwnerId());
		mav.addObject("formStatus", true);
		return mav;
	}

	@PostMapping("userProductDelete")
	public ResponseEntity<?> softDeleteUser(@RequestBody Integer userProductId) {
		userProductRepository.deleteById(userProductId);
		System.out.println(userProductId);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
