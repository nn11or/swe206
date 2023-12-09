import java.util.ArrayList;

public class Machine implements Comparable<Machine>{
    private String machineName;
    private String SuggestedUsage;
    private static ArrayList<Machine> listOfMachines = new ArrayList<>();
    private int numberOfUsage;

    public Machine(String machineName, String SuggestedUsage) {
        this.machineName = machineName;
        this.SuggestedUsage = SuggestedUsage;
        listOfMachines.add(this);
        numberOfUsage = 0;
    }
    public void setSuggestedUsage(String suggestedUsage){
        this.SuggestedUsage = suggestedUsage;
    }
    public String getSuggestedUsage(){
        return SuggestedUsage;
    }
    public int getNumberOfUsage(){
        return numberOfUsage;
    }

    public void setNumberOfUsage(int numberOfUsage){
        this.numberOfUsage = numberOfUsage;
    }
    public String getMachineName(){
        return machineName;
    }
    public void setMachineName(String machineName){
        this.machineName = machineName;
    }
    public static ArrayList<Machine> getListOfMachines() {
        return listOfMachines;
    }


    public static void removeMachine(Machine machine) {
        listOfMachines.remove(machine);
    }

    @Override
    public int compareTo(Machine otherMachine) {
        return Integer.compare(this.numberOfUsage, otherMachine.numberOfUsage);
    }
}
