import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankAccount account = new BankAccount(new BigDecimal("50.00"));

        boolean exit = false;

        while (!exit){
            System.out.println("--Banking Menu--");
            System.out.println("1 - deposit");
            System.out.println("2 - withdrawal");
            System.out.println("3 - print balance");
            System.out.println("4 - exit");
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
                    account.printBalance();
                    break;
                case "4":
                    exit = true;
                    System.out.println("Exiting from banking menu");
                    break;
                default:
                    System.out.println("Invalid option - try again");
            }
        }
        sc.close();
    }
}