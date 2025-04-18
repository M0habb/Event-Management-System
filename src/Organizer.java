import java.util.Date;
import java.util.List;

public class Organizer extends User implements Crud<Event>{

    private List<Event> eventsCreated;
    Organizer(){
        super();
    }
    Organizer(String userName, String password, Gender gender, Date birthDate, long phoneNumber){
        super(userName, password, gender, birthDate, phoneNumber);
        super.setRole(Role.ORGANIZER);
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
    public void create() {

    }

    @Override
    public void read() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}