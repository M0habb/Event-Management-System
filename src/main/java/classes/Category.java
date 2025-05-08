package classes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Category {
    private CategoryType type;
    private String name;
    private String description;
    private ArrayList<Event> events;

    public Category(String name, CategoryType type, String description){
        this.name = name;
        this.type = type;
        this.description = description;
        this.events = new ArrayList<Event>();
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

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public String getUpcomingEvents(){

        String eventsString = "Upcoming " + this.name +" events: ";
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
