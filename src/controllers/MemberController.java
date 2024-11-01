package controllers;

import models.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    private List<Member> members = new ArrayList<>();

    public List<Member> getMembers() {
        return members;
    }
    
    public Member createMember(Scanner scanner) {
        System.out.print("Enter Member's ID: ");
        String memberId = scanner.nextLine();
        scanner.nextLine(); 

        System.out.print("Enter Member's Name: ");
        String memberName = scanner.nextLine();

        System.out.print("Enter Member's Email: ");
        String memberEmail = scanner.nextLine();

        System.out.print("Enter Membership Plan ID: ");
        String membershipPlanId = scanner.nextLine();

        Member member = new Member(memberId, memberName, memberEmail, membershipPlanId);
        this.addMember(member);
        return member;
    }

    public void addMember(Member member) {
        if (member != null && member.getEmail().contains("@")) {
            members.add(member);
            System.out.println("Member added successfully.");
        } else {
            System.out.println("Invalid member details.");
        }
    }

    public Member getMemberByID(String memberID) {
        for (Member member : members) {
            if (member.getMemberId() == memberID) {
                return member;
            }
        }
        return null;
    }

    public boolean updateMember(String memberID, String newName, String newEmail, String newMembershipPlan) {
        Member member = getMemberByID(memberID);
        if (member != null) {
            member.setName(newName);
            if (newEmail != null && !newEmail.contains("@")) {
                newEmail = newEmail + "@example.com";
            }
            member.setEmail(newEmail);
            member.setMembershipPlanId(newMembershipPlan);
            System.out.println("Member updated successfully.");
            return true;
        }
        System.out.println("Member not found.");
        return false;
    }

    public boolean deleteMember(String memberID) {
        Member member = getMemberByID(memberID);
        if (member != null) {
            members.remove(member);
            System.out.println("Member deleted successfully.");
            return true;
        }
        System.out.println("Member not found.");
        return false;
    }

    public void viewAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members available.");
            return;
        }

        System.out.println(String.format("| %-10s | %-20s | %-30s | %-15s |", "Member ID", "Name", "Email", "Membership Plan"));
        System.out.println("-----------------------------------------------------------------------------------------------");
        
        for (Member member : members) {
            System.out.println(String.format("| %-10d | %-20s | %-30s | %-15s |", 
                member.getMemberId(),
                member.getName(),
                member.getEmail(),
                member.getMembershipPlanId()
            ));
        }
    }
}
