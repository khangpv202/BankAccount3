package Transaction;

import TransactionDAO.TransactionDAO;
import TransactionDTO.TransactionDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 6/27/13
 * Time: 11:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction {
    private static TransactionDAO transactionDao ;

    public static void setMockTransactionDAO(TransactionDAO transactionDAO) {
        transactionDao=transactionDAO;
    }

    public static void save(TransactionDTO transaction) {
        transactionDao.save(transaction);
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber) {
        return transactionDao.getTransactionsOccurred(accountNumber);
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber, long startTime, long stopTime) {
        return transactionDao.getTransactionsOccurred(accountNumber,startTime,stopTime);
    }

    public static List<TransactionDTO> getAmountNumberOfTransactions(String accountNumber, int numberOfTransaction) {
        return transactionDao.getAmountNumberOfTransactions(accountNumber,numberOfTransaction);
    }
}
