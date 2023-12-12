import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you a regular user or an administrator? Enter '1' for regular user, '2' for administrator:");
        int userTypeChoice = scanner.nextInt();
        scanner.nextLine();
        User currentUser;

        if (userTypeChoice == 1) {
            currentUser = new Member("RegularUserEmail", "RegularUserName", new ArrayList<>(), "RegularUserPassword");
        } else if (userTypeChoice == 2) {
            currentUser = new Admin("AdminUserEmail", "AdminUserName", new ArrayList<>(), "AdminUserPassword");
        } else {
            System.out.println("Invalid choice. Exiting...");
            return;
        }

        while (true) {
            displayMainMenu(currentUser);

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    currentUser.viewTeams();
                    break;
                case 2:
                    // project
                    createNewProject(scanner);
                    break;
                case 3:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }


    public static void displayMainMenu(User currentUser) {
        System.out.println("Welcome, " + currentUser.getUsername() + "!");
        System.out.println("Choose an option:");
        System.out.println("1. View Teams");
        System.out.println("2. Assign Machine Time to Project");
        System.out.println("3. Exit");
    }
    private static void createNewProject(Scanner scanner) {
        System.out.println("Enter the name of the new project:");
        String projectName = scanner.nextLine();

        String result = Project.UniqueName(projectName);
        System.out.println(result);
    }
}