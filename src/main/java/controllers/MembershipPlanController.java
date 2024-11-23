package controllers;

import models.MembershipPlan;
import repositories.MembershipPlanRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MembershipPlanController {
    private final MembershipPlanRepository membershipPlanRepository = new MembershipPlanRepository();
    private final List<MembershipPlan> membershipPlans = new ArrayList<>();

    public MembershipPlanController() {
        this.membershipPlans.addAll(membershipPlanRepository.getAllMembershipPlans());
    }

    public MembershipPlan createMembershipPlan(Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Enter Plan ID: ");
        String planId = scanner.nextLine();

        System.out.print("Enter Plan Type: ");
        String planType = scanner.nextLine();

        System.out.print("Enter Plan Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Plan Status (Active/Inactive): ");
        String status = scanner.nextLine();

        Date dayStart = null;
        System.out.print("Enter Start Date (dd/MM/yyyy): ");
        try {
            dayStart = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return null;
        }

        Date dayEnd = null;
        System.out.print("Enter End Date (dd/MM/yyyy): ");
        try {
            dayEnd = dateFormat.parse(scanner.nextLine());
            if (!validateDates(dayStart, dayEnd)) {
                System.out.println("End date must be after the start date.");
                return null;
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return null;
        }

        MembershipPlan newPlan = new MembershipPlan(planId, dayStart, dayEnd, planType, price, status);

        // Add plan to both database and in-memory list
        membershipPlanRepository.addMembershipPlan(newPlan);
        membershipPlans.add(newPlan);
        System.out.println("Membership plan created successfully!");
        
        return newPlan;
    }

    public boolean updateMembershipPlan(Scanner scanner) {
        System.out.print("Enter the Plan ID of the plan you wish to update: ");
        String planId = scanner.nextLine();

        MembershipPlan plan = getPlanById(planId);
        if (plan == null) {
            System.out.println("Plan with ID " + planId + " not found.");
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Enter new Plan Type (current: " + plan.getPlanType() + "): ");
        String newPlanType = scanner.nextLine();
        if (!newPlanType.isEmpty()) {
            plan.setPlanType(newPlanType);
        }

        System.out.print("Enter new Plan Price (current: " + plan.getPrice() + "): ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) {
            try {
                double newPrice = Double.parseDouble(priceInput);
                plan.setPrice(newPrice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Keeping the current price.");
            }
        }

        System.out.print("Enter new Plan Status (current: " + plan.getStatus() + "): ");
        String newStatus = scanner.nextLine();
        if (!newStatus.isEmpty()) {
            plan.setStatus(newStatus);
        }

        System.out.print("Enter new Start Date (dd/MM/yyyy, current: " + dateFormat.format(plan.getDayStart()) + "): ");
        String startDateInput = scanner.nextLine();
        if (!startDateInput.isEmpty()) {
            try {
                Date newDayStart = dateFormat.parse(startDateInput);
                if (validateDates(newDayStart, plan.getDayEnd())) {
                    plan.setDayStart(newDayStart);
                } else {
                    System.out.println("Invalid date. Start date must be before the end date.");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Keeping the current start date.");
            }
        }

        System.out.print("Enter new End Date (dd/MM/yyyy, current: " + dateFormat.format(plan.getDayEnd()) + "): ");
        String endDateInput = scanner.nextLine();
        if (!endDateInput.isEmpty()) {
            try {
                Date newDayEnd = dateFormat.parse(endDateInput);
                if (validateDates(plan.getDayStart(), newDayEnd)) {
                    plan.setDayEnd(newDayEnd);
                } else {
                    System.out.println("Invalid date. End date must be after the start date.");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Keeping the current end date.");
            }
        }

        membershipPlanRepository.updateMembershipPlan(plan);
        System.out.println("Membership plan updated successfully.");
        return true;
    }

    public boolean deleteMembershipPlan(String planId) {
        MembershipPlan plan = getPlanById(planId);
        if (plan != null) {
            membershipPlans.remove(plan);
            membershipPlanRepository.deleteMembershipPlan(planId);
            System.out.println("Membership plan deleted successfully.");
            return true;
        }
        System.out.println("Membership plan not found.");
        return false;
    }

    public MembershipPlan getPlanById(String planId) {
        for (MembershipPlan plan : membershipPlans) {
            if (plan.getPlanId().equals(planId)) {
                return plan;
            }
        }
        System.out.println("Plan not found in memory.");
        return null;
    }

    public void viewAllMembershipPlans() {
        if (membershipPlans.isEmpty()) {
            System.out.println("No membership plans available.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(String.format("| %-10s | %-15s | %-10s | %-10s | %-12s | %-15s |", 
                                         "Plan ID", "Type", "Price", "Status", "Start Date", "End Date"));
        System.out.println("-------------------------------------------------------------------------------------------");

        for (MembershipPlan plan : membershipPlans) {
            System.out.println(String.format("| %-10s | %-15s | %-10.2f | %-10s | %-12s | %-15s |", 
                                             plan.getPlanId(),
                                             plan.getPlanType(),
                                             plan.getPrice(),
                                             plan.getStatus(),
                                             dateFormat.format(plan.getDayStart()),
                                             dateFormat.format(plan.getDayEnd())));
        }
    }

    private boolean validateDates(Date start, Date end) {
        return end.after(start);
    }
}
