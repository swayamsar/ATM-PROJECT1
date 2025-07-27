import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n====== ATM Menu ======");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 4);
    }

    private void checkBalance() {
        System.out.println("Your current balance is ₹" + account.getBalance());
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ₹");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
        } else {
            account.deposit(amount);
            System.out.println("Successfully deposited ₹" + amount);
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ₹");
        double amount = scanner.nextDouble();

        if (account.withdraw(amount)) {
            System.out.println("Please collect your cash: ₹" + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(10000);
        ATM atm = new ATM(userAccount);
        atm.showMenu();
    }
}
