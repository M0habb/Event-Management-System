import java.util.ArrayList;

public class Room {

    private int roomNum;
    private Size size; //no. of ppl
    private boolean available;
    private ArrayList<Room> Room;
    public ArrayList<String> room = new ArrayList<>();

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
    public void setRoom(ArrayList<Room> room) {
        Room = room;
    }

    public void isAvailable(){
        System.out.println("Rooms available");
        if (Room.isEmpty()){
            System.out.println("no available rooms");
        }
        else {
            System.out.println("available rooms are:");
            for(Room room:Room) {
                System.out.println("-" + room);
            }
        }
    }


}




