import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Team {

     private String teamName;
     private String TeamLeader;
     private ArrayList<Member> MembersOfTeam;
     private static ArrayList<Team> listOfTeams = new ArrayList<>();
     static int uniqueNumber = 10000;
     private int teamId;
     private Project project;

     public Team(String teamName,String teamLeader, ArrayList<Member> MembersOfTeam) {
         this.teamName = teamName;
         this.TeamLeader = teamLeader;
         this.MembersOfTeam = MembersOfTeam;
         uniqueNumber = uniqueNumber + 1;
         teamId = uniqueNumber;
         listOfTeams.add(this);
     }
     public String getTeamName(){
         return teamName;
     }

     public void setTeamName(String teamName){
         this.teamName = teamName;
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

    private void addMember(Member member){
        MembersOfTeam.add(member);
        member.getMemberTeamsList().add(this);
    }

    private void removeMember(Member member) {
        MembersOfTeam.remove(member);
        member.getMemberTeamsList().remove(this);
    }

    public static void writeTeamsToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Team team : listOfTeams) {
                String teamInfo = formatTeamInfo(team);
                writer.write(teamInfo + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatTeamInfo(Team team) {
        StringBuilder info = new StringBuilder("Team ID: " + team.teamId + ", Team Name: " + team.getTeamName() + ", Team Leader: " + team.TeamLeader);
        info.append(", Members: ");
        for (Member member : team.MembersOfTeam) {

            info.append(member.getUsername() + " (Teams: " + member.getMemberTeamsList().size() + "), ");
        }
        // Remove the last comma and space
        if (!team.MembersOfTeam.isEmpty()) {
            info.setLength(info.length() - 2);
        }
        return info.toString();
    }

}
