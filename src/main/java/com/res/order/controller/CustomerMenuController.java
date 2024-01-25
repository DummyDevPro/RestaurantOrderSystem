package com.res.order.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.res.order.dao.Menu;

@Controller
public class CustomerMenuController {
	@GetMapping("/allMenu")
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
				menuObj.setMenuId(rs.getInt("menu_id"));
				menuObj.setMenuName(rs.getString("menu_name"));
				menuObj.setMenuPrice(rs.getString("menu_price"));
				menuObj.setMenuCategory(rs.getInt("menu_category"));
				menuObj.setMenuDetail(rs.getString("menu_detail"));
				menuObj.setStatusOfStock(rs.getInt("status_of_stock"));
				
				menuObj.setPhotoBase64String(Base64.encodeBase64String(rs.getBytes("menu_photo")));

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
		return "customer/all_menu";
	}
}
