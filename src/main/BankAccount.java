package main;

import java.math.BigDecimal;

public class BankAccount {
    private BigDecimal balance;
    private static int counter = 1;
    private int accountNumber;

    public BankAccount(BigDecimal balance) {
        this.balance = balance;
        this.accountNumber = counter++;
    }
    public BigDecimal getBalance() {return balance;}
    public void setBalance(BigDecimal balance) {this. balance = balance;}

    public int getAccountNumber() {return accountNumber;}
    public void setAccountNumber(int accountNumber) {this.accountNumber = accountNumber;}

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

    public String toCSV() {return accountNumber + "," + balance;}
}
