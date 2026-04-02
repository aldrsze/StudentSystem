package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Register a new user (For Signup)
    public static boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            // This will catch errors like duplicate usernames or emails
            System.out.println("Error saving user: " + e.getMessage());
            return false;
        }
    }

    // Authenticate a user (For Login)
    public static boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE BINARY username = ? AND BINARY password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                // If rs.next() is true, a matching user was found!
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Database error during login: " + e.getMessage());

        }
        return false;
    }
}