package com.SEVO.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.SEVO.demo.dao.ProductRepository;
import com.SEVO.demo.entity.Product;

@Controller
@RequestMapping(path = "/customer/portfolio")
public class PortfolioController {

	@Autowired
	private ProductRepository productRepository;
	@GetMapping("")
	public ModelAndView ListPortfolioProducts() {
		ModelAndView mav = new ModelAndView("customer/portfolioList");
		List<Product> products = productRepository.findAll();
		mav.addObject("products", products);
		return mav;
	}
}
