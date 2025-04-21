import java.security.spec.RSAOtherPrimeInfo;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Attendee extends User{

    private List<Category> interests;
    private Address address;
    private ArrayList<Event> eventsAttended;
    Attendee(){
        super();
    }
    Attendee(String userName, String password, Gender gender, Date birthDate, long phoneNumber, Address address) {
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

    @Override
    public void signup() {
        Scanner sc = new Scanner(System.in);
        String username, password;

        System.out.println("Enter your username: ");
        username = sc.nextLine();
        boolean exists = false;
        do{
            exists = false;
            for (Attendee attendee : Database.totalAttendees){
                if (attendee.getUserName().equals(username)){
                    exists = true;
                    System.out.println("Username already exists!");
                    System.out.println("Enter your username: ");
                    username = sc.nextLine();
                }
            }
        } while (exists);

        System.out.println("Enter your password. Password must be at least 8 characters long, contains at least one uppercase character, one lowercase character, one digit, and one special character: ");
        password = sc.nextLine();
        while (!StrongPasswordValidation(password)){
            System.out.println("Password must be at least 8 characters long, contains at least one uppercase character, one lowercase character, one digit, and one special character: ");
            password = sc.nextLine();
        }

        System.out.println("Gender Selection: ");
        System.out.println("1. Male");
        System.out.println("2. Female");
        int input = sc.nextInt();
        switch (input){
            case 1:
                this.setGender(Gender.MALE);
                break;
            case 2:
                this.setGender(Gender.FEMALE);
                break;
        }

        System.out.println("Enter Birth Year(1900-2015): ");
        int year = sc.nextInt();
        boolean validYear = false;
        while (!validYear){
            validYear = true;
            if (year < 1990 || year > 2015){
                validYear = false;
                System.out.println("Invalid year! Input year between 1990 and 2015: ");
                year = sc.nextInt();
            }
        }

        System.out.println("Enter Birth Month: ");
        int month = sc.nextInt();
        boolean validMonth = false;
        while (!validMonth){
            validMonth = true;
            if (month < 1 || month > 12){
                validMonth = false;
                System.out.println("Invalid month! Input month between 1 and 12: ");
                month = sc.nextInt();
            }
        }

        System.out.println("Enter Birth Day: ");
        int day = sc.nextInt();
        boolean validDay = false;
        while (!validDay){
            validDay = true;
            if (day < 1 || day > 31){
                validDay = false;
                System.out.println("Invalid day! Input day between 1 and 31: ");
                day = sc.nextInt();
            }
        }

        Date birthDate = new Date(year, month, day);

        System.out.println("Enter phone number: ");
        long phonenumber = sc.nextLong();
        System.out.println("Enter your country: ");
        sc.nextLine();
        String country = sc.nextLine();
        System.out.println("Enter your city: ");
        String city = sc.nextLine();
        System.out.println("Enter your street: ");
        String street = sc.nextLine();
        System.out.println("Enter your postal code: ");
        long postalCode = sc.nextLong();
        Address address = new Address(country, city, street, postalCode);

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
        int i = this.getWallet().deductBalance(event.getFees());
        if(i == 0){
            System.out.println("Ticket purchased!");
            event.getAttendees().add(this);
            eventsAttended.add(event);
        }else if(i == 2){
            System.out.println("Insufficient Funds!");
        }
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
    }
}