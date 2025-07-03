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

}
