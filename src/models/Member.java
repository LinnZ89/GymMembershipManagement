package models;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private String membershipPlan;
    
    private List<String> progressHistory = new ArrayList<>();
    private double balanceDue;
    
    public Member(int memberId, String name, String email, String membershipPlan) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.membershipPlan = membershipPlan;
    }

    
    public int getMemberId() {
        return memberId;
    }


    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void addProgress(String progress) {
        progressHistory.add(progress);
        System.out.println("Progress added: " + progress);
    }

    public void viewProgress() {
        System.out.println("Progress History for " + name);
        for (String progress : progressHistory) {
            System.out.println(progress);
        }
    }

    
    public void makePayment(double amount) {
        balanceDue -= amount;
        System.out.println("Payment made: $" + amount + ". Remaining balance: $" + balanceDue);
    }

    public void viewBalance() {
        System.out.println("Balance due: $" + balanceDue);
    }
    
    
}
