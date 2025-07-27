import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class BankAccount {
    private double balance;
    private ArrayList<String> history = new ArrayList<>();

    public BankAccount(double initialBalance) {
        balance = initialBalance;
        history.add("Account opened with ₹" + initialBalance);
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            history.add("Withdrew ₹" + amount);
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            history.add("Deposited ₹" + amount);
        }
    }

    public void takeLoan(double amount) {
        if (amount > 0) {
            balance += amount;
            history.add("Loan granted ₹" + amount);
        }
    }

    public void resetAccount() {
        balance = 10000;
        history.add("Account reset to ₹10000");
    }

    public ArrayList<String> getHistory() {
        return history;
    }
}

public class ATMGUI extends JFrame implements ActionListener {
    private BankAccount account;
    private JLabel messageLabel;
    private JTextField amountField;
    private JPanel panel;
    private ImageIcon backgroundIcon;

    public ATMGUI() {
        account = new BankAccount(10000);
        setTitle("Enhanced ATM Interface");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Load background image
        backgroundIcon = new ImageIcon("img.jpg"); // Ensure this file is in the same folder
        Image backgroundImage = backgroundIcon.getImage();

        // Custom JPanel with background
        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        add(backgroundPanel);

        // Transparent inner panel
        panel = new JPanel(new GridLayout(9, 1, 10, 10));
        panel.setOpaque(false);

        messageLabel = new JLabel("Welcome to the ATM", JLabel.CENTER);
        messageLabel.setForeground(Color.WHITE);
        panel.add(messageLabel);

        amountField = new JTextField();
        panel.add(amountField);

        String[] buttons = {
            "Check Balance", "Deposit", "Withdraw",
            "Take Loan", "View Transaction History", "Reset Account", "Exit"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.addActionListener(this);
            panel.add(btn);
        }

        backgroundPanel.add(panel); // add transparent panel to background
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String input = amountField.getText();

        switch (command) {
            case "Check Balance":
                messageLabel.setText("Balance: ₹" + account.getBalance());
                break;

            case "Deposit":
                try {
                    double amt = Double.parseDouble(input);
                    if (amt > 0) {
                        account.deposit(amt);
                        messageLabel.setText("Deposited ₹" + amt);
                    } else {
                        messageLabel.setText("Enter positive amount");
                    }
                } catch (Exception ex) {
                    messageLabel.setText("Invalid input");
                }
                break;

            case "Withdraw":
                try {
                    double amt = Double.parseDouble(input);
                    if (account.withdraw(amt)) {
                        messageLabel.setText("Withdrawn ₹" + amt);
                    } else {
                        messageLabel.setText("Insufficient balance");
                    }
                } catch (Exception ex) {
                    messageLabel.setText("Invalid input");
                }
                break;

            case "Take Loan":
                try {
                    double amt = Double.parseDouble(input);
                    if (amt > 0) {
                        account.takeLoan(amt);
                        messageLabel.setText("Loan granted ₹" + amt);
                    } else {
                        messageLabel.setText("Enter valid loan amount");
                    }
                } catch (Exception ex) {
                    messageLabel.setText("Invalid input");
                }
                break;

            case "View Transaction History":
                ArrayList<String> history = account.getHistory();
                JTextArea textArea = new JTextArea();
                for (String entry : history) {
                    textArea.append(entry + "\n");
                }
                textArea.setEditable(false);
                JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Transaction History", JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Reset Account":
                account.resetAccount();
                messageLabel.setText("Account reset to ₹10000");
                break;

            case "Exit":
                JOptionPane.showMessageDialog(this, "Thank you for using the ATM!");
                System.exit(0);
                break;
        }

        amountField.setText("");
    }

    public static void main(String[] args) {
        new ATMGUI();
    }
}
