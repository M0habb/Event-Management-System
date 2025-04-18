import java.util.List;

public class Category {
    private CategoryType type;
    private String name;
    private String description;
    private List<Event> events;


    //Setters & Getters

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


    public String getUpcomingEvents(){
        return "s";
    }
    public int getEventCount(){
        return 0;
    }
}
