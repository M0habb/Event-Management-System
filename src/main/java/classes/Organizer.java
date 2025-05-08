package classes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Organizer extends User implements Crud<Event>{

    private ArrayList<Event> eventsCreated;
    Organizer(){
        super();
        Database.organizers.add(this);
    }
    public Organizer(String userName, String password, Gender gender, Date birthDate, long phoneNumber){
        super(userName, password, gender, birthDate, phoneNumber);
        Database.organizers.add(this);
    }

    public void setEventsCreated(ArrayList<Event> eventsCreated){
        this.eventsCreated = eventsCreated;
    }
    public ArrayList<Event> getEventsCreated(){
        return eventsCreated;
    }


    public Room rentRoom(){
        showAvRooms();
        return new Room();
//        while (true){
//            boolean avail = true;
//            System.out.println("Enter room number: ");
//            Scanner scanner = new Scanner(System.in);
//            int roomNum = scanner.nextInt();
//            for (Room room : Database.rooms){
//                if (room.getRoomName() == roomName){
//                    if (room.getAvailable()){
//                        room.setAvailable(false);
//                        return room;
//                    }else {
//                        System.out.println("Room not available!");
//                        avail = false;
//                        break;
//                    }
//
//                }
//            }
//            if (avail) continue;
//            System.out.println("Room not found!");
//        }
    }
    public void showAvRooms(){
        System.out.println("Rooms available: ");
        boolean exists=false;
        for(Room room:Database.rooms) {
            if(room.getAvailable()){
                exists=true;
                System.out.println("-" + room.getRoomName() + ", Size: " + room.getSize());
            }
        }
        if(!exists){
            System.out.println("none");
        }
    }
    public int showOrgUpcomingEvents(){
        int count = 1;
        for (Event event : Database.events){
            if (event.getOrganizer().equals(this) && event.getEventDate().after(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))){
                System.out.println(count + ". " + event.getEventName() + ": " + event.getEventDate());
                count++;
            }
        }
        if (count == 1){
            System.out.println("No Upcoming Events.");
            return 0;
        }
        return count - 1;
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
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter event name: ");
        String eventName = scanner.nextLine();
        System.out.println("Enter event date (day): ");
        int day = scanner.nextInt();
        System.out.println("Enter event date (month): ");
        int month = scanner.nextInt();
        System.out.println("Enter event date (year): ");
        int year = scanner.nextInt();
        Date eventDate = new Date(year, month, day);
        System.out.println("Choose event category: ");
        int count = 1;
        for (Category category : Database.categories){
            System.out.println("Category ID: " + category.getID() + ", Category Description: " + category.getDescription());
        }
        Category category = Database.categories.get(scanner.nextInt());
        boolean validInput = false;
        boolean outdoors = false;
        while (!validInput){
            System.out.println("Outdoors? (Y/N): ");
            scanner.nextLine();
            String outdoorsString = scanner.nextLine();
            if(outdoorsString.equals("Y")){
                outdoors = true;
                validInput = true;
            }else if (outdoorsString.equals("N")){
                outdoors = false;
                validInput = true;
            }else System.out.println("Invalid Input!");
        }

        Room room = rentRoom();
        System.out.println("Enter ticket price: ");
        double fees = scanner.nextDouble();
        System.out.println("Enter max number of attendees: ");
        int maxAttendees = scanner.nextInt();
        Event event = new Event(eventName, eventDate, outdoors, room, maxAttendees, this, category);
        event.setFees(fees);
        Database.events.add(event);
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