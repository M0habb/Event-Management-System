import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {
    public static ArrayList<Attendee> attendees = new ArrayList<>();
    public static ArrayList<Organizer> organizers = new ArrayList<>();
    public static ArrayList<Admin> admins = new ArrayList<>();
    public static ArrayList<Event> events = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Category> categories = new ArrayList<>();
    public static ArrayList<Ticket> tickets = new ArrayList<>();

    public static void initializeDummyData() {
        // Add example admins, attendees, events, rooms, etc.
        Date bd = new Date(23, 07, 2006);
        Date bd2 = new Date(21, 12, 2005);
        Date bd3 = new Date(11, 02, 2010);

        Address address1 = new Address("Egypt", "Cairo", "123", 12341);
        Address address2 = new Address("Egypt", "Alexander", "456", 3624);
        Address address3 = new Address("Japan", "Tokyo", "890", 2385);

        Attendee attendee1 = new Attendee("Mohab", "123", Gender.MALE, bd, 155573, address1);
        Attendee attendee2 = new Attendee("Mohamed", "1325", Gender.MALE, bd2, 5236, address2);
        Attendee attendee3 = new Attendee("Jana", "asda", Gender.FEMALE, bd3, 85635436, address3);

        Admin admin1 = new Admin("Sama", "123", Gender.FEMALE, bd, 155573, 3);
        Admin admin2 = new Admin("Omar", "1325", Gender.MALE, bd2, 5236, 6);
        Admin admin3 = new Admin("John", "asda", Gender.MALE, bd3, 85635436, 7);

        Organizer organizer1 = new Organizer("Michael", "123", Gender.MALE, bd, 155573);
        Organizer organizer2 = new Organizer("Ahmad", "1325", Gender.MALE, bd2, 5236);
        Organizer organizer3 = new Organizer("Sarah", "asda", Gender.FEMALE, bd3, 85635436);

        Category category1 = new Category("fuck you", CategoryType.MUSIC, "Events of this category are jsfasj fsadjf asf s");
        Category category2 = new Category("fuck you too", CategoryType.SPORTS, "Events of this category are jjhnajkg skdlfg kdsj");

        Event event1 = new Event("ASU Music Concert", bd, false, address2, 100, organizer3, category1);
        Event event2 = new Event("ASU Sports Concert", bd2, true, address1, 250, organizer2, category2);
        Event event3 = new Event("IDK Music Concert", bd3, true, address3, 400, organizer1, category1);

        admins.add(admin1);
        admins.add(admin2);
        admins.add(admin3);
        organizers.add(organizer1);
        organizers.add(organizer2);
        organizers.add(organizer3);
        attendees.add(attendee1);
        attendees.add(attendee2);
        attendees.add(attendee3);
        categories.add(category1);
        categories.add(category2);
        events.add(event1);
        events.add(event2);
        events.add(event3);

        ArrayList<Event> org1EventsCreated = new ArrayList<Event>();
        org1EventsCreated.add(event1);
        org1EventsCreated.add(event2);
        organizer1.setEventsCreated(org1EventsCreated);
        organizer2.setEventsCreated(org1EventsCreated);
        organizer3.setEventsCreated(org1EventsCreated);
        admin1.showOrganizers();
    }
}