// === DBUtil.java ===
package com.vpn.backend;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/vpn_dashboard";
        String user = "root"; //  MySQL user
        String password = "vaishnavi"; //  MySQL password

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
    public static void insertAuditLog(String by, String type, String table, String details) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO system_audit (action_by, action_type, target_table, details) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, by);
            stmt.setString(2, type);
            stmt.setString(3, table);
            stmt.setString(4, details);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
