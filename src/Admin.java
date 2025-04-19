import java.util.Date;
import java.util.List;

public abstract class Admin extends User implements Crud<Category> {
    private int workingHours;

    // Constructors
    public Admin() {
        super();
    }

    public Admin(String userName, String password, Gender gender,
                 Date birthDate, long phoneNumber, int workingHours) {
        super(userName, password, gender, birthDate, phoneNumber);
        this.workingHours = workingHours;
    }

    // Setters & Getters
    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    // Display methods
    public void showRooms() {
        System.out.println("Available Rooms:");
        for (Room room : Database.rooms) {
            System.out.println("Room #" + room.getRoomNum() +
                    " | Size: " + room.getSize() +
                    " | Available: " + room.getAvailable());
        }
    }

    public void showEvents() {
        System.out.println("Upcoming Events:");
        for (Event event : Database.events) {
            System.out.println(event.getEventName() +
                    " | Date: " + event.getEventDate() +
                    " | Location: " + event.getLocation() +
                    " | Category: " + event.getCategory().getType());
        }
    }

    public void showAttendees() {
        System.out.println("Registered Attendees:");
        for (Attendee attendee : Database.attendees) {
            System.out.println(attendee.getUserName() +
                    " | Balance: " + attendee.getWallet().getBalance());
        }
    }

    public void showOrganizers() {
        System.out.println("Registered Organizers:");
        for (Organizer organizer : Database.organizers) {
            System.out.println(organizer.getUserName() +
                    " | Events Created: " + organizer.getEventsCreated().size());
        }
    }

    // Room management
    public Room addRoom(int roomNum, Size size) {
        Room newRoom = new Room(roomNum, size, true);
        Database.rooms.add(newRoom);
        return newRoom;
    }

    // CRUD Operations for Category (from Crud interface)
    @Override
    public void create(Category category) {
        Database.categories.add(category);
        System.out.println("Category created: " + category.getType());
    }

    @Override
    public Category read(int categoryId) {
        for (Category category : Database.categories) {
            if (category.getCategoryId() == categoryId) {
                return category;
            }
        }
        return null;
    }

    @Override
    public void update(Category updatedCategory) {
        for (int i = 0; i < Database.categories.size(); i++) {
            if (Database.categories.get(i).getCategoryId() == updatedCategory.getCategoryId()) {
                Database.categories.set(i, updatedCategory);
                System.out.println("Category updated: " + updatedCategory.getType());
                return;
            }
        }
        System.out.println("Category not found for update");
    }

    @Override
    public void delete(int categoryId) {
        Category toRemove = null;
        for (Category category : Database.categories) {
            if (category.getCategoryId() == categoryId) {
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

    // Additional admin operations
    public void assignEventToCategory(Event event, Category category) {
        event.setCategory(category);
        category.addEvent(event);
        System.out.println("Event '" + event.getEventName() +
                "' assigned to category: " + category.getType());
    }

    public void showAllCategories() {
        System.out.println("Available Categories:");
        for (Category category : Database.categories) {
            System.out.println(category.getType() +
                    " (" + category.getEvents().size() + " events)");
        }
    }

    @Override
    public void signup() {
        // Admin-specific signup logic
        Database.admins.add(this);
        System.out.println("Admin account created for: " + this.getUserName());
    }
}