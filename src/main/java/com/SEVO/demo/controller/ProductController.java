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
import com.SEVO.demo.entity.Product;

@Controller
@RequestMapping(path = "/admin/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("")
	public ModelAndView ListProducts() {
		ModelAndView mav = new ModelAndView("admin/productList");
		List<Product> products = productRepository.findAll();
		mav.addObject("products", products);
		return mav;
	}

	@GetMapping("create")
	public String createProduct(Model model) {
		model.addAttribute(new Product());
		return "admin/createProduct";
	}

	@PostMapping("saveProduct")
	public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResults) {
		
		System.out.println(bindingResults);
		if (bindingResults.hasErrors()) {
			return "admin/createProduct";
		} else {
			productRepository.saveAndFlush(product);
			return "redirect:/admin/product";

		}

	}

	@RequestMapping(value = "/{productid}", method = RequestMethod.GET)
	public String editProduct(@PathVariable @NotNull @DecimalMin("0") Integer productid, Model model) {
		Optional<Product> product = productRepository.findById(productid);

		model.addAttribute(product.get());
		return "admin/editProduct";
	}

	@PostMapping("updateProduct")
	public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResults) {

		if (bindingResults.hasErrors()) {
			return "admin/editProduct/"+product.getProductId();
		} else {
			Product tempProduct = productRepository.findById(product.getProductId()).get();
			
			tempProduct.setProductId(product.getProductId());
			tempProduct.setProductName(product.getProductName());
			tempProduct.setProductDescription(product.getProductDescription());
			
			productRepository.saveAndFlush(tempProduct);
			return "redirect:/admin/product";

		}

	}
}
