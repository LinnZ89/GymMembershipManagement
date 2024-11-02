package repositories;

import models.MembershipPlan;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembershipPlanRepository {

    public void addMembershipPlan(MembershipPlan plan) {
        String sql = "INSERT INTO MembershipPlan (planId, dayStart, dayEnd, planType, price, status, memberId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, plan.getPlanId());
            pstmt.setDate(2, new Date(plan.getDayStart().getTime()));
            pstmt.setDate(3, new Date(plan.getDayEnd().getTime()));
            pstmt.setString(4, plan.getPlanType());
            pstmt.setDouble(5, plan.getPrice());
            pstmt.setString(6, plan.getStatus());
            pstmt.setString(7, plan.getMemberId());

            pstmt.executeUpdate();
            System.out.println("Membership plan added to database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding membership plan to database.");
        }
    }

    public void updateMembershipPlan(MembershipPlan plan) {
        String sql = "UPDATE MembershipPlan SET planType = ?, price = ?, status = ?, dayStart = ?, dayEnd = ? WHERE planId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, plan.getPlanType());
            pstmt.setDouble(2, plan.getPrice());
            pstmt.setString(3, plan.getStatus());
            pstmt.setDate(4, new Date(plan.getDayStart().getTime()));
            pstmt.setDate(5, new Date(plan.getDayEnd().getTime()));
            pstmt.setString(6, plan.getPlanId());

            pstmt.executeUpdate();
            System.out.println("Membership plan updated in database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating membership plan in database.");
        }
    }

    public void deleteMembershipPlan(String planId) {
        String sql = "DELETE FROM MembershipPlan WHERE planId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, planId);

            pstmt.executeUpdate();
            System.out.println("Membership plan deleted from database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting membership plan from database.");
        }
    }

    public List<MembershipPlan> getAllMembershipPlans() {
        List<MembershipPlan> membershipPlans = new ArrayList<>();
        String sql = "SELECT * FROM MembershipPlan";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                membershipPlans.add(new MembershipPlan(
                    rs.getString("planId"),
                    rs.getDate("dayStart"),
                    rs.getDate("dayEnd"),
                    rs.getString("planType"),
                    rs.getDouble("price"),
                    rs.getString("status"),
                    rs.getString("memberId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching membership plans from database.");
        }
        return membershipPlans;
    }

    public MembershipPlan getPlanById(String planId) {
        String sql = "SELECT * FROM MembershipPlan WHERE planId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, planId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new MembershipPlan(
                    rs.getString("planId"),
                    rs.getDate("dayStart"),
                    rs.getDate("dayEnd"),
                    rs.getString("planType"),
                    rs.getDouble("price"),
                    rs.getString("status"),
                    rs.getString("memberId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Membership plan not found in database.");
        return null;
    }
}
