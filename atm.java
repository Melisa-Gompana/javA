import java.util.ArrayList;
import java.util.Scanner;

// Class representing the Bank Account
class Account {
    private double balance;
    private int pin;
    private ArrayList<String> transactionHistory;

    // Constructor to initialize account with a default balance and PIN
    public Account(double initialBalance, int initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
    }

    // Method to check account balance
    public double getBalance() {
        return balance;
    }

    // Method to withdraw cash
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount);
            return true;
        } else {
            return false;
        }
    }

    // Method to deposit cash
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
        }
    }

    // Method to change PIN
    public boolean changePin(int oldPin, int newPin) {
        if (this.pin == oldPin) {
            this.pin = newPin;
            transactionHistory.add("PIN Changed Successfully");
            return true;
        } else {
            return false;
        }
    }

    // Method to verify PIN
    public boolean verifyPin(int enteredPin) {
        return this.pin == enteredPin;
    }

    // Method to display transaction history
    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println("- " + transaction);
            }
        }
    }
}

// Main ATM Machine Class
public class ATMSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating an account with initial balance $1000 and PIN 1234
        Account account = new Account(1000.0, 1234);

        System.out.println("Welcome to the ATM Machine!");
        System.out.print("Please enter your PIN: ");
        int enteredPin = scanner.nextInt();

        // Verify PIN
        if (!account.verifyPin(enteredPin)) {
            System.out.println("Incorrect PIN. Exiting...");
            return;
        }

        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Cash Withdrawal");
            System.out.println("3. Cash Deposit");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Balance Inquiry
                    System.out.println("Current Balance: $" + account.getBalance());
                    break;

                case 2:
                    // Cash Withdrawal
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance or invalid amount.");
                    }
                    break;

                case 3:
                    // Cash Deposit
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;

                case 4:
                    // Change PIN
                    System.out.print("Enter current PIN: ");
                    int currentPin = scanner.nextInt();
                    System.out.print("Enter new PIN: ");
                    int newPin = scanner.nextInt();
                    if (account.changePin(currentPin, newPin)) {
                        System.out.println("PIN changed successfully.");
                    } else {
                        System.out.println("Incorrect current PIN.");
                    }
                    break;

                case 5:
                    // Transaction History
                    account.showTransactionHistory();
                    break;

                case 6:
                    // Exit
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}