package repositories;

import models.Member;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    public void addMember(Member member) {
        String sql = "INSERT INTO Members (memberId, m_name, email, membershipPlanId) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, member.getMemberId());
            pstmt.setString(2, member.getName()); // Fixed column name to `m_name`
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getMembershipPlanId());

            pstmt.executeUpdate();
            System.out.println("Member added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding member to database.");
        }
    }

    public void updateMember(String memberID, String newName, String newEmail, String newMembershipPlan) {
        String sql = "UPDATE Members SET m_name = ?, email = ?, membershipPlanId = ? WHERE memberId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName); // Fixed column name to `m_name`
            pstmt.setString(2, newEmail);
            pstmt.setString(3, newMembershipPlan);
            pstmt.setString(4, memberID);

            pstmt.executeUpdate();
            System.out.println("Member updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating member in database.");
        }
    }

    public void deleteMember(String memberID) {
        String sql = "DELETE FROM Members WHERE memberId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberID);

            pstmt.executeUpdate();
            System.out.println("Member deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting member from database.");
        }
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM Members";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                members.add(new Member(
                    rs.getString("memberId"),
                    rs.getString("m_name"), // Fixed column name to `m_name`
                    rs.getString("email"),
                    rs.getString("membershipPlanId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching members from database.");
        }
        return members;
    }

    public Member getMemberByID(String memberID) {
        String sql = "SELECT * FROM Members WHERE memberId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Member(
                    rs.getString("memberId"),
                    rs.getString("m_name"), // Fixed column name to `m_name`
                    rs.getString("email"),
                    rs.getString("membershipPlanId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching member by ID from database.");
        }
        System.out.println("Member not found in database.");
        return null;
    }
}
