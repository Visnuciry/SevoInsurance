package com.SEVO.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.SEVO.demo.dao.UserRepository;
import com.SEVO.demo.dto.UpdateUsersPassword;
import com.SEVO.demo.entity.User;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("")
	public String showMainPage() {
		return "admin/HomePageAdmin";

	}
	@GetMapping("users")
	public ModelAndView ListUsers() {
		ModelAndView mav = new ModelAndView("admin/UserList");
		List<User> users = userRepository.findAll();
		mav.addObject("users", users);
		return mav;
	}

	@GetMapping("trashUsers")
	public ModelAndView TrashUsers() {
		ModelAndView mav = new ModelAndView("admin/TrashUsers");
		List<User> users = userRepository.recycleBin();
		mav.addObject("users", users);
		return mav;
	}

	@PostMapping("userDelete")
	public ResponseEntity<?> softDeleteUser(@RequestBody Integer userId) {
		userRepository.softDelete(userId);
		return ResponseEntity.ok(userId);
	}

	@PostMapping("userRestore")
	public ResponseEntity<?> restoreUser(@RequestBody Integer userId) {
		userRepository.restoreUser(userId);
		return ResponseEntity.ok(userId);
	}

	@PostMapping("passwordReset")
	public ResponseEntity<?> passwordRest(@Valid @RequestBody UpdateUsersPassword user,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.ok("error");
		} else {
			Optional<User> tempUser = userRepository.findById(user.getId());
			User updateUser = userRepository.findByUserName(tempUser.get().getUserName());
			updateUser.setId(tempUser.get().getId());
			updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.saveAndFlush(updateUser);
			return ResponseEntity.ok("success");
		}
	}
	@PostMapping("deleteUser")
	public ResponseEntity<?> deleteUser(@RequestBody Integer userId) {
		userRepository.deleteById(userId);
		return ResponseEntity.ok(userId);
	}

}
