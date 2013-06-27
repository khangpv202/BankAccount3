package BankAccountDTO;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 6/27/13
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountDTO {
    private String accountNumber;
    private double balance;
    private long timestamp;

    public AccountDTO(String accountNumber) {
        this.accountNumber= accountNumber;
        this.balance=0;
        this.timestamp= System.currentTimeMillis();
    }

    public double getBalance() {
        return balance;  //To change body of created methods use File | Settings | File Templates.
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

   /* public String toString(){
        System.out.println(accountNumber+"  "+ balance);
        return null;
    }*/
}
