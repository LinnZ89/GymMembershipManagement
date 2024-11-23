package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:sqlserver://192.168.1.14:1433;databaseName=GymManagementDB;encrypt=true;trustServerCertificate=true;";
    private static final String user = "admin_user"; // Replace with your username
    private static final String password = "h089."; // Replace with your password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
