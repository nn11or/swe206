import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Admin extends User{
    public Admin(String type, String email, String Username, ArrayList<String> researchInterest, String password) {
        super(type, email, Username, researchInterest, password);
    }
    @Override
    public void viewTeams(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a team:");
        // Fetch the list of teams

        // Print all teams in the list
        for (int i = 0; i < Team.getListOfTeams().size(); i++) {
            System.out.println((i) + ". " + Team.getListOfTeams().get(i).getTeamName());
        }

        int selectedTeamIndex = scanner.nextInt();
        Team selectedTeam = Team.getListOfTeams().get(selectedTeamIndex);
        System.out.println("Enter 1 to view project details or 2 to view available machines:");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                viewProjectDetails(selectedTeam);
                break;
            case 2:
                viewMachinesAvailable(selectedTeam);
                break;
            case 3:
                assignMachineTimeToProject();
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public static void accessAdminList() {
        System.out.println("Admin List:");
        for (Admin admin : User.getAdmins()) {
            System.out.println("Type: Admin, Username: " + admin.getUsername() + ", Email: " + admin.getEmail());
        }
    }

}