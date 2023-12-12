import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
public class User {
    private String Email;
    private String Username;
    private ArrayList<String> ResearchInterest; // TO THE member
    private String Password;



    public User(String email, String username, ArrayList<String> researchInterest,String password) {
        this.Email = email;
        this.Username = username;
        this.ResearchInterest = researchInterest;
        this.Password = password;
    }
    public String getEmil(){
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
    public void assignMachineTimeToProject(Machine machine,Project project, LocalDate date,LocalTime startTime, LocalTime endTime){
        Reservation newReservation = new Reservation(machine, project, date, startTime, endTime);
        boolean isOverlap = true;
        for (Reservation existingReservation : Reservation.listOfReservation){
            if (existingReservation.overlapsWith(newReservation)) {
                isOverlap = false;
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


}
