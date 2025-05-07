package classes;
import java.util.Date;
public class Ticket {
    private Attendee owner;
    private double fees;
    private Date date;
    private String eventName;
    public Ticket(String eventName, Attendee owner, double fees, Date date){
        this.eventName=eventName;
        this.owner=owner;
        this.fees=fees;
        this.date=date;

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public Attendee getOwner() {
        return owner;
    }

    public Date getDate() {
        return date;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public void setOwner(Attendee owner) {
        this.owner = owner;
    }

}
