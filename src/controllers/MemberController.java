package controllers;

import java.util.ArrayList;
import java.util.List;

import models.MemberProgress;
import models.Member;

public class MemberController {
    
    private List<Member> members;
    private List<MemberProgress> progressHistory;

    public MemberController() {
        this.members = new ArrayList<>();
        this.progressHistory = new ArrayList<>();
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<MemberProgress> getProgressHistory() {
        return progressHistory;
    }

    public void setProgressHistory(List<MemberProgress> progressHistory) {
        this.progressHistory = progressHistory;
    }

    public Member getMemberByID(int memberID) {
        for (Member member : members) {
            if (member.getMemberId() == memberID) {
                return member;
            }
        }
        return null;
    }

    public void addMember(Member member) {
        if (member != null) {
            members.add(member);
        }
    }

    public void addProgressHistory(MemberProgress progress) {
        if (progress != null) {
            progressHistory.add(progress);
        }
    }

    public List<Member> searchMemberByID(int memberID) {
        List<Member> result = new ArrayList<>();
        for (Member member : members) {
            if (member.getMemberId() == memberID) {
                result.add(member);
            }
        }
        return result;
    }

    public List<Member> searchMemberByName(String name) {
        List<Member> result = new ArrayList<>();
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                result.add(member);
            }
        }
        return result;
    }

    public boolean updateMember(int memberID, String newName, String newEmail, String newMembershipPlan) {
        Member member = getMemberByID(memberID);

        if (member != null) {
            member.setName(newName);
            if (newEmail != null && !newEmail.endsWith("@gmail.com")) {
                newEmail = newEmail + "@gmail.com";
            }
            member.setEmail(newEmail);
            member.setMembershipPlan(newMembershipPlan);
            return true;
        }
        return false;
    }

    public boolean deleteMember(int memberID) {
        Member member = getMemberByID(memberID);
        if (member != null) {
            members.remove(member);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (members.isEmpty()) {
            return "No members available.";
        }
        StringBuilder str = new StringBuilder(String.format("|%15s|%15s|%25s|%35s|\n", "MemberID", "Name", "Email", "Membership Plan"));
        for (Member member : members) {
            str.append(String.format("|%15d|%15s|%25s|%35s|\n", member.getMemberId(), member.getName(), member.getEmail(), member.getMembershipPlan()));
        }
        return str.toString();
    }

    public void viewProgress() {
        if (progressHistory.isEmpty()) {
            System.out.println("No member progress history available.");
        } else {
            for (MemberProgress progress : progressHistory) {
                System.out.println(progress.toString());
            }
        }
    }
}
