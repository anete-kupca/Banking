package main;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<Integer, BankAccount> accounts = new HashMap<>();
        boolean exit = false;

        while (!exit){
            System.out.println("--Banking Menu--");
            System.out.println("1 - create an account");
            System.out.println("2 - delete an account");
            System.out.println("3 - deposit");
            System.out.println("4 - withdrawal");
            System.out.println("5 - print balance");
            System.out.println("6 - transfer account");
            System.out.println("7 - Export Report to CSV");
            System.out.println("8 - exit");
            System.out.println("Select option: ");

            String input = sc.nextLine();

            switch(input) {
                case "1": {
                    System.out.print("Enter new account number: ");
                    int newAccNum = Integer.parseInt(sc.nextLine());

                    if (accounts.containsKey(newAccNum)) {
                        System.out.println("Account already exists");
                    } else {
                        BankAccount newAccount = new BankAccount(BigDecimal.ZERO);
                        accounts.put(newAccNum, newAccount);
                        System.out.println("Account " + newAccNum + " created successfully");
                    }
                    break;
                }
                case "2": {
                    System.out.print("Enter account number to delete: ");
                    int deleteAccNum = Integer.parseInt(sc.nextLine());

                    if (accounts.containsKey(deleteAccNum)) {
                        accounts.remove(deleteAccNum);
                        System.out.println("Account " + deleteAccNum + " deleted");
                    } else {
                        System.out.println("Account not found");
                    }
                    break;
                }
                case "3": {
                    int accNum = askAccountNumber(sc);
                    BankAccount acc = accounts.get(accNum);
                    if (acc != null) {
                        System.out.print("Enter deposit amount: ");
                        BigDecimal amt = new BigDecimal(sc.nextLine());
                        acc.deposit(amt);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                }
                case "4": {
                    int accNum = askAccountNumber(sc);
                    BankAccount acc = accounts.get(accNum);
                    if (acc != null) {
                        System.out.print("Enter withdrawal amount: ");
                        BigDecimal amt = new BigDecimal(sc.nextLine());
                        acc.withdraw(amt);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                }
                case "5": {
                    for (BankAccount acc : accounts.values()) {
                        acc.printBalance();
                    }
                    break;
                }
                case "6": {
                    System.out.print("Enter FROM account number: ");
                    int from = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter TO account number: ");
                    int to = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter amount to transfer: ");
                    BigDecimal amt = new BigDecimal(sc.nextLine());

                    BankAccount fromAcc = accounts.get(from);
                    BankAccount toAcc = accounts.get(to);
                    if (fromAcc != null && toAcc != null) {
                        fromAcc.transfer(toAcc, amt);
                    } else {
                        System.out.println("One or both accounts not found.");
                    }
                    break;
                }
                case "7": {
                    exportToCSV(accounts);
                    break;
                }
                case "8": {
                    exit = true;
                    System.out.println("Exiting from banking menu");
                    break;
                }
                default:
                    System.out.println("Invalid option - try again");
            }
        }
        sc.close();
    }

    private static int askAccountNumber(Scanner sc) {
        System.out.print("Enter account number: ");
        return Integer.parseInt(sc.nextLine());
    }

    public static void exportToCSV(Map<Integer, BankAccount> accounts) {
        try (FileWriter writer = new FileWriter("accounts.csv")) {
            writer.write("AccountNumber,Balance" + "\n");
            for (BankAccount account : accounts.values()) {
                writer.write(account.toCSV());
            }
            System.out.println("report saved to file accounts.csv");
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
