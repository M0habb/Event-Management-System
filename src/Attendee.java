import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Attendee extends User{

    private List<Category> interests;
    private Address address;
    private List<Ticket> ticketsOwned;
    Attendee(){
        super();
    }
    Attendee(String userName, String password, Gender gender, Date birthDate, long phoneNumber) throws IOException {
        super(userName, password, gender, birthDate, phoneNumber);
        super.setRole(Role.ATTENDEE);
    }

    @Override
    public void signup() {

    }

    public void chooseEvent(){}
    public void buyTickets(){}
}