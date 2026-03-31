package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Connection details
    private static final String URL = "jdbc:mysql://localhost:3306/student_system";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    // Returns the connection
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(" Connection FAILED. check if mySQL is running. ");
            e.printStackTrace();
        }
        return connection;
    }
}
