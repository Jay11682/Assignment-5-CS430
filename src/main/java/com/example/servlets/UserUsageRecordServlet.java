package com.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addUseRecord")
public class UserUsageRecordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://faure.cs.colostate.edu:3306/jaysnell";
    private static final String JDBC_USERNAME = "jaysnell";
    private static final String JDBC_PASSWORD = "833039495";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String deviceID = request.getParameter("deviceID");
        String usageDate = request.getParameter("usageDate");
        int usageDuration = Integer.parseInt(request.getParameter("usageDuration"));

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            String sql = "INSERT INTO Uses (UserID, DeviceID, UsageDate, UsageDuration) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, deviceID);
            statement.setString(3, usageDate);
            statement.setInt(4, usageDuration);

            statement.executeUpdate();

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            //Show that record was added without failure
            out.print("Use record added successfully.");
            out.flush();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding use record.");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
