package controllers;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import models.MembershipPlan;
import java.util.Scanner;

public class MembershipPlanController {

    private List<MembershipPlan> membershipPlans;

    public MembershipPlanController() {
        this.membershipPlans = new ArrayList<>();
    }

    public List<MembershipPlan> getMembershipPlans() {
        return membershipPlans;
    }

    public void setMembershipPlans(List<MembershipPlan> membershipPlans) {
        this.membershipPlans = membershipPlans;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n--- Membership Plan Menu ---");
            System.out.println("1. Create Membership Plan");
            System.out.println("2. Delete Membership Plan");
            System.out.println("3. Update Membership Plan");
            System.out.println("4. View All Membership Plans");
            System.out.println("5. Back To Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createMembershipPlan(scanner);
                    break;
                
                case 2:
                    deleteMembershipPlan(scanner);
                    break;
                
                case 3:
                    updateMembershipPlan(scanner);
                    break;
                
                case 4:
                    System.out.println(toString());
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting Membership Plan Menu...");
                    break;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void createMembershipPlan(Scanner scanner) {
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

        System.out.print("Enter Start Date (dd/MM/yyyy): ");
        Date dayStart = null;
        try {
            dayStart = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return;
        }

        System.out.print("Enter End Date (dd/MM/yyyy): ");
        Date dayEnd = null;
        try {
            dayEnd = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return;
        }

        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();

    
        MembershipPlan newPlan = new MembershipPlan(planId, dayStart, dayEnd, planType, price, status, memberId);
        addMembershipPlan(newPlan);
        System.out.println("Membership plan created successfully!");
    }


    private void deleteMembershipPlan(Scanner scanner) {
        System.out.print("Enter Plan ID to delete: ");
        String planId = scanner.nextLine();

        if (deleteMembershipPlan(planId)) {
            System.out.println("Membership plan deleted successfully!");
        } else {
            System.out.println("Plan not found. Deletion failed.");
        }
    }

    private void updateMembershipPlan(Scanner scanner) {
        System.out.print("Enter Plan ID to update: ");
        String planId = scanner.nextLine();
        
        MembershipPlan plan = getPlanById(planId);
        if (plan != null) {
            System.out.print("Enter new Plan Type (current: " + plan.getPlanType() + "): ");
            String newPlanType = scanner.nextLine();
            System.out.print("Enter new Plan Price (current: " + plan.getPrice() + "): ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter new Plan Status (current: " + plan.getStatus() + "): ");
            String newStatus = scanner.nextLine();

            if (updateMembershipPlan(planId, newPlanType, newPrice, newStatus)) {
                System.out.println("Membership plan updated successfully!");
            } else {
                System.out.println("Failed to update membership plan.");
            }
        } else {
            System.out.println("Plan not found.");
        }
    }

    public MembershipPlan getPlanById(String planId) {
        for (MembershipPlan plan : membershipPlans) {
            if (plan.getPlanId().equals(planId)) {
                return plan;
            }
        }
        return null;
    }

    public void addMembershipPlan(MembershipPlan plan) {
        if (plan != null) {
            membershipPlans.add(plan);
        }
    }

    public boolean updateMembershipPlan(String planId, String newPlanType, double newPrice, String newStatus) {
        MembershipPlan plan = getPlanById(planId);
        if (plan != null) {
            plan.setPlanType(newPlanType);
            plan.setPrice(newPrice);
            plan.setStatus(newStatus);
            return true;
        }
        return false;
    }

    public boolean deleteMembershipPlan(String planId) {
        MembershipPlan plan = getPlanById(planId);
        if (plan != null) {
            membershipPlans.remove(plan);
            return true;
        }
        return false;
    }

    public List<MembershipPlan> searchPlansByType(String planType) {
        List<MembershipPlan> result = new ArrayList<>();
        for (MembershipPlan plan : membershipPlans) {
            if (plan.getPlanType().equalsIgnoreCase(planType)) {
                result.add(plan);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        if (membershipPlans.isEmpty()) {
            return "No membership plans available.";
        }
        StringBuilder str = new StringBuilder(String.format("|%15s|%20s|%10s|%15s|\n", "Plan ID", "Type", "Price", "Status"));
        for (MembershipPlan plan : membershipPlans) {
            str.append(String.format("|%15s|%20s|%10.2f|%15s|\n", plan.getPlanId(), plan.getPlanType(), plan.getPrice(), plan.getStatus()));
        }
        return str.toString();
    }
}
