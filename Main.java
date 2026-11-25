
package studentresults;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("STUDENT RESULTS MANAGEMENT SYSTEM");
        System.out.println("=================================");

        // Enter student information
        System.out.print("Enter Registration Number: ");
        String reg = input.nextLine();

        System.out.print("Enter Student Name: ");
        String name = input.nextLine();

        System.out.print("Enter Student Age: ");
        int age = Integer.parseInt(input.nextLine());

        System.out.print("Enter Module 1 Mark: ");
        double mark1 = Double.parseDouble(input.nextLine());

        System.out.print("Enter Module 2 Mark: ");
        double mark2 = Double.parseDouble(input.nextLine());

        System.out.print("Enter Module 3 Mark: ");
        double mark3 = Double.parseDouble(input.nextLine());

        // Validate marks
        if (age <= 0 || mark1 < 0 || mark1 > 100
                || mark2 < 0 || mark2 > 100
                || mark3 < 0 || mark3 > 100) {

            System.out.println("Invalid age or marks!");
        } else {

            // Create Student object
            Student student = new Student(
                    reg, name, age,
                    mark1, mark2, mark3
            );

            // Display results
            student.displayInformation();
        }

        input.close();
    }
}
