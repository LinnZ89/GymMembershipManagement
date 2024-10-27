package controllers;
import java.util.ArrayList;
import java.util.List;

import models.History;
import models.Member;

public class MemberController {
    
    private List<Member> members = new ArrayList<>();
    private List<History> progressHistory = new ArrayList<>();

    public MemberController() {
        members = new ArrayList<>();
        progressHistory = new ArrayList<>();
    }

    public List<Member> getMembers() {
        return members;
    }
    public void setMembers(List<Member> members) {
        this.members = members;
    }
    public List<History> getProgressHistory() {
        return progressHistory;
    }
    public void setProgressHistory(List<History> progressHistory) {
        this.progressHistory = progressHistory;
    }

    public Member getMemberByID(int memberID){
        for(Member member : members){
            if(member.getMemberId() == memberID){
                return member;
            }
        }
        return null;
    }

    public void addMember(Member member){
        if(member != null){
            members.add(member);
        }
    }

    public void addProgressHistory(History history){
        if(history != null){
            progressHistory.add(history);
        }
    }

    public List<Member> searchMemberByID(int memberID){
        List<Member> result = new ArrayList<>();
        for(Member member : members){
            if(member.getMemberId() == memberID){
                result.add(member);
            }
        }
        return result;
    }

    public List<Member> searchMemberByName(String name){
        List<Member> result = new ArrayList<>();
        for(Member member : members){
            if(member.getName().equalsIgnoreCase(name)){
                result.add(member);
            }
        }
        return result;
    }

    public boolean updateMember(int newMemverID, String newName, String newEmail, String newMembershipPlan){
        Member member = getMemberByID(newMemverID);

        if(member != null){
            member.setName(newName);
            if(newEmail != null && newEmail.endsWith("@gmail.com")){
                newEmail = newEmail + "@gmail.com";
            }
            member.setEmail(newEmail);
            member.setMembershipPlan(newMembershipPlan);
        }
        return true;
    }

    public Member deleteMember(int memberID){
        for(int i = 0; i < members.size(); i++){
            if(members.get(i).getMemberId() == memberID)
                return members.get(i);
        }
        return null;
    }

    public String toStirng(){
        if(members.isEmpty()){
            return null;
        }
        String str = String.format("|%15s|%15s|%25s|%35s|\n", "MemberID", "Name", "Email", 
        "Membership Plan");
        for(Member member : members){
            str += String.format("|%15d|%15s|%25s|%35s|\n", member.getMemberId(), member.getName(), 
            member.getEmail(), member.getMembershipPlan());
        }
        return str;
    }

    public void viewProgress(){
        for (History history : progressHistory) {
            System.out.println(history.toString());
        }
    }
        
}

