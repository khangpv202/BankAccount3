import BankAccount.BankAccount;
import BankAccountDAO.BankAccoutDAO;
import BankAccountDTO.AccountDTO;
import Transaction.Transaction;
import TransactionDAO.TransactionDAO;
import TransactionDTO.TransactionDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 6/27/13
 * Time: 9:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestBankAccount {
    BankAccoutDAO mockAccountDAO = mock(BankAccoutDAO.class);
    TransactionDAO mockTransaction = mock(TransactionDAO.class);
    private String accountNumber = "0123456789";

    @Before
    public void initial(){
        reset(mockAccountDAO);
        BankAccount.setMockAccountDAO(mockAccountDAO);
        reset(mockTransaction);
        Transaction.setMockTransactionDAO(mockTransaction);
    }
    @Test
    public void testOpenNewAccount(){

        AccountDTO initialAccount = BankAccount.open(accountNumber);
        ArgumentCaptor<AccountDTO> savedAccount = ArgumentCaptor.forClass(AccountDTO.class);
        verify(mockAccountDAO).save(savedAccount.capture());
        assertEquals(savedAccount.getValue().getBalance(),0,0.001);
        assertEquals(savedAccount.getValue().getAccountNumber(),accountNumber);
        assertEquals(savedAccount.getValue().getTimestamp(), initialAccount.getTimestamp());
    }
    @Test
    public void testGetAccount(){
        AccountDTO initialAccount = BankAccount.open(accountNumber);
        BankAccount.getAccount(accountNumber);
        verify(mockAccountDAO).getAccount(accountNumber);
    }
    @Test
    public void testDiposit(){
        AccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockAccountDAO.getAccount(accountNumber)).thenReturn(initialAccount);
        BankAccount.deposit(accountNumber, 10, "first deposit");
        BankAccount.deposit(accountNumber, 10, "second deposit");
        ArgumentCaptor<AccountDTO>savedAccount = ArgumentCaptor.forClass(AccountDTO.class);
        verify(mockAccountDAO,times(3)).save(savedAccount.capture());
        assertEquals(savedAccount.getValue().getBalance(),20,0.001);
    }
    @Test
    public void testDepositTransaction(){
        AccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockAccountDAO.getAccount(accountNumber)).thenReturn(initialAccount);
        TransactionDTO transaction = BankAccount.deposit(accountNumber, 10, "first deposit");
        ArgumentCaptor<TransactionDTO> savedTransaction = ArgumentCaptor.forClass(TransactionDTO.class);
        verify(mockTransaction).save(savedTransaction.capture());
        assertEquals(savedTransaction.getValue().getTimestamp(),transaction.getTimestamp());
    }
    @Test
    public void testWithdraw(){
        AccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockAccountDAO.getAccount(accountNumber)).thenReturn(initialAccount);
        BankAccount.deposit(accountNumber, 10, "first deposit");
        TransactionDTO transaction = BankAccount.withdraw(accountNumber, -10, "first withdraw");
        ArgumentCaptor<AccountDTO>savedAccount = ArgumentCaptor.forClass(AccountDTO.class);
        verify(mockAccountDAO,times(3)).save(savedAccount.capture());
        assertEquals(savedAccount.getValue().getBalance(),0,0.001);
    }
    @Test
    public void testWithdrawTransaction(){
        AccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockAccountDAO.getAccount(accountNumber)).thenReturn(initialAccount);
        BankAccount.deposit(accountNumber, 10, "first deposit");
        TransactionDTO transaction = BankAccount.withdraw(accountNumber, -10, "first deposit");
        ArgumentCaptor<TransactionDTO> savedTransaction = ArgumentCaptor.forClass(TransactionDTO.class);
        verify(mockTransaction,times(2)).save(savedTransaction.capture());
        assertEquals(savedTransaction.getValue().getTimestamp(),transaction.getTimestamp());
    }
    @Test
    public void testGetTransactionsOccurred(){
        AccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockAccountDAO.getAccount(accountNumber)).thenReturn(initialAccount);
        List<TransactionDTO> listTransaction = new ArrayList<TransactionDTO>();
        TransactionDTO first = BankAccount.deposit(accountNumber, 10, "first deposit");
        TransactionDTO second = BankAccount.withdraw(accountNumber, -10, "first withdraw");
        TransactionDTO third = BankAccount.withdraw(accountNumber, -10, "second withdraw");
        listTransaction.add(first);
        listTransaction.add(second);
        listTransaction.add(third);
        when(mockTransaction.getTransactionsOccurred(accountNumber)).thenReturn(listTransaction);
        List<TransactionDTO> savedList = Transaction.getTransactionsOccurred(accountNumber);
        assertEquals(listTransaction,savedList);
    }
    @Test
    public void testTransactionInInterval(){
        AccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockAccountDAO.getAccount(accountNumber)).thenReturn(initialAccount);
        long startTime = 0,stopTime = 1000l;
        Transaction.getTransactionsOccurred(accountNumber,startTime,stopTime);
        verify(mockTransaction).getTransactionsOccurred(accountNumber,startTime,stopTime);
    }
    @Test
    public void testGetNewestTransaction(){
        AccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockAccountDAO.getAccount(accountNumber)).thenReturn(initialAccount);
        Transaction.getAmountNumberOfTransactions(accountNumber,2);
        verify(mockTransaction).getAmountNumberOfTransactions(accountNumber,2);
    }


}
