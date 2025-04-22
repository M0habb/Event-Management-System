import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Category {
    private CategoryType type;
    private int ID;
    private String description;
    private List<Event> events;

    Category(int ID, CategoryType type, String description){
        this.ID = ID;
        this.type = type;
        this.description = description;
        Database.categories.add(this);
    }

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
    public int getID(){
        return this.ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }


    public String getUpcomingEvents(){

        String eventsString = "Upcoming " + this.ID +" events: ";
        int count = 0;

        for (Event e : Database.events){
            if (e.getCategory().equals(this) && e.getEventDate().after(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))){
                count++;
                eventsString += e.getEventName();
                eventsString += " on " + e.getEventDate() + ", ";
            }
        }

        if (count == 0){
            eventsString += "none.";
        }else eventsString = eventsString.substring(0, eventsString.length() - 2);

        return eventsString;
    }
    public int getEventCount(){
        int count = 0;
        for (Event e : Database.events){
            if(e.getCategory().equals(this)){
                count++;
            }
        }
        return count;
    }
}
