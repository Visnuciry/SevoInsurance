package com.SEVO.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SEVO.demo.entity.User;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

	@GetMapping(value = "")
	public String showMainPage() {
		return "admin/homePageAdmin";
		
	
		}

	}

