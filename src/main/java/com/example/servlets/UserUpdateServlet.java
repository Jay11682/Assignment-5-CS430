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

@WebServlet("/update")
public class UserUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://faure.cs.colostate.edu:3306/jaysnell";
    private static final String JDBC_USERNAME = "jaysnell";
    private static final String JDBC_PASSWORD = "833039495";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldUserID = request.getParameter("oldUserID");
        String newUserName = request.getParameter("updateUserName");
        String newUserType = request.getParameter("updateUserType");
        
        Connection connection = null;
        PreparedStatement statement = null;

        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish database connection
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            // Prepare SQL statement
            String sql = "UPDATE Users SET UserName=?, UserType=? WHERE UserID=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, newUserName);
            statement.setString(2, newUserType);
            statement.setString(3, oldUserID);
            
            // Execute query
            int rowsAffected = statement.executeUpdate();

            // Send as response
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            if (rowsAffected > 0) {
                // Show that user was updated
                out.print("User updated successfully.");
            } else {
                // Show that user was not updated
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("User not found or no changes were made.");
            }
            out.flush();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try { if (statement != null) statement.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}