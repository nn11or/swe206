import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class User {
    private String Type;
    private String Email;
    private String Username;
    private ArrayList<String> ResearchInterest; // TO THE member
    private String Password;



    public User(String type, String email, String username, ArrayList<String> researchInterest,String password) {
        this.Type = type;
        this.Email = email;
        this.Username = username;
        this.ResearchInterest = researchInterest;
        this.Password = password;
    }
    public String getType(){
        return Type;
    }
    public void setType(String type){
        this.Type = type;
    }
    public String getEmail(){
        return Email;
    }
    public void setEmail(String email){
        this.Email = email;
    }
    public String getUsername(){
        return Username;
    }
    public void setUsername(String username){
        this.Username = username;
    }
    public ArrayList<String> getResearchInterest(){
        return ResearchInterest;
    }
    public void setResearchInterest(ArrayList<String> researchInterest){
        this.ResearchInterest = researchInterest;
    }
    public String getPassword(){
        return Password;
    }
    public void setPassword(String password){
        this.Password = password;
    }
    public void viewTeams(){
        return;
    }
    public void viewProjectDetails(Team selectedTeam){
        System.out.println(selectedTeam.getProject());
    }


    public void viewMachinesAvailable(Team selectedTeam){
        System.out.println(selectedTeam.getProject().getAvailableMachines());
    }
    public void assignMachineTimeToProject() {
        Scanner scanner = new Scanner(System.in);

        // Display machines and get user selection
        ArrayList<Machine> machines = Machine.getListOfMachines();
        if (machines.isEmpty()) {
            System.out.println("No machines available.");
            return;
        }
        for (int i = 0; i < machines.size(); i++) {
            Machine machine = machines.get(i);
            System.out.println(i + ". " + machine.getMachineName() + " (ID: " + machine.getMachineId() + ", Suggested Usage: " + machine.getSuggestedUsage() + ")");
        }

        System.out.println("Select a machine by index:");
        int machineIndex = scanner.nextInt();
        if (machineIndex < 0 || machineIndex >= machines.size()) {
            System.out.println("Invalid machine index.");
            return;
        }
        Machine selectedMachine = machines.get(machineIndex);

        // Display projects and get user selection
        ArrayList<Project> projects = Project.getListOfProjects();
        if (projects.isEmpty()) {
            System.out.println("No projects available.");
            return;
        }
        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);
            System.out.println(i + ". " + project.getProjectName());
        }

        System.out.println("Select a project by index:");
        int projectIndex = scanner.nextInt();
        if (projectIndex < 0 || projectIndex >= projects.size()) {
            System.out.println("Invalid project index.");
            return;
        }
        Project selectedProject = projects.get(projectIndex);

        // Get reservation details
        System.out.println("Enter the reservation date (YYYY-MM-DD):");
        LocalDate date = LocalDate.parse(scanner.next());

        System.out.println("Enter the start time (HH:MM):");
        LocalTime startTime = LocalTime.parse(scanner.next());

        System.out.println("Enter the end time (HH:MM):");
        LocalTime endTime = LocalTime.parse(scanner.next());

        // Call the private helper method
        assignMachineTimeToProject(selectedMachine, selectedProject, date, startTime, endTime);
    }
    private void assignMachineTimeToProject(Machine machine,Project project, LocalDate date,LocalTime startTime, LocalTime endTime){
        Reservation newReservation = new Reservation(machine, project, date, startTime, endTime);
        boolean isOverlap = false;
        for (Reservation existingReservation : Reservation.listOfReservation){
            if (existingReservation.overlapsWith(newReservation)) {
                isOverlap = true;
                break;
            }
        }
        if (!isOverlap) {
            project.addMachine(machine);
            machine.setNumberOfUsage(machine.getNumberOfUsage()+1);
            Reservation.listOfReservation.add(newReservation);
            System.out.println("Reservation successful for machine: " + machine.getMachineName() +
                    " for project: " + project.getProjectName() +
                    " from " + startTime + " to " + endTime + " on " + date);
        } else {
            System.out.println("Cannot make reservation. Overlaps with an existing reservation.");
        }

    }

    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Member> members = new ArrayList<>();

    public static void readUsersFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                ArrayList<String> interests = new ArrayList<>(Arrays.asList(parts).subList(3, parts.length));
                if ("Admin".equals(parts[0])) {
                    admins.add(new Admin(parts[0], parts[2], parts[1], interests, parts[3]));
                } else if ("Member".equals(parts[0])) {
                    members.add(new Member(parts[0], parts[2], parts[1], interests, parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Accessor methods for admins and members
    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static ArrayList<Member> getMembers() {
        return members;
    }
}
