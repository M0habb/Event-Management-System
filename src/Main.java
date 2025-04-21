import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        initializeDummyData();

        while(true){
            // Startup Menu
            System.out.println("1. Login");
            System.out.println("2. Signup (Attendee)");
            System.out.println("3. Exit");

            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();

            boolean exit = false;

            switch (input){
                case 1:
                    int succesfulLogin = User.login();

                    while (succesfulLogin == 0){
                        System.out.println("Invalid Username/Password!");
                        succesfulLogin = User.login();
                    }

                    switch (succesfulLogin){
                        case 1:
                            // Admin Menu
                            Admin admin = (Admin) User.currentUser;
                            boolean logout = false;

                            while (true){
                                System.out.println("1. Show Rooms");
                                System.out.println("2. Show Events");
                                System.out.println("3. Show Attendees");
                                System.out.println("4. Show Organizers");
                                System.out.println("5. Manage Categories");//crud
                                System.out.println("6. Logout");

                                int adInput = scanner.nextInt();

                                switch (adInput){
                                    case 1:
                                        admin.showRooms();
                                        System.out.println("Press enter to go back.");
                                        scanner.nextLine();
                                        scanner.nextLine();
                                        break;
                                    case 2:
                                        admin.showAllEvents();
                                        System.out.println("Press enter to go back.");
                                        scanner.nextLine();
                                        scanner.nextLine();
                                        break;
                                    case 3:
                                        admin.showAllAttendees();
                                        System.out.println("Press enter to go back.");
                                        scanner.nextLine();
                                        scanner.nextLine();
                                        break;
                                    case 4:
                                        admin.showOrganizers();
                                        System.out.println("Press enter to go back.");
                                        scanner.nextLine();
                                        scanner.nextLine();
                                        break;
                                    case 5:
                                        System.out.println("1. Create category");
                                        System.out.println("2. Read category");
                                        System.out.println("3. Update category");
                                        System.out.println("4. Delete category");
                                        System.out.println("5. Back");

                                        int catInput = scanner.nextInt();

                                        switch (catInput){

                                            case 1:
                                                System.out.print("Enter category ID: ");
                                                int createId = scanner.nextInt();

                                                System.out.print("Enter category type (e.g., SPORTS, MUSIC, TECH): ");
                                                String typeInput = scanner.nextLine().toUpperCase();

                                                CategoryType type;
                                                try {
                                                    type = CategoryType.valueOf(typeInput);
                                                } catch (IllegalArgumentException e) {
                                                    System.out.println("Invalid category type.");
                                                    break;
                                                }

                                                System.out.print("Enter category description: ");
                                                String desc = scanner.nextLine();

                                                Category newCategory = new Category(createId, type, desc);
                                                admin.create(newCategory);
                                                break;
                                            case 2:
                                                admin.read();
                                                break;
                                            case 3:
                                                System.out.print("Enter ID of category to update: ");
                                                int updateId = scanner.nextInt();
                                                scanner.nextLine();

                                                System.out.print("Enter new category type: ");
                                                String newTypeInput = scanner.nextLine().toUpperCase();

                                                CategoryType newType;
                                                try {
                                                    newType = CategoryType.valueOf(newTypeInput);
                                                } catch (IllegalArgumentException e) {
                                                    System.out.println("Invalid category type.");
                                                    break;
                                                }

                                                System.out.print("Enter new description: ");
                                                String newDesc = scanner.nextLine();

                                                Category updatedCategory = new Category(updateId, newType, newDesc);
                                                admin.update(updatedCategory);
                                                break;
                                            case 4:
                                                System.out.print("Enter category ID to delete: ");
                                                int deleteId = scanner.nextInt();
                                                admin.delete(deleteId);
                                                break;
                                            case 5:
                                                continue;

                                        }
                                    case 6:
                                        logout = true;
                                        break;
                                }
                                if (logout) break;
                            }
                            break;
                        case 2:
                            // Organizer Menu
                            Organizer organizer = (Organizer) User.currentUser;
                            Scanner sc = new Scanner(System.in);

                            System.out.println("1. Upcoming Events");
                            System.out.println("2. Previous Events");
                            System.out.println("3. Create Event");
                            System.out.println("4. Logout");

                            int orgInput = sc.nextInt();

                            switch (orgInput){
                                case 1:
                                    organizer.showOrgUpcomingEvents();
                                    break;
                                case 2:
                                    organizer.showOrgPreviousEvents();
                                    break;
                                case 3:
                                    //organizer.create();
                                    break;
                                case 4:
                                    break;
                            }
                            break;
                        case 3:
                            // Attendee Menu
                            break;
                    }
                    break;

                case 2:
                    Attendee newAttendee = new Attendee();
                    newAttendee.signup();
                    System.out.println("Successful signup!");
                    break;
                case 3:
                    exit = true;
                    break;

            }
            if(exit){
                break;
            }
        }


    }
    public static void initializeDummyData() {
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

        Category category1 = new Category(1, CategoryType.MUSIC, "Events of this category are jsfasj fsadjf asf s");
        Category category2 = new Category(2, CategoryType.SPORTS, "Events of this category are jjhnajkg skdlfg kdsj");

        Event event1 = new Event("ASU Music Concert", bd, false, address2, 100, organizer3, category1);
        Event event2 = new Event("ASU Sports Concert", bd2, true, address1, 250, organizer2, category2);
        Event event3 = new Event("IDK Music Concert", bd3, true, address3, 400, organizer1, category1);

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

        ArrayList<Event> org1EventsCreated = new ArrayList<Event>();
        org1EventsCreated.add(event1);
        org1EventsCreated.add(event2);
        organizer1.setEventsCreated(org1EventsCreated);
        organizer2.setEventsCreated(org1EventsCreated);
        organizer3.setEventsCreated(org1EventsCreated);
    }
}