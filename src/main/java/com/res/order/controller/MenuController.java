package com.res.order.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.res.order.dao.Menu;

@Controller
public class MenuController {

	@GetMapping("/adminAllMenu")
	public String toAdminAllMenuPage(Model model) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/res_order_app", "root", "root");

			String sql = "SELECT * FROM all_menu";
			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			List<Menu> setMenu = new ArrayList<>();
			List<Menu> tanpinMenu = new ArrayList<>();
			List<Menu> dessertMenu = new ArrayList<>();

			List<Menu> softDrinkMenu = new ArrayList<>();
			List<Menu> whiskeyMenu = new ArrayList<>();
			List<Menu> beerMenu = new ArrayList<>();
			List<Menu> nonAlcoholMenu = new ArrayList<>();

			while (rs.next()) {
				Menu menuObj = new Menu();
				menuObj.setMenuName(rs.getString("menu_name"));
				menuObj.setMenuPrice(rs.getString("menu_price"));
				menuObj.setMenuCategory(rs.getInt("menu_category"));
				menuObj.setMenuDetail(rs.getString("menu_detail"));

				switch (menuObj.getMenuCategory()) {
				case 0:
					setMenu.add(menuObj);
					break;
				case 1:
					tanpinMenu.add(menuObj);
					break;
				case 2:
					dessertMenu.add(menuObj);
					break;
				case 3:
					softDrinkMenu.add(menuObj);
					break;
				case 4:
					whiskeyMenu.add(menuObj);
					break;
				case 5:
					beerMenu.add(menuObj);
					break;
				case 6:
					nonAlcoholMenu.add(menuObj);
					break;
				}
			}

			model.addAttribute("setMenus", setMenu);
			model.addAttribute("tanpinMenus", tanpinMenu);
			model.addAttribute("dessertMenus", dessertMenu);
			model.addAttribute("softDrinkMenus", softDrinkMenu);
			model.addAttribute("whiskeyMenus", whiskeyMenu);
			model.addAttribute("beerMenus", beerMenu);
			model.addAttribute("nonAlcoholMenus", nonAlcoholMenu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin_all_menu";
	}

	@PostMapping("/addMenuAction")
	public String addMenuAction(@ModelAttribute Menu menu) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/res_order_app", "root", "root");

			String sql = "INSERT INTO all_menu (menu_photo,menu_name,menu_price,menu_category,menu_detail) VALUES (?,?,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setBytes(1, menu.getMenuPhoto().getBytes());
			pstm.setString(2, menu.getMenuName());
			pstm.setString(3, menu.getMenuPrice());
			pstm.setInt(4, menu.getMenuCategory());
			pstm.setString(5, menu.getMenuDetail());

			pstm.executeUpdate();

		} catch (Exception e) {
		}
		return "redirect:adminHome";
	}
}
