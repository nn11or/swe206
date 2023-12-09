import java.util.ArrayList;
import java.util.Scanner;

public class Member extends User implements Comparable<Member>{
    private ArrayList<Team> MemberTeamsList;
    private static ArrayList<Member> listOfMembers = new ArrayList<>();


    public Member(String email, String Username, ArrayList<String> researchInterest, String password) {
        super(email, Username, researchInterest, password);
        listOfMembers.add(this);
    }
    public ArrayList<Team> getMemberTeamsList() {
        return MemberTeamsList;
    }
    public static ArrayList<Member> getListOfMembers(){
        return listOfMembers;
    }
    @Override

    public void viewTeams(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a team:");
        // print teams
        int selectedTeamIndex = scanner.nextInt();
        Team selectedTeam = MemberTeamsList.get(selectedTeamIndex);
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
    public static void deleteMember(Member member) {
        listOfMembers.remove(member);
    }

    @Override
    public int compareTo(Member otherMember){
        return Integer.compare(this.MemberTeamsList.size(), otherMember.MemberTeamsList.size());
    }


}
