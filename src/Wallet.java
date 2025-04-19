public class Wallet {

    private int walletID;
    private double balance;
    Wallet(){

    }
    Wallet(int walletID){
        this.walletID = walletID;
        balance = 0;
    }

    //Setters & Getters
    public void setWalletID(int walletID){
        this.walletID = walletID;
    }

    public int getWalletID() {
        return walletID;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount){
        if (amount < 0){
            throw new IllegalArgumentException("Cannot add negative amount.");
        }
        balance += amount;
    }
    public void deductBalance(double amount){
        if (amount < 0){
            throw new IllegalArgumentException("Cannot deduct negative amount.");
        }else if (amount > balance){
            throw new IllegalArgumentException("Insufficient funds.");
        }
        balance -= amount;
    }
}