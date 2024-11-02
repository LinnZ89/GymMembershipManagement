package repositories;

import models.Trainer;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerRepository {

    public void addTrainer(Trainer trainer) {
        String sql = "INSERT INTO Trainers (trainerId, name, specialization) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, trainer.getTrainerId());
            pstmt.setString(2, trainer.getName());
            pstmt.setString(3, trainer.getSpecialization());

            pstmt.executeUpdate();
            System.out.println("Trainer added to database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding trainer to database.");
        }
    }

    public void updateTrainer(String trainerId, String newName, String newSpecialization) {
        String sql = "UPDATE Trainers SET name = ?, specialization = ? WHERE trainerId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, newSpecialization);
            pstmt.setString(3, trainerId);

            pstmt.executeUpdate();
            System.out.println("Trainer updated in database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTrainer(String trainerId) {
        String sql = "DELETE FROM Trainers WHERE trainerId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, trainerId);

            pstmt.executeUpdate();
            System.out.println("Trainer deleted from database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        String sql = "SELECT * FROM Trainers";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                trainers.add(new Trainer(
                    rs.getString("trainerId"),
                    rs.getString("name"),
                    rs.getString("specialization")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching trainers from database.");
        }
        return trainers;
    }

    public Trainer getTrainerByID(String trainerId) {
        String sql = "SELECT * FROM Trainers WHERE trainerId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, trainerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Trainer(
                    rs.getString("trainerId"),
                    rs.getString("name"),
                    rs.getString("specialization")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Trainer not found in database.");
        return null;
    }
}
