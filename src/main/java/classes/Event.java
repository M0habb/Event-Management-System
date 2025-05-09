package classes;

import java.util.Date;
import java.util.ArrayList;

public class Event {


    private String eventName;
    private Date eventDate;
    private boolean outdoors;
    private Room room;
    private int maxAttendees;
    private double fees;
    private Organizer organizer;
    private Category category;
    private ArrayList<Attendee> attendees;
    public ArrayList<String> activities = new ArrayList<>();

    public Event(){

    }
    public Event(String eventName, Date eventDate, boolean outdoors, Room room, int maxAttendees, Organizer organizer, Category category) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.outdoors = outdoors;
        this.room = room;
        this.maxAttendees = maxAttendees;
        this.organizer = organizer;
        this.category = category;
        this.room.setAvailable(false);
        this.attendees = new ArrayList<>();
        Database.events.add(this);
        organizer.getEventsCreated().add(this);
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
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
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
    public void setAttendees(ArrayList<Attendee> attendees){
        this.attendees = attendees;
    }
    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category){
        this.category = category;
    }
    public int getNumberofAttendees(){
        return attendees.size();
    }
    public ArrayList<Attendee> getAttendees(){
        return attendees;
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
    public boolean showAttendees(){
        if (attendees.isEmpty()) {
            return false;
        } else {
            System.out.println("Attendees for the event:");
            for (Attendee attendee : attendees) {
                System.out.println("- " + attendee);
            }
        }
        return true;
    }
}