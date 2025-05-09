package classes;

import java.util.ArrayList;

public class Room {

    private String roomName;
    private int size; //no. of ppl
    private boolean available;
    private Address address;
    private boolean outdoors;
    public Room(){

    }
    public Room(String roomName, int size, boolean available, Address address, boolean outdoors){
        this.roomName = roomName;
        this.size = size;
        this.available = available;
        this.address = address;
    }

    public Room(String name, int size, boolean b) {
        this.roomName=name;
        this.size=size;
        this.available=b;

    }


//setters and getters

    public boolean isOutdoors() {
        return outdoors;
    }

    public void setOutdoors(boolean outdoors) {
        this.outdoors = outdoors;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public boolean getAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public String getRoomName() {
        return roomName;
    }
    public void setRoomNum(int roomNum) {
        this.roomName = roomName;
    }
    public void setRoom(ArrayList<Room> room) {
        Database.rooms = room;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public void isAvailable(){
        System.out.println("Rooms available: ");
        boolean exists=false;
        for(Room room:Database.rooms) {
            if(room.getAvailable()){
                exists=true;
                System.out.println("-" + room.roomName);
            }
        }
        if(!exists){
            System.out.println("none");
        }
    }

}




