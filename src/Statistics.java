import java.util.ArrayList;
import java.util.Collections;

public class Statistics {

    public void viewMostUtilizedMachines() {
        ArrayList<Machine> ListOfMachines = Machine.getListOfMachines();
        Collections.sort(ListOfMachines);
        // top 5
        for(int i =0; i < 5 && i < ListOfMachines.size(); i++){
            System.out.println(ListOfMachines.get(i).getMachineName());
        }
    }
    public void viewMostActiveMembers(){
        ArrayList<Member> ListOfMember = Member.getListOfMembers();
        Collections.sort(ListOfMember);
        // top 5
        for(int i =0;  i < 5 && i < ListOfMember.size(); i++){
            System.out.println(ListOfMember.get(i).getUsername());
        }
    }
    public String projectThatUsesTheMostMachines() {
        ArrayList<Project> ListOfProject = Project.getListOfProjects();
        Collections.sort(ListOfProject);
        return ListOfProject.get(0).getProjectName();
    }

}
