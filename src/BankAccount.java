import java.math.BigDecimal;

public class BankAccount {
    private BigDecimal balance;

    public BankAccount(BigDecimal balance) {this.balance = balance;}
    public BigDecimal getBalance() {return balance;}
    public void setBalance(BigDecimal balance) {this. balance = balance;}

    public void deposit(BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) > 0){
            balance = balance.add(amount);
            System.out.println("Deposited: " + amount + " $");
        } else {
            System.out.println("Deposit amount must be positive");
        }
    }
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Withdrawal amount must be positive");
        } else if (amount.compareTo(balance) > 0) {
            System.out.println("Insufficient funds");
        } else {
            balance = balance.subtract(amount);
            System.out.println("Withdrew: " + amount + " $");
        }
    }

    public void printBalance() {
        System.out.println("Current balance: " + balance + " $");
    }

    public void transfer(BankAccount other, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Transfer amount must be positive");
        } else if (amount.compareTo(this.balance) > 0) {
            System.out.println("Insufficient funds");
        } else {
            this.withdraw(amount);
            other.deposit(amount);
            System.out.println("Transferred " + amount + " $ to another account");
        }
    }

}
