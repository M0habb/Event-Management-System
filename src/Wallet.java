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

    public double addBalance(){
        return 0;
    }
    public double deductBalance(){
        return 0;
    }
}