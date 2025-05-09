package GUI;

import classes.*;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainController extends Application{

    public static void main(String[] args) {

        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeDummyData();
        Parent root = FXMLLoader.load(getClass().getResource("/resources/login.fxml"));
        primaryStage.setTitle("Event Management System");
        primaryStage.setScene(new Scene(root, 1142, 642));
        primaryStage.show();
    }

    public static void initializeDummyData() {
        Date bd = new Date(2006, 07, 23);
        Date bd2 = new Date(2005, 12, 21);
        Date bd3 = new Date(2030, 02, 11);
        Date bd4 = new Date(2002, 05, 15);
        Date bd5 = new Date(2001, 06, 22);
        Date bd6 = new Date(2000, 10, 15);


        Address address1 = new Address("Egypt", "Cairo", "123", 12341);
        Address address2 = new Address("Egypt", "Alexnder", "456", 3624);
        Address address3 = new Address("Japan", "Tokyo", "890", 2385);
        Address address4 = new Address("Saudi", "Jeddah", "546", 9874);
        Address address5 = new Address("Egypt", "Alexnder", "298", 6548);
        Address address6 = new Address("Japan", "Tokyo", "560", 7635);

        Room room1 = new Room("Al-Manara conference center", 50, true, address1, false);
        Room room2 = new Room("Opera Hall", 1000, true, address2, true);
        Room room3 = new Room("Cairo International Stadium", 50000, true, address3, true);
        Room room4 = new Room("The London Palladium", 10000, true, new Address("KSA", "Jeddah", "1st St.", 123123), false);
        Room room5 = new Room("Al-Manara conference center", 50, true, address1, false);
        Room room6 = new Room("Opera Hall", 1000, true, address2, true);
        Room room7 = new Room("Cairo International Stadium", 50000, true, address3, true);
        Room room8 = new Room("The London Palladium", 10000, true, new Address("KSA", "Jeddah", "1st St.", 123123), false);


        Attendee attendee1 = new Attendee("Sarah", "123", Gender.MALE, bd, 155573, address1);
        Attendee attendee2 = new Attendee("Mohamed", "1325", Gender.MALE, bd2, 5236, address2);
        Attendee attendee3 = new Attendee("Hana", "asda", Gender.FEMALE, bd3, 85635436, address3);
        Attendee attendee4 = new Attendee("Farah", "123", Gender.FEMALE, bd, 594984, address4);
        Attendee attendee5 = new Attendee("Toaa", "1325", Gender.FEMALE, bd2, 6141651, address5);
        Attendee attendee6 = new Attendee("Hend", "asda", Gender.FEMALE, bd3, 519825, address6);
        Attendee attendee7 = new Attendee("Samy", "123", Gender.MALE, bd, 164116, address1);
        Attendee attendee8 = new Attendee("Fady", "1325", Gender.MALE, bd2, 5117418, address2);
        Attendee attendee9 = new Attendee("Ramy", "asda", Gender.MALE, bd3, 258515, address3);

        Admin admin1 = new Admin("Sama", "123", Gender.FEMALE, bd, 155573, 3);
        Admin admin2 = new Admin("Omar", "1325", Gender.MALE, bd2, 5236, 6);
        Admin admin3 = new Admin("John", "asda", Gender.MALE, bd3, 85635436, 7);

        Organizer organizer1 = new Organizer("Michael", "123", Gender.MALE, bd, 155573);
        Organizer organizer2 = new Organizer("Ahmad", "1325", Gender.MALE, bd2, 5236);
        Organizer organizer3 = new Organizer("Jana", "123", Gender.FEMALE, bd3, 85635436);

        Category category1 = new Category("House", CategoryType.MUSIC, "Events of this category are jsfasj fsadjf asf s");
        Category category2 = new Category("Football", CategoryType.SPORTS, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category3 = new Category("Act", CategoryType.THEATER);

        Event event1 = new Event("ASU Music Concert", new Date(126, Calendar.MARCH, 12), false, room1, 60, organizer3, category1);
        Event event2 = new Event("ASU Sports Concert", bd2, true, room2, 250, organizer2, category2);
        Event event3 = new Event("IDK Music Concert", bd3, true, room3, 400, organizer1, category1);
        Event event4 = new Event("Test event", new Date(125, Calendar.DECEMBER, 23), true, room2, 150, organizer3, category1);
        Event event5 = new Event("Jana prev event", new Date(123, Calendar.APRIL, 23), true, room1, 1230, organizer3, category2);
        Event event6 = new Event("Romeo & Juliet", new Date(126, Calendar.JANUARY, 15), false, room8, 100, organizer3, category3);

        event1.setFees(200);
        event2.setFees(500);
        event3.setFees(1000);
        event4.setFees(1500);
        event5.setFees(2000);
        event6.setFees(500);

        Database.admins.add(admin1);
        Database.admins.add(admin2);
        Database.admins.add(admin3);
        Database.organizers.add(organizer1);
        Database.organizers.add(organizer2);
        Database.organizers.add(organizer3);
        Database.totalAttendees.add(attendee1);
        Database.totalAttendees.add(attendee2);
        Database.totalAttendees.add(attendee3);
        Database.categories.add(category1);
        Database.categories.add(category2);
        Database.events.add(event1);
        Database.events.add(event2);
        Database.events.add(event3);
        Database.events.add(event4);
        Database.events.add(event5);
        Database.events.add(event6);
        Database.rooms.add(room1);
        Database.rooms.add(room2);
        Database.rooms.add(room3);
        Database.rooms.add(room4);

        attendee1.getWallet().addBalance(400);

        ArrayList<Event> org1EventsCreated = new ArrayList<Event>();
        org1EventsCreated.add(event1);
        org1EventsCreated.add(event2);
        organizer1.setEventsCreated(org1EventsCreated);
        organizer2.setEventsCreated(org1EventsCreated);
        organizer3.setEventsCreated(org1EventsCreated);
    }
}
