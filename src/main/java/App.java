import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import controllers.*;
import models.*;

public class App {
    public static void main(String[] args) {
        AdminController adminController = new AdminController();
        MembershipPlanController membershipController = new MembershipPlanController();
        TrainerController trainerController = new TrainerController();
        TrainingSessionController sessionController = new TrainingSessionController();
        MemberController memberController = new MemberController();
        PaymentController paymentController = new PaymentController();
        MemberProgressController progressController = new MemberProgressController();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            boolean loggedin = false;

            while (!loggedin) {
                System.out.println("Please login!");

                System.out.print("Enter admin username: ");
                String username = scanner.nextLine();

                System.out.print("Enter admin password: ");
                String password = scanner.nextLine();

                if (adminController.login(username, password)) {
                    System.out.println("Login successful!");
                    loggedin = true;
                } else {
                    System.out.println("Login failed! Please try again.");
                }
            }

            while (loggedin) {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Manage Membership Plans");
                System.out.println("2. Manage Trainers");
                System.out.println("3. Manage Training Sessions");
                System.out.println("4. Manage Members");
                System.out.println("5. Manage Member Progress");
                System.out.println("6. Manage Payments");
                System.out.println("7. Logout");
                System.out.println("8. Exit");

                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        manageMembershipPlans(membershipController, scanner);
                        break;

                    case 2:
                        manageTrainers(trainerController, scanner);
                        break;

                    case 3:
                        manageTrainingSessions(sessionController, scanner);
                        break;

                    case 4:
                        manageMembers(memberController, scanner);
                        break;

                    case 5:
                        manageMemberProgress(progressController, scanner);
                        break;

                    case 6:
                        managePayments(paymentController, scanner);
                        break;

                    case 7:
                        System.out.println("You have been logged out.");
                        loggedin = false;
                        break;

                    case 8:
                        System.out.println("Exiting application...");
                        loggedin = false;
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }

        scanner.close();
        System.out.println("Application closed.");
    }

