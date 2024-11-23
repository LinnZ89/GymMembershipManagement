package repositories;

import models.TrainingSession;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingSessionRepository {

    public void addTrainingSession(TrainingSession session) {
        String sql = "INSERT INTO TrainingSessions (sessionId, Day_Start, Day_End, sessionLocation, memberId, trainerId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, session.getSessionId());
            pstmt.setDate(2, new Date(session.getDayStart().getTime())); // Updated to `Day_Start`
            pstmt.setDate(3, new Date(session.getDayEnd().getTime()));   // Updated to `Day_End`
            pstmt.setString(4, session.getSessionLocation());
            pstmt.setString(5, session.getMemberId());
            pstmt.setString(6, session.getTrainerId());

            pstmt.executeUpdate();
            System.out.println("Training session added to database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding training session to database.");
        }
    }

    public void updateTrainingSession(TrainingSession session) {
        String sql = "UPDATE TrainingSessions SET sessionLocation = ?, trainerId = ? WHERE sessionId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, session.getSessionLocation());
            pstmt.setString(2, session.getTrainerId());
            pstmt.setString(3, session.getSessionId());

            pstmt.executeUpdate();
            System.out.println("Training session updated in database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating training session in database.");
        }
    }

    public void deleteTrainingSession(String sessionId) {
        String sql = "DELETE FROM TrainingSessions WHERE sessionId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sessionId);

            pstmt.executeUpdate();
            System.out.println("Training session deleted from database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting training session from database.");
        }
    }

    public List<TrainingSession> getAllTrainingSessions() {
        List<TrainingSession> trainingSessions = new ArrayList<>();
        String sql = "SELECT * FROM TrainingSessions";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                trainingSessions.add(new TrainingSession(
                    rs.getString("sessionId"),
                    rs.getDate("Day_Start"), // Updated to `Day_Start`
                    rs.getDate("Day_End"),   // Updated to `Day_End`
                    rs.getString("sessionLocation"),
                    rs.getString("memberId"),
                    rs.getString("trainerId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching training sessions from database.");
        }
        return trainingSessions;
    }

    public TrainingSession getSessionById(String sessionId) {
        String sql = "SELECT * FROM TrainingSessions WHERE sessionId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sessionId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new TrainingSession(
                    rs.getString("sessionId"),
                    rs.getDate("Day_Start"), // Updated to `Day_Start`
                    rs.getDate("Day_End"),   // Updated to `Day_End`
                    rs.getString("sessionLocation"),
                    rs.getString("memberId"),
                    rs.getString("trainerId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Training session not found in database.");
        return null;
    }
}
