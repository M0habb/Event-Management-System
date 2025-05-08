package classes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Attendee extends User{

    private List<Category> interests;
    private Address address;
    private ArrayList<Event> eventsAttended;
    private Attendee attendee;
    public Attendee(){
        super();
        this.eventsAttended = new ArrayList<>();
    }
    public Attendee(String userName, String password, Gender gender, Date birthDate, long phoneNumber, Address address) {
        super(userName, password, gender, birthDate, phoneNumber);
        this.eventsAttended = new ArrayList<>();
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Category> getInterests() {
        return interests;
    }

    public void setInterests(List<Category> interests) {
        this.interests = interests;
    }

    public ArrayList<Event> getEventsAttended() {
        return eventsAttended;
    }

    public void setEventsAttended(ArrayList<Event> eventsAttended) {
        this.eventsAttended = eventsAttended;
    }

    public void signup(String username, Gender gender,  Date birthDate, Address address, long phonenumber, String password) {

        this.setUserName(username);
        this.setPassword(password);
        this.setPhoneNumber(phonenumber);
        this.address = address;
        this.setBirthDate(birthDate);

        Database.totalAttendees.add(this);

    }

    public boolean StrongPasswordValidation(String password)
    {
        if (password.length() < 8)
            return false;

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialCharacter = false;

        for (int i = 0; i < password.length(); ++i)
        {
            if (Character.isUpperCase(password.charAt(i)))
                hasUppercase = true;

            else if (Character.isLowerCase(password.charAt(i)))
                hasLowercase = true;

            else if (Character.isDigit(password.charAt(i)))
                hasDigit = true;

            else
                hasSpecialCharacter = true;
        }

        if (!hasUppercase)
            return false;
        if (!hasLowercase)
            return false;
        if (!hasDigit)
            return false;
        if (!hasSpecialCharacter)
            return false;

        return true;
    }


    public void chooseEvent(){}
    public int showUpcomingEvents() {
        int count = 1;
        for (Event event : Database.events) {
            if (event.getEventDate().after(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))) {
                System.out.println(count + ". " + event.getEventName() + ": " + event.getEventDate());
                count++;
            }
        }
        if (count == 1) {
            System.out.println("No Upcoming Events.");
            return 0;
        }
        return count - 1;
    }
    public void buyTickets(Event event){
        Ticket ticket=new Ticket(event.getEventName(),attendee,event.getFees(),event.getEventDate());

        if(this.getWallet().deductBalance(event.getFees())){
            System.out.println("Ticket purchased!");
            event.getAttendees().add(this);
            event.getOrganizer().getWallet().addBalance(event.getFees());
            eventsAttended.add(event);
        }else if(!this.getWallet().deductBalance(event.getFees())){
            System.out.println("Insufficient Funds!");
        }
        Database.tickets.add(ticket);
    }
    public void showEventsAttended(){
        int count = 0;
        for (Event event : eventsAttended){
            count++;
            System.out.println(count + ". " + event.getEventName() + ", " + event.getEventDate());
        }
        if (count == 0){
            System.out.println("No events attended.");
        }
    }
    public void showPersonalDetails(){
        System.out.println("Username: "+ getUserName());
        System.out.println("Gender: "+ getGender());
        System.out.println("Address: "+ getAddress());
        System.out.println("BirthDate: "+ getBirthDate());
        System.out.println("PhoneNumber: "+ getPhoneNumber());
        System.out.println("Wallet Balance: " + getWallet().getBalance());
    }
}