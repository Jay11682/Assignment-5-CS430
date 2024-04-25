package com.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Use;
import com.example.model.User;
import com.example.model.UseDevice;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchDeviceUsage")
public class UserSearchDeviceUsageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://faure.cs.colostate.edu:3306/jaysnell";
    private static final String JDBC_USERNAME = "jaysnell";
    private static final String JDBC_PASSWORD = "833039495";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        
        List<UseDevice> deviceUsageList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            String sql = "SELECT Users.UserID, Devices.DeviceID, Users.UserName, Devices.DeviceName, Devices.DeviceType, Uses.UsageDate, Uses.UsageDuration " +
                         "FROM Users " +
                         "JOIN Uses ON Users.UserID = Uses.UserID " +
                         "JOIN Devices ON Uses.DeviceID = Devices.DeviceID " +
                         "WHERE Users.UserID = ? AND Uses.UsageDate BETWEEN ? AND ?";
            
            statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            statement.setString(2, date1);
            statement.setString(3, date2);
            
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int userID = resultSet.getInt("UserID");
                int deviceID = resultSet.getInt("DeviceID");
                String userName = resultSet.getString("UserName");
                String deviceName = resultSet.getString("DeviceName");
                String deviceType = resultSet.getString("DeviceType");
                String usageDate = resultSet.getString("UsageDate");
                int usageDuration = resultSet.getInt("UsageDuration");
                UseDevice composite = new UseDevice(userID, userName, deviceID, deviceName, deviceType, usageDate, usageDuration);
                deviceUsageList.add(composite);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Return a JSON error response to the client
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
            return;
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (statement != null) statement.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        Gson gson = new Gson();
        String jsonResult = gson.toJson(deviceUsageList);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResult);
        out.flush();
    }
}
