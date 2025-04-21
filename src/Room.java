import java.util.ArrayList;

public class Room {

    private int roomNum;
    private Size size; //no. of ppl
    private boolean available;


    Room(int roomNum, Size size, boolean available){
        this.roomNum = roomNum;
        this.size = size;
        this.available = available;
    }
//setters and getters
    public Size getSize() {
        return size;
    }
    public void setSize(Size size) {
        this.size = size;
    }
    public boolean getAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public int getRoomNum() {
        return roomNum;
    }
    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }
<<<<<<< HEAD
=======
    public void setRoom(ArrayList<Room> room) {
        Database.rooms = room;
    }
>>>>>>> a2c4c58d25ad307cb999546b00e0c9133b6b1ee0

    public void isAvailable(){
        System.out.println("Rooms available");
       boolean exists=false;
        if (room.getAvailable()){
            boolean exists=true;
        }
        if(!exists){
            System.out.println("no available rooms");
        }
        else {
            System.out.println("available rooms are:");
            for(Room room:Database.rooms) {
                if(room.getAvailable()){
                System.out.println("-" + roomNum);}
            }
        }
    }

}




