import java.util.Date;
import java.util.List;

public class Event {

    private String eventName;
    private Date eventDate;
    private boolean outdoors;
    private Address location;
    private int maxAttendees;
    private double fees;
    private Organizer organizer;
    private Category category;
    private List<Attendee> attendees;

    public Event(String eventName, Date eventDate, boolean outdoors, Address location, int maxAttendees, Organizer organizer, Category category) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.outdoors = outdoors;
        this.location = location;
        this.maxAttendees = maxAttendees;
        this.organizer = organizer;
        this.category = category;
    }

    //Setters & Getters
    public Date getEventDate() {
        return eventDate;
    }
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
    public boolean getOutdoors() {
        return outdoors;
    }
    public void setOutdoors(boolean outdoors) {
        this.outdoors = outdoors;
    }
    public Address getLocation() {
        return location;
    }
    public void setLocation(Address location) {
        this.location = location;
    }
    public int getMaxAttendees() {
        return maxAttendees;
    }
    public void setMaxAttendees(int maxAttendees) {
        this.maxAttendees = maxAttendees;
    }
    public double getFees() {
        return fees;
    }
    public void setFees(double fees) {
        this.fees = fees;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setOrganizer(Organizer organizer){
        this.organizer = organizer;
    }
    public Organizer getOrganizer(){
        return organizer;
    }
    public void setAttendees(List<Attendee> attendees){
        this.attendees = attendees;
    }
    public List<Attendee> getAttendees(){
        return attendees;
    }
    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category){
        this.category = category;
    }

    public void showActivities(){
        System.out.println("Activities for the event:");
        if (activities.isEmpty()) {
            System.out.println("No activities scheduled.");
        } else {
            for (String activity : activities) {
                System.out.println("- " + activity);
            }
        }
    }
    public void showAttendees(){
        System.out.println("Attendees for the event:");
        if (attendees.isEmpty()) {
            System.out.println("No attendees yet.");
        } else {
            for (Attendee attendee : attendees) {
                System.out.println("- " + attendee);
            }
        }
    }
}