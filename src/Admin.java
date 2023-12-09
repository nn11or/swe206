import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Admin extends User{
    public Admin(String email, String Username, ArrayList<String> researchInterest, String password) {
        super(email, Username, researchInterest, password);
    }

    @Override
    public void viewTeams(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a team:");
        // print teams
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
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

}
