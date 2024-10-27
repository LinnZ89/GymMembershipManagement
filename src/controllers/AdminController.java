package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.DatabaseConnection;

public class AdminController {

    public boolean login(String username, String password) {
        String sql_query = "SELECT * FROM Admins WHERE username = ? AND password = ?";

        try (Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql_query)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
