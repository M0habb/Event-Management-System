package classes;//package classes;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class Category {
//    private CategoryType type;
//    private String name;
//    private String description;
//    private ArrayList<Event> events;
//
//    public Category(String name, CategoryType type, String description){
//        this.name = name;
//        this.type = type;
//        this.description = description;
//        this.events = new ArrayList<Event>();
//    }
//
//    //Setters & Getters
//
//    public CategoryType getType() {
//        return type;
//    }
//
//    public void setType(CategoryType type) {
//        this.type = type;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public List<Event> getEvents() {
//        return events;
//    }
//
//    public void setEvents(ArrayList<Event> events) {
//        this.events = events;
//    }
//    public String getName(){
//        return this.name;
//    }
//    public void setName(String name){
//        this.name = name;
//    }
//
//
//    public String getUpcomingEvents(){
//
//        String eventsString = "Upcoming " + this.name +" events: ";
//        int count = 0;
//
//        for (Event e : Database.events){
//            if (e.getCategory().equals(this) && e.getEventDate().after(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))){
//                count++;
//                eventsString += e.getEventName();
//                eventsString += " on " + e.getEventDate() + ", ";
//            }
//        }
//
//        if (count == 0){
//            eventsString += "none.";
//        }else eventsString = eventsString.substring(0, eventsString.length() - 2);
//
//        return eventsString;
//    }
//    public int getEventCount(){
//        int count = 0;
//        for (Event e : Database.events){
//            if(e.getCategory().equals(this)){
//                count++;
//            }
//        }
//        return count;
//    }
//}


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Category {
    private final SimpleStringProperty categoryName;
    private final SimpleObjectProperty<CategoryType> categoryType;
    private final SimpleBooleanProperty editable;
    private String description;
    private ArrayList<Event> events;

    public Category(String name, CategoryType type, String description) {
        this.categoryName = new SimpleStringProperty(name);
        this.categoryType = new SimpleObjectProperty<>(type);
        this.editable = new SimpleBooleanProperty(false);
        this.description = description;
    }
    public Category(String name, CategoryType type) {
        this.categoryName = new SimpleStringProperty(name);
        this.categoryType = new SimpleObjectProperty<>(type);
        this.editable = new SimpleBooleanProperty(false);
    }

    // Getters and setters
    public String getName() { return categoryName.get(); }
    public void setCategoryName(String name) { this.categoryName.set(name); }
    public SimpleStringProperty categoryNameProperty() { return categoryName; }
    public String getDescription(){return description;}

    public CategoryType getType() { return categoryType.get(); }
    public void setCategoryType(CategoryType type) { this.categoryType.set(type); }
    public SimpleObjectProperty<CategoryType> categoryTypeProperty() { return categoryType; }

    public boolean isEditable() { return editable.get(); }
    public void setEditable(boolean value) { this.editable.set(value); }
    public SimpleBooleanProperty editableProperty() { return editable; }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public String getUpcomingEvents(){

        String eventsString = "Upcoming " + this.getName() +" events: ";
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
