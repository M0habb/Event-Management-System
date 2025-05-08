package classes;

import java.util.Date;
import java.util.Scanner;

public class Admin extends User implements Crud<Category>{
    private int workingHours;
    public Admin(){
        super();
    }
    public Admin(String userName, String password, Gender gender, Date birthDate, long phoneNumber, int workingHours){
        super(userName, password, gender, birthDate, phoneNumber);
        this.workingHours = workingHours;
    }

    //Setters & Getters
    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }


    public void showRooms(){
        for (Room room : Database.rooms) {
            System.out.println("Room #" + room.getRoomName() +
                    " | Size: " + room.getSize() +
                    " | Available: " + room.getAvailable());
        }

    }
    public void showAllEvents(){
        System.out.println("Upcoming Events:");
        for (Event event : Database.events) {
            System.out.println(event.getEventName() +
                    " | Date: " + event.getEventDate() +

                    " | Room: " + event.getRoom() +

                    " | Category: " + event.getCategory().getType());
        }

    }

    public void  showAllAttendees() {
        System.out.println("Registered Attendees:");
        for (Attendee attendee : Database.totalAttendees) {
            System.out.println(attendee.getUserName() +
                    " | Balance: " + attendee.getWallet().getBalance());

        } 
    }
    public void showOrganizers() {
        System.out.println("Registered Organizers:");
        for (Organizer organizer : Database.organizers) {
            System.out.println(organizer.getUserName() +
                    " . Events Created: " + organizer.getEventsCreated().size());

        }
    }
    public void addRoom(String roomName, Size size, Address address, boolean outdoors){
        Room newRoom = new Room(roomName, size, true, address, outdoors);
        Database.rooms.add(newRoom);
    }


    @Override
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter category ID: ");
        int createId = scanner.nextInt();

        System.out.println("Choose category type:");
        CategoryType[] types = CategoryType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i].name().charAt(0) + types[i].name().substring(1).toLowerCase());
        }

        int typeInput = scanner.nextInt();
        CategoryType type;

        if (typeInput >= 1 && typeInput <= types.length) {
            type = types[typeInput - 1];
        } else {
            System.out.println("Invalid choice. Defaulting to OTHER.");
            type = CategoryType.OTHER;
        }

        System.out.print("Enter category description: ");
        scanner.nextLine();
        String desc = scanner.nextLine();

        Category newCategory = new Category(createId, type, desc);
        Database.categories.add(newCategory);
    }

    @Override
    public void read() {
        System.out.println("Available Categories:");
        for (Category category : Database.categories) {
            System.out.println(category.getType() +
                    " (" + category.getEvents().size() + " events)");
        }
    }

    @Override
    public void update(Category updatedCategory) {
        for (int i = 0; i < Database.categories.size(); i++) {
            if (Database.categories.get(i).getID() == updatedCategory.getID()) {
                Database.categories.set(i, updatedCategory);
                System.out.println("Category updated: " + updatedCategory.getID());
                return;
            }
        }
        System.out.println("Category not found for update");

    }

    @Override
    public void delete(int categoryID) {
        Category toRemove = null;
        for (Category category : Database.categories) {
            if (category.getID() == categoryID) {
                toRemove = category;
                break;
            }
        }
        if (toRemove != null && toRemove.getEvents().isEmpty()) {
            Database.categories.remove(toRemove);
            System.out.println("Category deleted: " + toRemove.getType());
        } else if (toRemove != null) {
            System.out.println("Cannot delete category with associated events");
        } else {
            System.out.println("Category not found");
        }

    }
}