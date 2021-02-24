package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@GetMapping({"/", "/home"})
	public @ResponseBody String home() {
		return "home";
	}
	
	@GetMapping("/login")
	public @ResponseBody String login() {
		return "login";
	}
	
	// test
	@GetMapping("/logintest")
	public @ResponseBody String logintest() {
		return "logintest";
	}
	
	
	@GetMapping("/logout")
	public @ResponseBody String logout() {
		return "logout";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
}
