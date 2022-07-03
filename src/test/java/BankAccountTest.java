import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

import Exceptions.EnoughException;
import entities.Account;
import entities.History;
import enumeration.OperationType;

/**
 * @author elouhichi
 * @created 29/06/2022 - 20:50
 * @project KataBankAccount
 */
public class BankAccountTest {

    @Mock
    private Account account;

    @Before
    public void init() {
        // initialize balance
        this.account = new Account(new BigDecimal(100) , new History());
    }

    @Test
    public void verifyInitBalance() {
        //Given
        BigDecimal balance = this.account.getBalance();
        //Then
        assertEquals(new BigDecimal(100), balance);
    }

    @Test(expected = EnoughException.class)
    public void withdrawInvalidAmount() throws EnoughException {
        //When
        this.account.withdraw(new BigDecimal(200));
    }

    @Test
    public void withdrawAmountEqualBalance() throws EnoughException {
        //When
        this.account.withdraw(new BigDecimal(100));
        //Then
        assertEquals(BigDecimal.ZERO, this.account.getBalance());
    }

    @Test()
    public void withdrawValidAmount() throws EnoughException {
        //When
        this.account.withdraw(new BigDecimal(50));

        //Then
        assertEquals(new BigDecimal(50), this.account.getBalance());

    }

    @Test()
    public void depositValidAmount() throws EnoughException {
        //When
        this.account.deposit(new BigDecimal(100));

        //Then
        assertEquals(new BigDecimal(200), this.account.getBalance());

    }

    @Test(expected = IllegalArgumentException.class)
    public void depositInvalidAmount() throws IllegalArgumentException {
        //When
        this.account.deposit(new BigDecimal(-50));

    }

    @Test
    public void depositHistory() throws EnoughException {
        //When
        this.account.deposit(new BigDecimal(100));
        //Then
        assertEquals(OperationType.DEPOSIT , this.account.getHistory().getStatement().get(0).getType());
    }

    @Test
    public void withdrawHistory() throws EnoughException {
        //When
        this.account.withdraw(new BigDecimal(50));
        //Then
        assertEquals(OperationType.WITHDRAW , this.account.getHistory().getStatement().get(0).getType());
    }

    @Test
    public void printHistory() throws EnoughException {
        //When
        this.account.withdraw(new BigDecimal(50));
        this.account.deposit(new BigDecimal(100));
         String statementToEvaluate = "Operation | Date | Amount | Balance\n" +
                 "WITHDRAW | 2022-07-03 | 50 | 50\n" +
                 "DEPOSIT | 2022-07-03 | 100 | 150\n" +
                 "Total Withdraw | 50.0\n" +
                 "Total deposit | 100.0\n" +
                 "Balance | 150\n";
        //Then
        assertEquals(this.account.getHistory().printStatement(),statementToEvaluate);

    }
}
