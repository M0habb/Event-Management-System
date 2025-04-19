import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Organizer extends User implements Crud<Event>{

    private ArrayList<Event> eventsCreated;
    Organizer(){
        super();
    }
    Organizer(String userName, String password, Gender gender, Date birthDate, long phoneNumber){
        super(userName, password, gender, birthDate, phoneNumber);
        super.setRole(Role.ORGANIZER);
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

    public void createEvent(){}
    public void rentRoom(){}
    public void showAvRooms(){}
    public void showOrgEvents(){}
    public void showAttendees (){}

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