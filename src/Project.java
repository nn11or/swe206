import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Project implements Comparable<Project>{
    private String projectName;
    private ArrayList<Machine> AvailableMachines;
    private static ArrayList<Project> listOfProjects = new ArrayList<>();
    private static List<String> listOfTheProjectsName = new ArrayList<>(); //New

    private Team team;
    static int uniqueNumber = 10000;
    private int projectId;


    public Project(String projectName) {
        this.projectName = projectName;
        this.AvailableMachines = new ArrayList<>();
        uniqueNumber = uniqueNumber + 1;
        projectId = uniqueNumber;
        listOfProjects.add(this);
    }
    public static String UniqueName(String projectName){
        if (listOfTheProjectsName.contains(projectName)) {
            return "Name has already been taken";
        }else{
            listOfTheProjectsName.add(projectName);
            return "Name has been saved successfully";
        }
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
