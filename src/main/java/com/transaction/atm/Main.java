package com.transaction.atm;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// User class to represent user
class User {
    private String userId;
    private String pin;
    private double balance;

    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

// Atm class handle the operation
class ATM {
    private Map<String, User> users;

    public ATM() {
        this.users = new HashMap<String, User>();
        // Dummy data for demonstration purposes
        users.put("user123", new User("user123", "1234", 1000.0));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");

        // User login
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (authenticateUser(userId, pin)) {
            System.out.println("Login successful!");

            // Main menu
            while (true) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        displayTransactionsHistory(userId);
                        break;
                    case 2:
                        withdraw(userId, scanner);
                        break;
                    case 3:
                        deposit(userId, scanner);
                        break;
                    case 4:
                        transfer(userId, scanner);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        scanner.close(); // Close the Scanner before exiting
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Login failed. Invalid user ID or PIN.");
        }
    }

    private boolean authenticateUser(String userId, String pin) {
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            return user.getPin().equals(pin);
        }
        return false;
    }

    private void displayTransactionsHistory(String userId) {
        // Dummy 
        System.out.println("Transaction history for user " + userId);
        // actual transaction history 
    }

    private void withdraw(String userId, Scanner scanner) {
        User user = users.get(userId);

        System.out.print("Enter withdrawal amount: ");

        // Check if the input is a valid double
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();

            if (amount > 0 && amount <= user.getBalance()) {
                user.setBalance(user.getBalance() - amount);
                System.out.println("Withdrawal successful. Remaining balance: $" + user.getBalance());
                // actual withdrawal transaction 
            } else {
                System.out.println("Invalid withdrawal amount or insufficient funds.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); 
        }
    }

    private void deposit(String userId, Scanner scanner) {
        User user = users.get(userId);

        System.out.print("Enter deposit amount: ");

        
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();

            if (amount > 0) {
                user.setBalance(user.getBalance() + amount);
                System.out.println("Deposit successful. New balance: $" + user.getBalance());
                
            } else {
                System.out.println("Invalid deposit amount.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // consume the invalid input
        }
    }

    private void transfer(String userId, Scanner scanner) {
        User user = users.get(userId);

        System.out.print("Enter recipient's user ID: ");
        String recipientId = scanner.nextLine();

        if (users.containsKey(recipientId)) {
            User recipient = users.get(recipientId);

            System.out.print("Enter transfer amount: ");

            // Check if the input is a valid double
            if (scanner.hasNextDouble()) {
                double amount = scanner.nextDouble();

                if (amount > 0 && amount <= user.getBalance()) {
                    user.setBalance(user.getBalance() - amount);
                    recipient.setBalance(recipient.getBalance() + amount);
                    System.out.println("Transfer successful. Remaining balance: $" + user.getBalance());
                    
                } else {
                    System.out.println("Invalid transfer amount or insufficient funds.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // consume the invalid input
            }
        } else {
            System.out.println("Recipient not found.");
        }
    }
}

//  start the ATM application
public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
