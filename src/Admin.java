import java.util.Date;

public class Admin extends User implements Crud<Category>{
    private int workingHours;
    Admin(){
        super();
    }
    Admin(String userName, String password, Gender gender, Date birthDate, long phoneNumber, int workingHours){
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

    }
    public void showEvents(){

    }
  

    @Override
    public void signup() {

    }

    public void showAttendees(){

    }
    public void showOrganizers(){

    }
    public void addRoom(){

    }


    @Override
    public void create() {

    }

    @Override
    public void read() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}