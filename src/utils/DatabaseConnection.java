package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:sqlserver://192.168.1.9:1433;databaseName=GymManagementDB";
    private static final String user = "admin_user";
    private static final String password = "h089.";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
