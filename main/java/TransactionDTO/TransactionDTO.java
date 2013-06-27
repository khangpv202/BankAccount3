package TransactionDTO;

import BankAccountDTO.AccountDTO;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 6/27/13
 * Time: 11:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDTO {
    private String accountNumber;
    private double amount;
    private String description;
    private long timestemp;

    public TransactionDTO(String accountDTO, double amount, String description) {
        this.accountNumber=accountDTO;
        this.amount=amount;
        this.description=description;
        this.timestemp=System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestemp;  //To change body of created methods use File | Settings | File Templates.
    }
}
