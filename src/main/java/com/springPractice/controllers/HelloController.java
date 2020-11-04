package com.springPractice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springPractice.services.UserService;

@Controller
public class HelloController {
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String hello() {
		return "index";
	}
	@GetMapping("/add-user")
	public String add(@RequestParam("name") String name, Model model) {
		userService.createUser(name);
		model.addAttribute("message", "User created Successfully");
		return "index";
	}
	@GetMapping("/show-all")
	public String hello(Model model) {
		model.addAttribute("message", "Showing all users");
		userService.showAll();
		return "index";
	}
	
}
