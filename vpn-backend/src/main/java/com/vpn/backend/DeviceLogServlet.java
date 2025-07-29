package com.vpn.backend;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DeviceLogServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Add to ALL your servlets' doGet() and doPost() methods
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String logData = request.getParameter("log_data");
        String ipAddress = request.getRemoteAddr();
        String deviceInfo = request.getHeader("User-Agent");

        try (Connection conn = DBUtil.getConnection()) {

            // First: check if user exists (optional but good for debugging)
            PreparedStatement checkUser = conn.prepareStatement("SELECT * FROM vpn_users WHERE username = ?");
            checkUser.setString(1, username);
            ResultSet rs = checkUser.executeQuery();

            if (!rs.next()) {
                out.print("{\"status\":\"error\", \"message\":\"Username not found in vpn_users\"}");
                return;
            }

            // Insert into vpn_logs
            String sql = "INSERT INTO vpn_logs (username, log_data, ip_address, device_info) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, logData);
            stmt.setString(3, ipAddress);
            stmt.setString(4, deviceInfo);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
            	 // Insert audit log
                DBUtil.insertAuditLog(username, "LOG_UPLOAD", "vpn_logs", "Device log uploaded");

                out.print("{\"status\":\"success\", \"message\":\"Log saved\"}");
            } else {
                out.print("{\"status\":\"error\", \"message\":\"Insert failed\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Add to ALL your servlets' doGet() and doPost() methods
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{\"status\":\"success\", \"message\":\"GET received\"}");
    }
}

