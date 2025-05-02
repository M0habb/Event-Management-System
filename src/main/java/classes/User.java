package classes;

import java.util.Date;
import java.util.Scanner;

public abstract class User {
    private String userName;
    private String password;
    private Gender gender;
    private Date birthDate;
    private long phoneNumber;
    private Role role;
    private Wallet wallet;
    public static User currentUser;

    public User(){
        this.wallet = new Wallet();
    }
    public User(String userName, String password, Gender gender, Date birthDate, long phoneNumber){
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.wallet = new Wallet();
    }

    //Setters and Getters for private attributes
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public Gender getGender(){
        return gender;
    }
    public void setBirthDate(Date birthDate){
        this.birthDate = birthDate;
    }
    public Date getBirthDate(){
        return birthDate;
    }
    public void setPhoneNumber(long phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public long getPhoneNumber(){
        return phoneNumber;
    }
    public void setRole(Role role){
        this.role = role;
    }
    public Role getRole(){
        return this.role;
    }
    public Wallet getWallet(){
        return this.wallet;
    }
    public void setWallet(Wallet wallet){
        this.wallet = wallet;
    }

    public static int login(){
        String username, password;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your username: ");
        username = sc.nextLine();

        System.out.println("Enter your password: ");
        password = sc.nextLine();

        for (Admin admin : Database.admins) {
            if (username.equals(admin.getUserName())){
                if (admin.getPassword().equals(password)){
                    System.out.println("Succesful Login. Welcome, " + admin.getUserName());
                    currentUser = admin;
                    return 1;
                }else return 0;
            }
        }

        for (Organizer organizer : Database.organizers) {
            if (username.equals(organizer.getUserName())){
                if (organizer.getPassword().equals(password)){
                    System.out.println("Succesful Login. Welcome, " + organizer.getUserName());
                    currentUser = organizer;
                    return 2;
                }else return 0;
            }
        }

        for (Attendee attendee : Database.totalAttendees) {
            if (username.equals(attendee.getUserName())){
                if (attendee.getPassword().equals(password)){
                    System.out.println("Succesful Login. Welcome, " + attendee.getUserName());
                    currentUser = attendee;
                    return 3;
                }else return 0;
            }
        }

        return 0;

    }
    public abstract void signup();

    public void getUserDetails(){
        System.out.println("Username: " + this.userName);
        System.out.println("Gender: " + this.gender);
        System.out.println("Date of Birth: " + this.birthDate);
        System.out.println("Wallet Balance: " + this.wallet.getBalance());
    }
}
