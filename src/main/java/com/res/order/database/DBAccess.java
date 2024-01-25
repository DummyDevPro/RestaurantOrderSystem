package com.res.order.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBAccess {
	public static ResultSet loginByUserInfo(String userId, String userPwd, int userRole) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://res-order-app-dummy-hmm.a.aivencloud.com:16952/res_order_app", "avnadmin", "AVNS_0rTLgFKgFz4SJvM3QIW");

			String sql = "SELECT * FROM res_order_app.account WHERE user_id = ? AND user_pwd = ? AND user_role = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			pstm.setString(2, userPwd);
			pstm.setInt(3, userRole);

			return pstm.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
