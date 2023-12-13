import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Reservation {
    private Machine machine;
    private Project project;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    static ArrayList<Reservation> listOfReservation = new ArrayList<>();
    static int uniqueNumber = 10000;
    private int reservationId;

    // do i need set and get for the project ?
    public Reservation(Machine machine, Project project, LocalDate date, LocalTime startTime, LocalTime endTime){
        this.machine = machine;
        this.project = project;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean overlapsWith(Reservation newReservation) {
        LocalDate newReservationDate = newReservation.getDate();
        LocalTime newReservationStartTime = newReservation.getStartTime();
        LocalTime newReservationEndTime = newReservation.getEndTime();
        if (!this.date.equals(newReservationDate)) {
            return false;
        }

        if (this.startTime.isBefore(newReservationEndTime) && this.endTime.isAfter(newReservationStartTime)) {
            return true;
        }

        return false;
    }
}
