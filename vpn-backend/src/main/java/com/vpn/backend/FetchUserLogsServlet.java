package com.vpn.backend;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//@WebServlet("/fetch-user-logs")
public class FetchUserLogsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Add to ALL your servlets' doGet() and doPost() methods
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String username = request.getParameter("username");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (username == null || username.trim().isEmpty()) {
            out.print("{\"status\":\"error\",\"message\":\"Username is required\"}");
            return;
        }

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM vpn_logs WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            StringBuilder json = new StringBuilder();
            json.append("{\"status\":\"success\",\"logs\":[");

            boolean first = true;
            while (rs.next()) {
                if (!first) json.append(",");
                json.append("{")
                    .append("\"id\":").append(rs.getInt("id")).append(",")
                    .append("\"log_data\":\"").append(rs.getString("log_data")).append("\",")
                    .append("\"ip_address\":\"").append(rs.getString("ip_address")).append("\",")
                    .append("\"device_info\":\"").append(rs.getString("device_info")).append("\",")
                    .append("\"timestamp\":\"").append(rs.getTimestamp("timestamp")).append("\"")
                    .append("}");
                first = false;
            }

            json.append("]}");
            out.print(json.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            out.print("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
