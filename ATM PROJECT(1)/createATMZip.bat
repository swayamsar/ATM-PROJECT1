@echo off
mkdir ATMProject
cd ATMProject
(
echo import java.util.Scanner^;

echo class BankAccount {
echo     private double balance;
echo     public BankAccount(double initialBalance) {
echo         balance = initialBalance;
echo     }
echo     public double getBalance() { return balance; }
echo     public boolean withdraw(double amount) {
echo         if (amount > 0 && amount <= balance) {
echo             balance -= amount;
echo             return true;
echo         } else return false;
echo     }
echo     public void deposit(double amount) {
echo         if (amount > 0) balance += amount;
echo     }
echo }
echo class ATM {
echo     private BankAccount account;
echo     private Scanner scanner;
echo     public ATM(BankAccount account) {
echo         this.account = account;
echo         scanner = new Scanner(System.in);
echo     }
echo     public void showMenu() {
echo         int choice;
echo         do {
echo             System.out.println("1. Check Balance\n2. Deposit\n3. Withdraw\n4. Exit");
echo             choice = scanner.nextInt();
echo             switch (choice) {
echo                 case 1: checkBalance(); break;
echo                 case 2: deposit(); break;
echo                 case 3: withdraw(); break;
echo                 case 4: System.out.println("Goodbye!"); break;
echo                 default: System.out.println("Invalid"); 
echo             }
echo         } while (choice != 4);
echo     }
echo     private void checkBalance() { System.out.println("Balance: ₹" + account.getBalance()); }
echo     private void deposit() {
echo         System.out.print("Deposit ₹: ");
echo         double amt = scanner.nextDouble();
echo         if (amt > 0) { account.deposit(amt); System.out.println("Deposited ₹" + amt); }
echo         else System.out.println("Invalid");
echo     }
echo     private void withdraw() {
echo         System.out.print("Withdraw ₹: ");
echo         double amt = scanner.nextDouble();
echo         if (account.withdraw(amt)) System.out.println("Withdrawn ₹" + amt);
echo         else System.out.println("Insufficient balance");
echo     }
echo }
echo public class ATMInterface {
echo     public static void main(String[] args) {
echo         BankAccount acc = new BankAccount(10000);
echo         ATM atm = new ATM(acc);
echo         atm.showMenu();
echo     }
echo }

) > ATMInterface.java

(
echo import javax.swing.*;
echo import java.awt.*;
echo import java.awt.event.*;

echo class BankAccount {
echo     private double balance;
echo     public BankAccount(double initialBalance) { balance = initialBalance; }
echo     public double getBalance() { return balance; }
echo     public boolean withdraw(double amount) {
echo         if (amount > 0 && amount <= balance) {
echo             balance -= amount;
echo             return true;
echo         }
echo         return false;
echo     }
echo     public void deposit(double amount) {
echo         if (amount > 0) balance += amount;
echo     }
echo }

echo public class ATMGUI extends JFrame implements ActionListener {
echo     private BankAccount account;
echo     private JLabel messageLabel;
echo     private JTextField amountField;
echo     public ATMGUI() {
echo         account = new BankAccount(10000);
echo         setTitle("ATM Interface");
echo         setSize(400, 300);
echo         setDefaultCloseOperation(EXIT_ON_CLOSE);
echo         setLayout(new GridLayout(6, 1, 10, 10));
echo         messageLabel = new JLabel("Welcome to the ATM", JLabel.CENTER);
echo         add(messageLabel);
echo         amountField = new JTextField();
echo         add(amountField);
echo         JButton checkBalanceBtn = new JButton("Check Balance");
echo         JButton depositBtn = new JButton("Deposit");
echo         JButton withdrawBtn = new JButton("Withdraw");
echo         JButton exitBtn = new JButton("Exit");
echo         add(checkBalanceBtn); add(depositBtn); add(withdrawBtn); add(exitBtn);
echo         checkBalanceBtn.addActionListener(this);
echo         depositBtn.addActionListener(this);
echo         withdrawBtn.addActionListener(this);
echo         exitBtn.addActionListener(this);
echo         setVisible(true);
echo     }
echo     public void actionPerformed(ActionEvent e) {
echo         String cmd = e.getActionCommand();
echo         String input = amountField.getText();
echo         switch (cmd) {
echo             case "Check Balance":
echo                 messageLabel.setText("Balance: ₹" + account.getBalance()); break;
echo             case "Deposit":
echo                 try {
echo                     double amt = Double.parseDouble(input);
echo                     if (amt > 0) { account.deposit(amt); messageLabel.setText("Deposited ₹" + amt); }
echo                     else messageLabel.setText("Enter positive amount");
echo                 } catch (Exception ex) { messageLabel.setText("Invalid input"); }
echo                 break;
echo             case "Withdraw":
echo                 try {
echo                     double amt = Double.parseDouble(input);
echo                     if (account.withdraw(amt)) messageLabel.setText("Withdrawn ₹" + amt);
echo                     else messageLabel.setText("Insufficient balance");
echo                 } catch (Exception ex) { messageLabel.setText("Invalid input"); }
echo                 break;
echo             case "Exit":
echo                 JOptionPane.showMessageDialog(this, "Thank you!");
echo                 System.exit(0); break;
echo         }
echo         amountField.setText("");
echo     }
echo     public static void main(String[] args) {
echo         new ATMGUI();
echo     }
echo }

) > ATMGUI.java

cd ..
powershell Compress-Archive -Path ATMProject -DestinationPath ATMProject.zip

echo Done! Your zip file is ready as ATMProject.zip
pause
