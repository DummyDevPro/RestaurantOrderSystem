package com.res.order.controller;

import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.res.order.database.DBAccess;

@Controller
public class LoginController {

	@GetMapping("/adminLoginAction")
	public String adminLoginAction(@RequestParam("user_id") String userId, @RequestParam("user_pwd") String pwd) {
		try {
			ResultSet rs = DBAccess.loginByUserInfo(userId, pwd, 0);
			// If result set is not null
			// And result set has any record
			if (rs != null && rs.next()) {
				return "redirect:adminHome";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
}