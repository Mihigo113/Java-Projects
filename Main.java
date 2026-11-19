import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US);

        final double DRINK_PRICE = 15.50;
        final double STUDENT_DISCOUNT_RATE = 0.10;
        final double SENIOR_DISCOUNT_RATE = 0.05;
        final double TIP_RATE = 0.10;

        System.out.println("==================================");
        System.out.println("       SMART RESTAURANT ORDER");
        System.out.println("==================================");

        System.out.print("Enter customer's name: ");
        String customerName = input.nextLine();

        System.out.print("Enter customer's age: ");
        int age = input.nextInt();

        System.out.print("Enter meal price: $");
        double mealPrice = input.nextDouble();

        System.out.print("Enter number of drinks: ");
        int numberOfDrinks = input.nextInt();

        System.out.print("Are you a student? (true/false): ");
        boolean isStudent = input.nextBoolean();

        System.out.print("Enter service rating (1-5): ");
        int serviceRating = input.nextInt();

        if (serviceRating < 1 || serviceRating > 5) {
            System.out.println();
            System.out.println("ERROR: Service rating must be between 1 and 5.");
            input.close();
            return;
        }

        double drinksCost = numberOfDrinks * DRINK_PRICE;
        double subtotal = mealPrice + drinksCost;

        double discountRate;
        String discountType;

        if (isStudent && age >= 60) {
            discountRate = STUDENT_DISCOUNT_RATE;
            discountType = "Student discount (10%)";
        } else if (isStudent) {
            discountRate = STUDENT_DISCOUNT_RATE;
            discountType = "Student discount (10%)";
        } else if (age >= 60) {
            discountRate = SENIOR_DISCOUNT_RATE;
            discountType = "Senior discount (5%)";
        } else {
            discountRate = 0.0;
            discountType = "No discount";
        }

        double discountAmount = subtotal * discountRate;
        double discountedTotal = subtotal - discountAmount;

        double tipAmount;
        if (serviceRating == 4 || serviceRating == 5) {
            tipAmount = discountedTotal * TIP_RATE;
        } else {
            tipAmount = 0.0;
        }

        double finalBill = discountedTotal + tipAmount;
        int wholeAmount = (int) finalBill;

        System.out.println();
        System.out.println("==================================");
        System.out.println("          SMART RESTAURANT");
        System.out.println("==================================");
        System.out.println("Customer: " + customerName);
        System.out.println("Age: " + age);
        System.out.printf("Meal cost:       $%.2f%n", mealPrice);
        System.out.printf("Drinks cost:     $%.2f%n", drinksCost);
        System.out.printf("Subtotal:        $%.2f%n", subtotal);
        System.out.println("Discount type:   " + discountType);
        System.out.printf("Discount amount: $%.2f%n", discountAmount);
        System.out.printf("Tip amount:      $%.2f%n", tipAmount);
        System.out.printf("Final bill:      $%.2f%n", finalBill);
        System.out.println("Whole amount:    $" + wholeAmount);
        System.out.println("==================================");
        System.out.println("       Thank you for ordering!");
        System.out.println("==================================");

        input.close();
    }
}