    private static void manageMembershipPlans(MembershipPlanController membershipController, Scanner scanner) {
        System.out.println("Managing Membership Plans");
        boolean running = true;
        while (running) {
            System.out.println("\n--- Membership Plan Menu ---");
            System.out.println("1. Create Membership Plan");
            System.out.println("2. Update Membership Plan");
            System.out.println("3. Delete Membership Plan");
            System.out.println("4. View All Membership Plans");
            System.out.println("5. Back to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    membershipController.createMembershipPlan(scanner);
                    break;
                case 2:
                    membershipController.updateMembershipPlan(scanner);
                    break;

                case 3:
                    System.out.print("Enter Membership Plan's ID: ");
                    String membershipPlanId = scanner.nextLine();
                    membershipController.deleteMembershipPlan(membershipPlanId);
                    break;

                case 4:
                    membershipController.viewAllMembershipPlans();
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void manageTrainers(TrainerController trainerController, Scanner scanner) {
        System.out.println("Managing Trainers");
        boolean running = true;
        while (running) {
            System.out.println("\n--- Trainer Menu ---");
            System.out.println("1. Add Trainer");
            System.out.println("2. Update Trainer");
            System.out.println("3. Delete Trainer");
            System.out.println("4. View All Trainers");
            System.out.println("5. Back to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    trainerController.createTrainer(scanner);
                    break;

                case 2:
                    System.out.print("Enter Trainer's ID: ");
                    String updateTrainerId = scanner.nextLine();

                    System.out.print("Enter New Trainer's Name: ");
                    String newTrainerName = scanner.nextLine();

                    System.out.print("Enter New Trainer's Specialization: ");
                    String newTrainerSpecialization = scanner.nextLine();

                    trainerController.updateTrainer(updateTrainerId, newTrainerName, newTrainerSpecialization);
                    break;

                case 3:
                    System.out.print("Enter Trainer's ID: ");
                    String deleteTrainerId = scanner.nextLine();
                    trainerController.deleteTrainer(deleteTrainerId);
                    scanner.nextLine();
                    break;

                case 4:
                    trainerController.viewAllTrainers();
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void manageTrainingSessions(TrainingSessionController sessionController, Scanner scanner) {
        System.out.println("Managing Training Sessions");
        boolean running = true;
        while (running) {
            System.out.println("\n--- Training Session Menu ---");
            System.out.println("1. Create Training Session");
            System.out.println("2. Update Training Session");
            System.out.println("3. Delete Training Session");
            System.out.println("4. View All Training Sessions");
            System.out.println("5. Back to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    sessionController.createTrainingSession(scanner);
                    break;

                case 2:
                    System.out.print("Enter Training Session's ID: ");
                    String updateTrainingSessionID = scanner.nextLine();

                    System.out.print("Enter New Training Session's Location: ");
                    String newTrainingSessionLocation = scanner.nextLine();

                    System.out.print("Enter New Training Session's Trainer ID: ");
                    String newTrainingSessionTrainerID = scanner.nextLine();

                    sessionController.updateTrainingSession(updateTrainingSessionID, newTrainingSessionLocation, newTrainingSessionTrainerID);
                    break;

                case 3:
                    System.out.print("Enter Training Session's ID: ");
                    String deleteTrainingSessionID = scanner.nextLine();
                    sessionController.deleteTrainingSession(deleteTrainingSessionID);
                    break;

                case 4:
                    sessionController.viewAllTrainingSessions();
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void manageMembers(MemberController memberController, Scanner scanner) {
        System.out.println("Managing Members");
        boolean running = true;
        while (running) {
            System.out.println("\n--- Member Menu ---");
            System.out.println("1. Add Member");
            System.out.println("2. Update Member");
            System.out.println("3. Delete Member");
            System.out.println("4. View All Members");
            System.out.println("5. Back to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    memberController.createMember(scanner);
                    break;

                case 2:
                    System.out.print("Enter Member's ID: ");
                    String updateMemberId = scanner.nextLine();
                    scanner.nextLine();

                    System.out.print("Enter New Member's Name: ");
                    String newMemberName = scanner.nextLine();

                    System.out.print("Enter New Member's Email: ");
                    String newMemberEmail = scanner.nextLine();

                    System.out.print("Enter New Member's Membership Plan ID: ");
                    String newMembershipPlanID = scanner.nextLine();

                    memberController.updateMember(updateMemberId, newMemberName, newMemberEmail, newMembershipPlanID);
                    break;

                case 3:
                    System.out.print("Enter Member's ID: ");
                    String deleteMemberId = scanner.nextLine();
                    scanner.nextLine();
                    memberController.deleteMember(deleteMemberId);
                    break;

                case 4:
                    memberController.viewAllMembers();
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void manageMemberProgress(MemberProgressController memberProgressController, Scanner scanner) {
        System.out.println("Managing Member Progress");
        boolean running = true;
        while (running) {
            System.out.println("\n--- Member Progress Menu ---");
            System.out.println("1. Add Member Progress");
            System.out.println("2. Update Member Progress");
            System.out.println("3. Delete Member Progress");
            System.out.println("4. View All Member Progresses");
            System.out.println("5. View Member Progress By ID");
            System.out.println("6. Back to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    memberProgressController.createMemberProgress(scanner);
                    break;

                case 2:
                    System.out.print("Enter Member Progress ID: ");
                    String updateMemberProgressId = scanner.nextLine();
                    memberProgressController.updateMemberProgress(updateMemberProgressId, scanner);
                    break;

                case 3:
                    System.out.print("Enter Member Progress ID: ");
                    String deleteMemberProgressId = scanner.nextLine();
                    memberProgressController.deleteMemberProgress(deleteMemberProgressId);
                    break;

                case 4:
                    memberProgressController.viewAllMemberProgress();
                    break;

                case 5:
                    System.out.print("Enter Progress ID: ");
                    String progressId = scanner.nextLine();
                    MemberProgress memberProgress = memberProgressController.getProgressById(progressId);
                    if (memberProgress != null) {
                        System.out.println(memberProgress);
                    } else {
                        System.out.println("Progress not found.");
                    }
                    break;

                case 6:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void managePayments(PaymentController paymentController, Scanner scanner) {
        System.out.println("Managing Payments");
        boolean running = true;
        while (running) {
            System.out.println("\n--- Payment Menu ---");
            System.out.println("1. Add Payment");
            System.out.println("2. Update Payment");
            System.out.println("3. Delete Payment");
            System.out.println("4. View All Payments");
            System.out.println("5. Search Payments by Member ID");
            System.out.println("6. Back to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    paymentController.createPayment(scanner);
                    break;

                case 2:
                    System.out.print("Enter Payment ID to update: ");
                    String paymentId = scanner.nextLine();

                    System.out.print("Enter New Payment Date (dd/MM/yyyy): ");
                    Date newDatePay = null;
                    try {
                        newDatePay = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
                    } catch (ParseException e) {
                        System.out.println("Invalid date format.");
                        break;
                    }

                    System.out.print("Enter New Loan Amount: ");
                    int newLoan = scanner.nextInt();

                    System.out.print("Enter New Money Paid: ");
                    int newMoneyPaid = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter New Payment Status: ");
                    String newPaymentStatus = scanner.nextLine();

                    System.out.print("Enter New Payment Method: ");
                    String newPaymentMethod = scanner.nextLine();

                    paymentController.updatePayment(paymentId, newDatePay, newLoan, newMoneyPaid, newPaymentStatus, newPaymentMethod);
                    break;

                case 3:
                    System.out.print("Enter Payment ID to delete: ");
                    paymentId = scanner.nextLine();
                    paymentController.deletePayment(paymentId);
                    break;

                case 4:
                    paymentController.viewAllPayments();
                    break;

                case 5:
                    System.out.print("Enter Member ID to search payments: ");
                    String memberId = scanner.nextLine();
                    List<Payment> payments = paymentController.searchPaymentsByMemberId(memberId);
                    if (!payments.isEmpty()) {
                        for (Payment payment : payments) {
                            System.out.println(payment);
                        }
                    } else {
                        System.out.println("No payments found for this member.");
                    }
                    break;

                case 6:
                    running = false;
                    System.out.println("Exiting Payment Menu...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
