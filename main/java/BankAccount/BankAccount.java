package BankAccount;

import BankAccountDAO.BankAccoutDAO;
import BankAccountDTO.AccountDTO;
import TransactionDTO.TransactionDTO;
import Transaction.Transaction;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 6/27/13
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    private  static BankAccoutDAO accoutDAO ;
    public static void setMockAccountDAO(BankAccoutDAO mockAccountDAO) {
        accoutDAO=mockAccountDAO;
    }

    public static AccountDTO open(String accountNumber) {
        AccountDTO account = new AccountDTO(accountNumber);
        accoutDAO.save(account);
        return account;
    }

    public static AccountDTO getAccount(String accountNumber) {
        return accoutDAO.getAccount(accountNumber);
    }

    public static TransactionDTO deposit(String accountNumber, double amount, String description) {
        AccountDTO accountDTO= accoutDAO.getAccount(accountNumber);
        double balance = accountDTO.getBalance() + amount;
        accountDTO.setBalance(balance);
        accoutDAO.save(accountDTO);
        TransactionDTO transaction = new TransactionDTO(accountNumber,amount,description);
        Transaction.save(transaction);
        return transaction;

    }

    public static TransactionDTO  withdraw(String accountNumber, double amount, String description) {
        AccountDTO accountDTO= accoutDAO.getAccount(accountNumber);
        double balance = accountDTO.getBalance() + amount;
        accountDTO.setBalance(balance);
        accoutDAO.save(accountDTO);
        TransactionDTO transaction = new TransactionDTO(accountNumber,amount,description);
        Transaction.save(transaction);
        return transaction;
    }
}
