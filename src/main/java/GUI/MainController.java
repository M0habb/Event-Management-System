package GUI;

import classes.*;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;

import java.util.ArrayList;
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

        Address address1 = new Address("Egypt", "Cairo", "123", 12341);
        Address address2 = new Address("Egypt", "Alexnder", "456", 3624);
        Address address3 = new Address("Japan", "Tokyo", "890", 2385);

        Room room1 = new Room(1, Size.SINGLE, true);
        Room room2 = new Room(2, Size.DOUBLE, true);
        Room room3 = new Room(3, Size.TRIPLE, true);
        Room room4 = new Room(4, Size.SINGLE, true);

        Attendee attendee1 = new Attendee("Sarah", "123", Gender.MALE, bd, 155573, address1);
        Attendee attendee2 = new Attendee("Mohamed", "1325", Gender.MALE, bd2, 5236, address2);
        Attendee attendee3 = new Attendee("Sarah", "asda", Gender.FEMALE, bd3, 85635436, address3);

        Admin admin1 = new Admin("Sama", "123", Gender.FEMALE, bd, 155573, 3);
        Admin admin2 = new Admin("Omar", "1325", Gender.MALE, bd2, 5236, 6);
        Admin admin3 = new Admin("John", "asda", Gender.MALE, bd3, 85635436, 7);

        Organizer organizer1 = new Organizer("Michael", "123", Gender.MALE, bd, 155573);
        Organizer organizer2 = new Organizer("Ahmad", "1325", Gender.MALE, bd2, 5236);
        Organizer organizer3 = new Organizer("Jana", "123", Gender.FEMALE, bd3, 85635436);

        Category category1 = new Category(1, CategoryType.MUSIC, "Events of this category are jsfasj fsadjf asf s");
        Category category2 = new Category(2, CategoryType.SPORTS, "Events of this category are jjhnajkg skdlfg kdsj");

        Event event1 = new Event("ASU Music Concert", bd, false, room1,
                00, organizer3, category1);
        Event event2 = new Event("ASU Sports Concert", bd2, true, room2, 250, organizer2, category2);
        Event event3 = new Event("IDK Music Concert", bd3, true, room3, 400, organizer1, category1);

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
        Database.rooms.add(room1);
        Database.rooms.add(room2);
        Database.rooms.add(room3);
        Database.rooms.add(room4);

        ArrayList<Event> org1EventsCreated = new ArrayList<Event>();
        org1EventsCreated.add(event1);
        org1EventsCreated.add(event2);
        organizer1.setEventsCreated(org1EventsCreated);
        organizer2.setEventsCreated(org1EventsCreated);
        organizer3.setEventsCreated(org1EventsCreated);
    }
}
