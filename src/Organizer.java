import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Organizer extends User implements Crud<Event>{

    private ArrayList<Event> eventsCreated;
    Organizer(){
        super();
    }
    Organizer(String userName, String password, Gender gender, Date birthDate, long phoneNumber){
        super(userName, password, gender, birthDate, phoneNumber);
    }

    public void setEventsCreated(ArrayList<Event> eventsCreated){
        this.eventsCreated = eventsCreated;
    }
    public ArrayList<Event> getEventsCreated(){
        return eventsCreated;
    }
    @Override
    public void signup() {

    }

    public void rentRoom(Event event, Room room){

    }
    public void showAvRooms(){
        System.out.println("Rooms available: ");
        boolean exists=false;
        for(Room room:Database.rooms) {
            if(room.getAvailable()){
                exists=true;
                System.out.println("-" + room.getRoomNum());
            }
        }
        if(!exists){
            System.out.println("none");
        }
    }
    public void showOrgUpcomingEvents(){
        int count = 1;
        for (Event event : Database.events){
            if (event.getOrganizer().equals(this) && event.getEventDate().after(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))){
                System.out.println(count + ". " + event.getEventName() + ": " + event.getEventDate());
                count++;
            }
        }
        if (count == 1){
            System.out.println("No Upcoming Events.");
        }
    }
    public void showOrgPreviousEvents(){
        int count = 1;
        for (Event event : eventsCreated){
            if (event.getEventDate().before(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))){
                System.out.println(count + ". " + event.getEventName() + ": " + event.getEventDate());
                count++;
            }
        }
        if (count == 1){
            System.out.println("No Previous Events.");
        }
    }
    public void showAttendees (){
        int count = 1;
        boolean attendees = false;
        for (Event event : eventsCreated){
            System.out.println(count + ". " + event.getEventName() + ": ");
            if(event.showAttendees()) attendees = true;
            count++;
        }
        if (count == 1)
            System.out.println("No Events created");
        else if (!attendees)
            System.out.println("No Attendees.");
    }

    @Override
    public void create(Event event) {

    }

    @Override
    public void read() {

    }

    @Override
    public void update(Event event) {

    }

    @Override
    public void delete(int i) { // Parameter Come Back Later

    }
}