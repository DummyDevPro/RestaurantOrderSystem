package com.res.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/adminLoginAction")
	public String adminLoginAction() {
		System.out.println("Click : Admin Login Action");
		// user id
		// user pwd
		
		// check account info at DB
		
		// return "redirect:/";
		
		return "redirect:adminHome";
	}
}