package models;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private String membershipPlan;
    
    
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
        if(email != null && email.endsWith("@gmial.com")){
            this.email = email + "@gmial.com";
        } else {
            this.email = email;
        }
    }

    public String getMembershipPlan() {
        return membershipPlan;
    }

    public void setMembershipPlan(String membershipPlan) {
        this.membershipPlan = membershipPlan;
    }
    
    public void makePayment(double amount) {
        balanceDue -= amount;
        System.out.println("Payment made: $" + amount + ". Remaining balance: $" + balanceDue);
    }

    public void viewBalance() {
        System.out.println("Balance due: $" + balanceDue);
    }
    
    public String toString() {
        return String.format("%s %s %s %s", memberId, name, email, membershipPlan);
    }
    
}
