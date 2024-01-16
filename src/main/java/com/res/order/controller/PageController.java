package com.res.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	@GetMapping("/")
	public String login() {
		return "index";
	}

	@GetMapping("/adminHome")
	public String adminHome() {
		return "admin_home";
	}

	@GetMapping("/addMenu")
	public String toAddMenuPage() {
		return "add_menu_item";
	}

	@GetMapping("/adminAllMenu")
	public String toAdminAllMenuPage() {
		return "admin_all_menu";
	}

	@GetMapping("/editMenu")
	public String toEditMenuPage() {
		return "edit_menu_item";
	}
}
