package models;

import java.util.Date;

public class MembershipPlan {
    private String planId;
    private Date dayStart;
    private Date dayEnd;
    private String planType;
    private double price;
    private String status;
    private String memberId;


    public MembershipPlan(String planId, Date dayStart, Date dayEnd, String planType, double price, String status,
            String memberId) {
        this.planId = planId;
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        this.planType = planType;
        this.price = price;
        this.status = status;
        this.memberId = memberId;
    }


    public String getPlanId() {
        return planId;
    }


    public void setPlanId(String planId) {
        this.planId = planId;
    }


    public Date getDayStart() {
        return dayStart;
    }


    public void setDayStart(Date dayStart) {
        this.dayStart = dayStart;
    }


    public Date getDayEnd() {
        return dayEnd;
    }


    public void setDayEnd(Date dayEnd) {
        this.dayEnd = dayEnd;
    }


    public String getPlanType() {
        return planType;
    }


    public void setPlanType(String planType) {
        this.planType = planType;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getMemberId() {
        return memberId;
    }


    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    
}
