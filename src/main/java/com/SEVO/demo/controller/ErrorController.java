package com.SEVO.demo.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

	@GetMapping("/403")
	public String showError403Page () {
		return "Error/403";
	}
	

	
	private static final String PATH = "/error";
	
	@RequestMapping(value = PATH)
	public String handleError (HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status!=null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "Error/404";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "Error/500";
			} else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
				return "Error/403";
			} else {
				return "Error/unkown";
			}
		}
		return "Error/unkown";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
