package controllers;

import models.Member;
import repositories.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    private final MemberRepository memberRepository = new MemberRepository();
    private final List<Member> members = new ArrayList<>();

    public MemberController() {
        this.members.addAll(memberRepository.getAllMembers());
    }

    public Member createMember(Scanner scanner) {
        System.out.print("Enter Member's ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Enter Member's Name: ");
        String memberName = scanner.nextLine();

        System.out.print("Enter Member's Email: ");
        String memberEmail = scanner.nextLine();

        System.out.print("Enter Membership Plan ID: ");
        String membershipPlanId = scanner.nextLine();

        Member member = new Member(memberId, memberName, memberEmail, membershipPlanId);

        memberRepository.addMember(member);
        members.add(member);
        System.out.println("Member added successfully.");
        
        return member;
    }

    public Member getMemberByID(String memberID) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberID)) {
                return member;
            }
        }
        System.out.println("Member not found.");
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

            memberRepository.updateMember(memberID, newName, newEmail, newMembershipPlan);
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
            memberRepository.deleteMember(memberID);
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
            System.out.println(String.format("| %-10s | %-20s | %-30s | %-15s |",
                member.getMemberId(),
                member.getName(),
                member.getEmail(),
                member.getMembershipPlanId()
            ));
        }
    }

    public void reloadMembersFromDatabase() {
        members.clear();
        members.addAll(memberRepository.getAllMembers());
    }
}
