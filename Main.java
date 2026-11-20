import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        final int CORRECT_PIN = 1234;
        final int MAX_ATTEMPTS = 3;
        int attempts = 0;
        boolean loggedIn = false;

        double balance = 0.0;
        double[] transactions = new double[100];
        int transactionCount = 0;

        System.out.println("==================================");
        System.out.println("       WELCOME TO THE ATM");
        System.out.println("==================================");

        // PIN authentication: maximum three attempts
        while (attempts < MAX_ATTEMPTS && !loggedIn) {
            System.out.print("Enter your PIN: ");
            int enteredPin = input.nextInt();

            if (enteredPin == CORRECT_PIN) {
                loggedIn = true;
                System.out.println("Login successful!");
            } else {
                attempts++;
                System.out.println("Incorrect PIN.");
                if (attempts < MAX_ATTEMPTS) {
                    System.out.println("Attempts remaining: "
                            + (MAX_ATTEMPTS - attempts));
                }
            }
        }

        if (!loggedIn) {
            System.out.println("Your account is locked. Goodbye.");
        } else {
            int choice = 0;

            // Repeat the menu until the user selects option 5
            while (choice != 5) {
                System.out.println();
                System.out.println("========== ATM MENU ==========");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. View Transactions");
                System.out.println("5. Exit");
                System.out.println("==============================");
                System.out.print("Enter your choice: ");
                choice = input.nextInt();

                // Select an action using switch
                switch (choice) {
                    case 1:
                        System.out.printf("Your current balance is: $%.2f%n",
                                balance);
                        break;

                    case 2:
                        System.out.print("Enter amount to deposit: $");
                        double deposit = input.nextDouble();

                        if (deposit <= 0) {
                            System.out.println(
                                    "Invalid amount. Deposit must be greater than zero.");
                        } else if (transactionCount >= transactions.length) {
                            System.out.println("Transaction history is full.");
                        } else {
                            balance += deposit;
                            transactions[transactionCount] = deposit;
                            transactionCount++;
                            System.out.println("Deposit successful.");
                            System.out.printf("New balance: $%.2f%n", balance);
                        }
                        break;

                    case 3:
                        System.out.print("Enter amount to withdraw: $");
                        double withdrawal = input.nextDouble();

                        if (withdrawal <= 0) {
                            System.out.println(
                                    "Invalid amount. Withdrawal must be greater than zero.");
                        } else if (withdrawal > balance) {
                            System.out.println("Error: Insufficient balance.");
                        } else if (transactionCount >= transactions.length) {
                            System.out.println("Transaction history is full.");
                        } else {
                            balance -= withdrawal;
                            // A negative value records a withdrawal
                            transactions[transactionCount] = -withdrawal;
                            transactionCount++;
                            System.out.println("Withdrawal successful.");
                            System.out.printf("New balance: $%.2f%n", balance);
                        }
                        break;

                    case 4:
                        System.out.println("======= TRANSACTION HISTORY =======");
                        if (transactionCount == 0) {
                            System.out.println("No transactions recorded yet.");
                        } else {
                            // Print transactions in the order they occurred
                            for (int i = 0; i < transactionCount; i++) {
                                if (transactions[i] > 0) {
                                    System.out.printf("Deposit: +$%.2f%n",
                                            transactions[i]);
                                } else {
                                    System.out.printf("Withdrawal: -$%.2f%n",
                                            -transactions[i]);
                                }
                            }
                        }
                        System.out.println("===================================");
                        break;

                    case 5:
                        System.out.println("Thank you for using our ATM.");
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please select a number from 1 to 5.");
                        break;
                }
            }
        }

        input.close();
    }
}
