import java.util.ArrayList;
import java.util.Scanner;

public class Team {
     private String TeamLeader;
     private ArrayList<Member> MembersOfTeam;
     private static ArrayList<Team> listOfTeams = new ArrayList<>();
    static int uniqueNumber = 10000;
    private int teamId;



    private Project project;

     public Team(String teamLeader, ArrayList<String> teamMembers) {
        this.TeamLeader = teamLeader;
        this.MembersOfTeam = new ArrayList<>();
         uniqueNumber = uniqueNumber + 1;
         teamId = uniqueNumber;
         listOfTeams.add(this);
     }

    public Project getProject(){
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public void setTeamLeader(String teamLeader) {
        TeamLeader = teamLeader;
    }
    public String getTeamLeader(){return TeamLeader;}

    public ArrayList<Member> getMembersOfTeam() {
        return MembersOfTeam;
    }
    public static ArrayList<Team> getListOfTeams(){
        return listOfTeams;
    }

    public static void addTeam(Team team) {
        listOfTeams.add(team);
    }

    public static void removeTeam(Team team) {
        listOfTeams.remove(team);
    }

    public void addMember(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a Member:");
        // print allMember
        int selectedMemberIndex = scanner.nextInt();
        Member selectedMember = Member.getListOfMembers().get(selectedMemberIndex);
        addMember(selectedMember);
    }
    public void removeMember(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a Member:");
        // print Members Of the Team
        int selectedMemberIndex = scanner.nextInt();
        Member selectedMember = getMembersOfTeam().get(selectedMemberIndex);
        removeMember(selectedMember);
    }

    private void addMember(Member member) {
        MembersOfTeam.add(member);
        member.getMemberTeamsList().add(this);
    }

    private void removeMember(Member member) {
        MembersOfTeam.remove(member);
        member.getMemberTeamsList().remove(this);
    }

}
