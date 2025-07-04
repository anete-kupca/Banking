import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount(BigDecimal.valueOf(100.00));
    }

    @Test
    void getBalance() {
        assertEquals(BigDecimal.valueOf(100.00), account.getBalance());
    }

    @Test
    void setBalance() {
        account.setBalance(BigDecimal.valueOf(250.50));
        assertEquals(BigDecimal.valueOf(250.50), account.getBalance());
    }

    @Test
    void getAccountNumber() {
        int accNum = account.getAccountNumber();
        assertTrue(accNum > 0); // since it's auto-incremented
    }

    @Test
    void setAccountNumber() {
        account.setAccountNumber(999);
        assertEquals(999, account.getAccountNumber());
    }

    @Test
    void depositValidAmount() {
        account.deposit(BigDecimal.valueOf(50.00));
        assertEquals(BigDecimal.valueOf(150.00), account.getBalance());
    }

    @Test
    void depositInvalidAmount() {
        account.deposit(BigDecimal.valueOf(-10.00));
        assertEquals(BigDecimal.valueOf(100.00), account.getBalance()); // should remain unchanged
    }

    @Test
    void withdrawValidAmount() {
        account.withdraw(BigDecimal.valueOf(30.00));
        assertEquals(BigDecimal.valueOf(70.00), account.getBalance());
    }

    @Test
    void withdrawMoreThanBalance() {
        account.withdraw(BigDecimal.valueOf(200.00));
        assertEquals(BigDecimal.valueOf(100.00), account.getBalance()); // should remain unchanged
    }

    @Test
    void withdrawNegativeAmount() {
        account.withdraw(BigDecimal.valueOf(-10.00));
        assertEquals(BigDecimal.valueOf(100.00), account.getBalance()); // should remain unchanged
    }

    @Test
    void transferValidAmount() {
        BankAccount recipient = new BankAccount(BigDecimal.valueOf(50.00));
        account.transfer(recipient, BigDecimal.valueOf(25.00));
        assertEquals(BigDecimal.valueOf(75.00), account.getBalance());
        assertEquals(BigDecimal.valueOf(75.00), recipient.getBalance());
    }

    @Test
    void transferMoreThanBalance() {
        BankAccount recipient = new BankAccount(BigDecimal.valueOf(50.00));
        account.transfer(recipient, BigDecimal.valueOf(200.00));
        assertEquals(BigDecimal.valueOf(100.00), account.getBalance());
        assertEquals(BigDecimal.valueOf(50.00), recipient.getBalance());
    }

    @Test
    void toCSV() {
        String expected = account.getAccountNumber() + "," + account.getBalance();
        assertEquals(expected, account.toCSV());
    }

    @Test
    void printBalanceDoesNotThrow() {
        assertDoesNotThrow(() -> account.printBalance());
    }
}