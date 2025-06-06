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
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void initializeDummyData() {
        Date bd = new Date(125, 07, 23);
        Date bd2 = new Date(125, 12, 21);
        Date bd3 = new Date(125, 07, 11);
        Date bd4 = new Date(125, 07, 15);
        Date bd5 = new Date(125, 06, 22);
        Date bd6 = new Date(125, 10, 15);


        Address address1 = new Address("Egypt", "Cairo", "123", 12341);
        Address address2 = new Address("Egypt", "Alexnder", "456", 3624);
        Address address3 = new Address("Japan", "Tokyo", "890", 2385);
        Address address4 = new Address("Saudi", "Jeddah", "546", 9874);
        Address address5 = new Address("Egypt", "Alexnder", "298", 6548);
        Address address6 = new Address("Japan", "Tokyo", "560", 7635);

        Room room1 = new Room("Al-Manara conference center", 50, true, address1, false);
        Room room2 = new Room("Opera Hall", 1000, true, address2, true);
        Room room3 = new Room("Cairo International Stadium", 50000, true, address3, true);
        Room room4 = new Room("Coworking space", 52000, true, new Address("KSA", "Jeddah", "1st St.", 123123), false);
        Room room5 = new Room("ALAhly stadium", 50, true, address1, false);
        Room room6 = new Room("New Capital venue", 1000, true, address2, true);
        Room room7 = new Room("ASU stage", 50, true, address1, true);
        Room room8 = new Room("ASU halls", 50, true, address1, false);
        Room room9 = new Room("ASU venue", 1000, true, address2, true);
        Room room10 = new Room("AUC stage", 50, true, address1, true);
        Room room11 = new Room("AUC venue", 1000, true, address2, true);
        Room room12 = new Room("AUC halls", 50000, true, address3, false);
        Room room13 = new Room("Alex library", 52000, true, new Address("KSA", "Jeddah", "1st St.", 123123), false);
        Room room14 = new Room("Elzamalek stadium", 50, true, address1, true);
        Room room15 = new Room("CFC stage", 1000, true, address2, true);
        Room room16 = new Room("Central park", 50, true, address1, true);
        Room room17 = new Room("South park", 50, true, address1, true);
        Room room18 = new Room("Cinema palace", 1000, true, address2, false);
        Room room19 = new Room("Mohamed Ali mosque", 50, true, address1, false);
        Room room20 = new Room("Church of the nativity", 1000, true, address2, false);


        Attendee attendee1 = new Attendee("Mohab", "123", Gender.MALE, bd, "155573", address1);
        Attendee attendee2 = new Attendee("Mohamed", "123", Gender.MALE, bd2, "5236", address2);
        Attendee attendee3 = new Attendee("Hana", "asda", Gender.FEMALE, bd3, "85635436", address3);
        Attendee attendee4 = new Attendee("Farah", "yf", Gender.FEMALE, bd, "594984", address4);
        Attendee attendee5 = new Attendee("Toaa", "ydd", Gender.FEMALE, bd2, "6141651", address5);
        Attendee attendee6 = new Attendee("Hend", "rg", Gender.FEMALE, bd3, "519825", address6);
        Attendee attendee7 = new Attendee("Samy", "xg", Gender.MALE, bd, "164116", address1);
        Attendee attendee8 = new Attendee("Fady", "dr", Gender.MALE, bd2, "5117418", address2);
        Attendee attendee9 = new Attendee("Ramy", "zrs", Gender.MALE, bd3, "258515", address3);
        Attendee attendee10 = new Attendee("Hobza", "123", Gender.MALE, bd, "155573", address1);
        Attendee attendee11 = new Attendee("Sameh", "123", Gender.MALE, bd2, "5236", address2);
        Attendee attendee12 = new Attendee("Haidy", "asda", Gender.FEMALE, bd3, "85635436", address3);
        Attendee attendee13 = new Attendee("Fofa", "yf", Gender.FEMALE, bd, "594984", address4);
        Attendee attendee14 = new Attendee("Tuana", "ydd", Gender.FEMALE, bd2, "6141651", address5);
        Attendee attendee15 = new Attendee("Hanin", "rg", Gender.FEMALE, bd3, "519825", address6);
        Attendee attendee16 = new Attendee("Soly", "xg", Gender.MALE, bd, "164116", address1);
        Attendee attendee17 = new Attendee("Soliman", "dr", Gender.MALE, bd2, "5117418", address2);
        Attendee attendee18 = new Attendee("Rane", "zrs", Gender.MALE, bd3, "258515", address3);

        Admin admin1 = new Admin("Sama", "123", Gender.FEMALE, bd, "155573", 3);
        Admin admin2 = new Admin("Omar", "1325", Gender.MALE, bd2, "5236", 6);
        Admin admin3 = new Admin("John", "asda", Gender.MALE, bd3, "85635436", 7);
        Admin admin4 = new Admin("Kindra", "123", Gender.FEMALE, bd, "68418", 5);
        Admin admin5 = new Admin("Mostafa", "1325", Gender.MALE, bd2, "58188", 4);
        Admin admin6 = new Admin("Joe", "kk", Gender.MALE, bd3, "6617", 4);
        Admin admin7 = new Admin("Rania", "bhb", Gender.FEMALE, bd, "5515575", 7);
        Admin admin8 = new Admin("Sokary", "jhb", Gender.MALE, bd2, "57757", 7);
        Admin admin9 = new Admin("Rani", "dfee", Gender.MALE, bd3, "17878651", 8);


        Organizer organizer6 = new Organizer("Michael", "123", Gender.MALE, bd, "155573");
        Organizer organizer2 = new Organizer("Ahmad", "1325", Gender.MALE, bd2, "57757");
        Organizer organizer3 = new Organizer("Jana", "123", Gender.FEMALE, bd3, "155573");
        Organizer organizer4 = new Organizer("Seif", "123", Gender.MALE, bd, "57757");
        Organizer organizer5 = new Organizer("Amjad", "1325", Gender.MALE, bd2, "155573");
        Organizer organizer1 = new Organizer("Sarah", "123", Gender.FEMALE, bd3, "57757");
        Organizer organizer7 = new Organizer("Micho", "123", Gender.MALE, bd, "15552973");
        Organizer organizer8 = new Organizer("Amr", "1325", Gender.MALE, bd2, "54754685");
        Organizer organizer9 = new Organizer("Nancy", "123", Gender.FEMALE, bd3, "48845115");
        Organizer organizer10 = new Organizer("Karim", "123", Gender.MALE, bd, "155573");
        Organizer organizer11 = new Organizer("Mohamady", "1325", Gender.MALE, bd2, "5236");
        Organizer organizer12 = new Organizer("Rokaya", "123", Gender.FEMALE, bd3, "85635436");
        Organizer organizer13 = new Organizer("Marwan", "123", Gender.MALE, bd, "4818461");
        Organizer organizer14 = new Organizer("Johny", "1325", Gender.MALE, bd2, "81881");
        Organizer organizer15 = new Organizer("Cloe", "123", Gender.FEMALE, bd3, "1574");
        Organizer organizer16 = new Organizer("Omair", "123", Gender.MALE, bd, "15552973");
        Organizer organizer17 = new Organizer("Amer", "1325", Gender.MALE, bd2, "54754685");
        Organizer organizer18 = new Organizer("Samia", "123", Gender.FEMALE, bd3, "48845115");


        Category category1 = new Category("House", CategoryType.MUSIC, "Events of this category are jsfasj fsadjf asf s");
        Category category2 = new Category("Football", CategoryType.SPORTS, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category3 = new Category("Techno", CategoryType.MUSIC, "Events of this category are jsfasj fsadjf asf s");
        Category category4 = new Category("Slow", CategoryType.MUSIC, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category5 = new Category("Swimming", CategoryType.SPORTS, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category6 = new Category("Tennis", CategoryType.SPORTS, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category7 = new Category("Drama", CategoryType.THEATER, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category8 = new Category("Comedy", CategoryType.THEATER, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category9 = new Category("Work", CategoryType.CONFERENCE, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category10 = new Category("Charity", CategoryType.OTHER, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category11 = new Category("Classical", CategoryType.MUSIC, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category12 = new Category("Music", CategoryType.MUSIC, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category13 = new Category("Sports", CategoryType.SPORTS, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category14 = new Category("Theatre", CategoryType.THEATER, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category15 = new Category("Conference", CategoryType.CONFERENCE, "Events of this category are jjhnajkg skdlfg kdsj");
        Category category16 = new Category("Other", CategoryType.OTHER, "Events of this category are jjhnajkg skdlfg kdsj");

        Event event1 = new Event("Omar khairat concert", new Date(123, Calendar.JUNE, 12), false, room2, 60, organizer3, category11);
        Event event2 = new Event("Cairokee concert", bd2, true, room6, 250, organizer2, category3);
        Event event3 = new Event("Amr Diab concert", bd3, true, room6, 400, organizer1, category4);
        Event event4 = new Event("Hamaky concert", new Date(125, Calendar.DECEMBER, 23), true, room6, 150, organizer3, category4);

        Event event5 = new Event("Football match", new Date(125, Calendar.JUNE, 12), false, room5, 60, organizer3, category2);
        Event event6 = new Event("Swimming race", bd2, true, room3, 250, organizer2, category5);
        Event event7 = new Event("Tennis game", bd3, true, room3, 400, organizer1, category6);

        Event event9 = new Event("Dreamscape Theater", new Date(125, Calendar.JUNE, 12), false, room1, 60, organizer3, category7);
        Event event10 = new Event("Enchanted Playhouse", bd3, true, room1, 250, organizer2, category7);
        Event event11 = new Event("Masrah masr", bd5, true, room1, 400, organizer1, category8);

        Event event12 = new Event("MindMerge", new Date(124, Calendar.DECEMBER, 23), true, room4, 150, organizer3, category9);
        Event event13 = new Event("NetworkNest", new Date(125, Calendar.JUNE, 12), false, room4, 60, organizer3, category9);
        Event event14 = new Event("Innovanter", bd5, true, room4, 250, organizer2, category9);

        Event event15 = new Event("Charity day", bd6, true, room3, 400, organizer1, category10);

        event1.setFees(200);
        event2.setFees(500);
        event3.setFees(1000);
        event4.setFees(1500);
        event5.setFees(2500);
        event6.setFees(1700);
        event7.setFees(1500);
        event9.setFees(2600);
        event10.setFees(3000);
        event11.setFees(750);
        event12.setFees(1500);
        event13.setFees(1100);
        event14.setFees(1500);
        event15.setFees(150);
        event5.setFees(2000);
        event6.setFees(500);
        

        attendee1.getWallet().addBalance(400);
    }
}
