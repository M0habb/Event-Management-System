import java.util.Date;;
public abstract class User {
    private String userName;
    private String password;
    private Gender gender;
    private Date birthDate;
    private long phoneNumber;
    private Role role;
    private Wallet wallet;
    User(){

    }
    User(String userName, String password, Gender gender, Date birthDate, long phoneNumber){
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

    public boolean login(){
        return true;
    }
    public abstract void signup();

    public void getUserDetails(){
        System.out.println("Username: " + this.userName);
        System.out.println("Gender: " + this.gender);
        System.out.println("Date of Birth: " + this.birthDate);
        System.out.println("Wallet ID: " + this.wallet.getWalletID());
    }
}
