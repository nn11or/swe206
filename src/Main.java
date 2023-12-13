import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args){

        Machine machine1 = new Machine("Machine 1", "Usage 1");
        Machine machine2 = new Machine("Machine 2", "Usage 2");

        // Initialize members
        ArrayList<String> researchInterests1 = new ArrayList<>();
        researchInterests1.add("Research Interest 1");
        Member member1 = new Member("member1@example.com", "Member1", researchInterests1, "password1");

        ArrayList<String> researchInterests2 = new ArrayList<>();
        researchInterests2.add("Research Interest 2");
        Member member2 = new Member("member2@example.com", "Member2", researchInterests2, "password2");



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
        System.out.println("2. Create New Project and it Team");
        System.out.println("3. Exit");
    }
    private static void createNewProject(Scanner scanner) {
        System.out.println("Enter the name of the new project:");
        String projectName = scanner.nextLine();
        String result = Project.UniqueName(projectName);

        if (result.equals("Name has been saved successfully")) {
            System.out.println(result);

            System.out.println("Enter the name of the team for the project:");
            String teamName = scanner.nextLine();
            System.out.println("Enter the name of the team leader:");
            String teamLeader = scanner.nextLine();
            ArrayList<Member> availableMembers = Member.getListOfMembers();

            // Display available members to choose from
            System.out.println("Available Members:");
            for (int i = 0; i < availableMembers.size(); i++) {
                System.out.println((i) + ". " + availableMembers.get(i).getUsername());
            }

            // Prompt user to select members for the team
            System.out.println("Enter the indices of members (comma-separated) to add to the team:");
            String selectedMembersInput = scanner.nextLine();
            String[] selectedMembersIndices = selectedMembersInput.split(",");

            ArrayList<Member> selectedMembers = new ArrayList<>();
            for (String index : selectedMembersIndices) {
                int selectedIndex = Integer.parseInt(index.trim()) - 1;
                if (selectedIndex >= 0 && selectedIndex < availableMembers.size()) {
                    selectedMembers.add(availableMembers.get(selectedIndex));
                }
            }


            Team newTeam = new Team(teamName,teamLeader,selectedMembers);
            Project newProject = new Project(projectName);
            newProject.setTeam(newTeam);
            newTeam.setProject(newProject);

            System.out.println("Team '" + teamName + "' has been created and assigned to the project '" + projectName + "'.");
        }
        else{
            System.out.println(result);
        }
    }



}