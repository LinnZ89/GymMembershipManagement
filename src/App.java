import java.util.Scanner;
import controllers.AdminController;

public class App {
    public static void main(String[] args) {
        AdminController adminController = new AdminController();
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
                System.out.println("1. Logout");
                System.out.println("2. Exit");

                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("You have been logged out.");
                        loggedin = false;
                        break;
                    case 2:
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
