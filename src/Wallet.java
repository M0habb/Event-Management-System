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

    public int addBalance(double amount){
        if (amount < 0){
            return 1;
        }
        balance += amount;
        return 0;
    }
    public int deductBalance(double amount){
        if (amount < 0){
            return 1;
        }else if (amount > balance){
            return 2;
        }
        balance -= amount;
        return 0;
    }
}