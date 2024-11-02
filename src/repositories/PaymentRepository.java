package repositories;

import models.Payment;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {

    public void addPayment(Payment payment) {
        String sql = "INSERT INTO Payments (paymentId, datePay, loan, moneyPaid, paymentStatus, paymentMethod, memberId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, payment.getPaymentId());
            pstmt.setDate(2, new Date(payment.getDatePay().getTime()));
            pstmt.setInt(3, payment.getLoan());
            pstmt.setInt(4, payment.getMoneyPaid());
            pstmt.setString(5, payment.getPaymentStatus());
            pstmt.setString(6, payment.getPaymentMethod());
            pstmt.setString(7, payment.getMemberId());

            pstmt.executeUpdate();
            System.out.println("Payment added to database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding payment to database.");
        }
    }

    public void updatePayment(Payment payment) {
        String sql = "UPDATE Payments SET datePay = ?, loan = ?, moneyPaid = ?, paymentStatus = ?, paymentMethod = ? WHERE paymentId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, new Date(payment.getDatePay().getTime()));
            pstmt.setInt(2, payment.getLoan());
            pstmt.setInt(3, payment.getMoneyPaid());
            pstmt.setString(4, payment.getPaymentStatus());
            pstmt.setString(5, payment.getPaymentMethod());
            pstmt.setString(6, payment.getPaymentId());

            pstmt.executeUpdate();
            System.out.println("Payment updated in database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating payment in database.");
        }
    }

    public void deletePayment(String paymentId) {
        String sql = "DELETE FROM Payments WHERE paymentId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, paymentId);

            pstmt.executeUpdate();
            System.out.println("Payment deleted from database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting payment from database.");
        }
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM Payments";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                payments.add(new Payment(
                    rs.getString("paymentId"),
                    rs.getDate("datePay"),
                    rs.getInt("loan"),
                    rs.getInt("moneyPaid"),
                    rs.getString("paymentStatus"),
                    rs.getString("paymentMethod"),
                    rs.getString("memberId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching payments from database.");
        }
        return payments;
    }

    public Payment getPaymentById(String paymentId) {
        String sql = "SELECT * FROM Payments WHERE paymentId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, paymentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Payment(
                    rs.getString("paymentId"),
                    rs.getDate("datePay"),
                    rs.getInt("loan"),
                    rs.getInt("moneyPaid"),
                    rs.getString("paymentStatus"),
                    rs.getString("paymentMethod"),
                    rs.getString("memberId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Payment not found in database.");
        return null;
    }
}
