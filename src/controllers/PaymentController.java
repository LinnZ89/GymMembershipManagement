package controllers;

import models.Payment;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PaymentController {
    private List<Payment> payments = new ArrayList<>();

    public Payment createPayment(Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Enter Payment ID: ");
        String paymentId = scanner.nextLine();

        System.out.print("Enter Payment Date (dd/MM/yyyy): ");
        Date datePay;
        try {
            datePay = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return null;
        }

        System.out.print("Enter Loan Amount: ");
        int loan = scanner.nextInt();

        System.out.print("Enter Money Paid: ");
        int moneyPaid = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Payment Status (Completed/Pending): ");
        String paymentStatus = scanner.nextLine();

        System.out.print("Enter Payment Method (Cash/Card): ");
        String paymentMethod = scanner.nextLine();

        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();

        Payment newPayment = new Payment(paymentId, datePay, loan, moneyPaid, paymentStatus, paymentMethod, memberId);
        addPayment(newPayment);
        System.out.println("Payment created successfully!");
        return newPayment;
    }

    public void addPayment(Payment payment) {
        if (payment != null) {
            payments.add(payment);
            System.out.println("Payment added successfully.");
        } else {
            System.out.println("Invalid payment details.");
        }
    }

    public boolean updatePayment(String paymentId, Date newDatePay, Integer newLoan, Integer newMoneyPaid, String newPaymentStatus, String newPaymentMethod) {
        Payment payment = getPaymentById(paymentId);
        if (payment != null) {
            payment.setDatePay(newDatePay);
            payment.setLoan(newLoan);
            payment.setMoneyPaid(newMoneyPaid);
            payment.setPaymentStatus(newPaymentStatus);
            payment.setPaymentMethod(newPaymentMethod);
            System.out.println("Payment updated successfully.");
            return true;
        }
        System.out.println("Payment not found.");
        return false;
    }

    public boolean deletePayment(String paymentId) {
        Payment payment = getPaymentById(paymentId);
        if (payment != null) {
            payments.remove(payment);
            System.out.println("Payment deleted successfully.");
            return true;
        }
        System.out.println("Payment not found.");
        return false;
    }

    public Payment getPaymentById(String paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }

    public List<Payment> searchPaymentsByMemberId(String memberId) {
        List<Payment> result = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.getMemberId().equals(memberId)) {
                result.add(payment);
            }
        }
        return result;
    }

    public void viewAllPayments() {
        if (payments.isEmpty()) {
            System.out.println("No payments available.");
        } else {
            for (Payment payment : payments) {
                System.out.println(payment);
            }
        }
    }
}
