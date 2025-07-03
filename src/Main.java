import java.math.BigDecimal;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<BankAccount> accounts = new ArrayList<>();

        BankAccount account = new BankAccount(new BigDecimal("50.00"));
        BankAccount account2 = new BankAccount(new BigDecimal("0.00"));

        accounts.add(account);
        accounts.add(account2);
        boolean exit = false;

        while (!exit){
            System.out.println("--Banking Menu--");
            System.out.println("1 - deposit");
            System.out.println("2 - withdrawal");
            System.out.println("3 - print balance");
            System.out.println("4 - transfer account");
            System.out.println("5 - Export Report to CSV");
            System.out.println("6 - exit");
            System.out.println("Select option: ");

            String input = sc.nextLine();

            switch(input) {
                case "1":
                    System.out.print("Enter deposit amount: ");
                    BigDecimal depositAmount = new BigDecimal(sc.nextLine());
                    account.deposit(depositAmount);
                    break;
                case "2":
                    System.out.println("Enter withdrawal amount: ");
                    BigDecimal withdrawalAmount = new BigDecimal(sc.nextLine());
                    account.withdraw(withdrawalAmount);
                case "3":
                    for (BankAccount acc : accounts) {
                        acc.printBalance();
                    }
                    break;
                case "4":
                    System.out.print("Enter amount to transfer to another account: ");
                    BigDecimal transferAmount = new BigDecimal(sc.nextLine());
                    account.transfer(account2, transferAmount);
                    break;
                case "5":
                    exportToCSV(accounts);
                    break;
                case "6":
                    exit = true;
                    System.out.println("Exiting from banking menu");
                    break;
                default:
                    System.out.println("Invalid option - try again");
            }
        }
        sc.close();
    }
    public static void exportToCSV(ArrayList<BankAccount> accounts) {
        try (FileWriter writer = new FileWriter("accounts.csv")) {
            writer.write("AccountNumber,Balance");
            for (BankAccount account : accounts) {
                writer.write(account.toCSV());
            }
            System.out.println("report saved to file accounts.csv");
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
