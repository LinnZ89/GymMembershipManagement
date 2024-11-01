package models;

import java.util.Date;

public class Payment {
    private String paymentId;
    private Date datePay;
    private Integer loan;
    private Integer moneyPaid;
    private String paymentStatus;
    private String paymentMethod;
    private String memberId;

    // Constructor
    public Payment(String paymentId, Date datePay, Integer loan, Integer moneyPaid, String paymentStatus, String paymentMethod, String memberId) {
        this.paymentId = paymentId;
        this.datePay = datePay;
        this.loan = loan;
        this.moneyPaid = moneyPaid;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.memberId = memberId;
    }

    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Date getDatePay() {
        return datePay;
    }

    public void setDatePay(Date datePay) {
        this.datePay = datePay;
    }

    public Integer getLoan() {
        return loan;
    }

    public void setLoan(Integer loan) {
        this.loan = loan;
    }

    public Integer getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(Integer moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    // toString method
    @Override
    public String toString() {
        return "Payment [paymentId=" + paymentId + ", datePay=" + datePay + ", loan=" + loan + 
               ", moneyPaid=" + moneyPaid + ", paymentStatus=" + paymentStatus + 
               ", paymentMethod=" + paymentMethod + ", memberId=" + memberId + "]";
    }
}