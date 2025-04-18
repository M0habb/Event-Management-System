import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date bd = new Date(2006, 07, 23);
        Date bd2 = new Date(2005, 12, 21);
        Date bd3 = new Date(2030, 02, 11);

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

        Database.admins.add(admin1);
        Database.admins.add(admin2);
        Database.admins.add(admin3);
        Database.organizers.add(organizer1);
        Database.organizers.add(organizer2);
        Database.organizers.add(organizer3);
        Database.attendees.add(attendee1);
        Database.attendees.add(attendee2);
        Database.attendees.add(attendee3);
        Database.categories.add(category1);
        Database.categories.add(category2);
        Database.events.add(event1);
        Database.events.add(event2);
        Database.events.add(event3);

        ArrayList<Event> org1EventsCreated = new ArrayList<Event>();
        org1EventsCreated.add(event1);
        org1EventsCreated.add(event2);
        organizer1.setEventsCreated(org1EventsCreated);
        organizer2.setEventsCreated(org1EventsCreated);
        organizer3.setEventsCreated(org1EventsCreated);
        admin1.showOrganizers();
        System.out.println(category1.getUpcomingEvents());
        System.out.println(category2.getEventCount());
    }
}