package repositories;

import models.MemberProgress;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberProgressRepository {

    public void addMemberProgress(MemberProgress progress) {
        String sql = "INSERT INTO MemberProgress (progressId, progressDate, muscleTrain, bodyweight, height, BMI, memberId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, progress.getProgressId());
            pstmt.setDate(2, new Date(progress.getProgressDate().getTime()));
            pstmt.setString(3, progress.getMuscleTrain());
            pstmt.setFloat(4, progress.getBodyweight());
            pstmt.setFloat(5, progress.getHeight());
            pstmt.setFloat(6, progress.getBmi()); // Updated to match column 'BMI'
            pstmt.setString(7, progress.getMemberId());

            pstmt.executeUpdate();
            System.out.println("Member progress added to database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding member progress to database.");
        }
    }

    public void updateMemberProgress(MemberProgress progress) {
        String sql = "UPDATE MemberProgress SET muscleTrain = ?, bodyweight = ?, height = ?, BMI = ? WHERE progressId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, progress.getMuscleTrain());
            pstmt.setFloat(2, progress.getBodyweight());
            pstmt.setFloat(3, progress.getHeight());
            pstmt.setFloat(4, progress.getBmi()); // Updated to match column 'BMI'
            pstmt.setString(5, progress.getProgressId());

            pstmt.executeUpdate();
            System.out.println("Member progress updated in database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating member progress in database.");
        }
    }

    public void deleteMemberProgress(String progressId) {
        String sql = "DELETE FROM MemberProgress WHERE progressId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, progressId);

            pstmt.executeUpdate();
            System.out.println("Member progress deleted from database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting member progress from database.");
        }
    }

    public List<MemberProgress> getAllMemberProgress() {
        List<MemberProgress> memberProgressList = new ArrayList<>();
        String sql = "SELECT * FROM MemberProgress";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                memberProgressList.add(new MemberProgress(
                    rs.getString("progressId"),
                    rs.getDate("progressDate"),
                    rs.getString("muscleTrain"),
                    rs.getFloat("bodyweight"),
                    rs.getFloat("height"),
                    rs.getFloat("BMI"), // Updated to match column 'BMI'
                    rs.getString("memberId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching member progress records from database.");
        }
        return memberProgressList;
    }

    public MemberProgress getProgressById(String progressId) {
        String sql = "SELECT * FROM MemberProgress WHERE progressId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, progressId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new MemberProgress(
                    rs.getString("progressId"),
                    rs.getDate("progressDate"),
                    rs.getString("muscleTrain"),
                    rs.getFloat("bodyweight"),
                    rs.getFloat("height"),
                    rs.getFloat("BMI"), // Updated to match column 'BMI'
                    rs.getString("memberId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching member progress by ID from database.");
        }
        return null;
    }
}
