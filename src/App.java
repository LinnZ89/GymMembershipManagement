import java.util.Scanner;
import controllers.*;

public class App {
    public static void main(String[] args) {
        AdminController adminController = new AdminController();
        MembershipPlanController membershipController = new MembershipPlanController();
        TrainerController trainerController = new TrainerController(); // Assuming you have this controller
        TrainingSessionController sessionController = new TrainingSessionController(); // Assuming you have this controller
        MemberController memberController = new MemberController(); // Assuming you have this controller
        PaymentController paymentController = new PaymentController(); // Assuming you have this controller

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            boolean loggedin = false;

            // Login loop
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

            // Main menu loop
            while (loggedin) {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Manage Membership Plans");
                System.out.println("2. Manage Trainers");
                System.out.println("3. Manage Training Sessions");
                System.out.println("4. Manage Members");
                System.out.println("5. Schedule Training Sessions");
                System.out.println("6. Manage Payments");
                System.out.println("7. Logout");
                System.out.println("8. Exit");

                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        membershipController.menu(); // Assuming this opens a membership menu with further options
                        break;

                    case 2:
                        System.out.println("Manage Trainers");
                        trainerController.menu(); // Assuming you have a menu method for managing trainers
                        break;

                    case 3:
                        System.out.println("Manage Training Sessions");
                        sessionController.menu(); // Assuming you have a menu method for managing training sessions
                        break;

                    case 4:
                        System.out.println("Manage Members");
                        memberController.menu(); // Assuming you have a menu method for managing members
                        break;

                    case 5:
                        System.out.println("Schedule Training Sessions");
                        sessionController.scheduleSession(); // Assuming you have a method to schedule sessions
                        break;

                    case 6:
                        System.out.println("Manage Payments");
                        paymentController.menu(); // Assuming you have a menu method for managing payments
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
}
