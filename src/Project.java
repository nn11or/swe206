import java.util.ArrayList;
import java.util.Scanner;

public class Project implements Comparable<Project>{
    private String projectName;
    private ArrayList<Machine> AvailableMachines;
    private static ArrayList<Project> listOfProjects = new ArrayList<>();
    private Team team;

    public Project(String projectName) {
        this.projectName = projectName;
        this.AvailableMachines = new ArrayList<>();
        listOfProjects.add(this);
    }

    public void setTeam(Team team){
        this.team = team;
    }
    public Team getTeam(){
        return team;
    }

    public void AssignTeam(){
        Scanner scanner = new Scanner(System.in);
        // print all teams
        System.out.println("Select a team to assign:");
        int selectedTeamIndex = scanner.nextInt();
        Team selectedTeam = Team.getListOfTeams().get(selectedTeamIndex);
        // error if team have another project
        setTeam(selectedTeam);
        selectedTeam.setProject(this);

    }
    public String getProjectName(){
        return projectName;
    }
    public ArrayList<Machine> getAvailableMachines() {
        return AvailableMachines;
    }
    public void addMachine(Machine machine) {
        AvailableMachines.add(machine);
    }

    public void removeMachine(Machine machine) {
        AvailableMachines.remove(machine);
    }


    public static ArrayList<Project> getListOfProjects() {
        return listOfProjects;
    }
    public static void removeProject(Project project) {
        listOfProjects.remove(project);
    }

    @Override
    public String toString(){
        return "The Project name is: " + projectName ;
    }
    @Override
    public int compareTo(Project otherProject) {
        return Integer.compare(this.getAvailableMachines().size(), otherProject.getAvailableMachines().size());
    }
}
