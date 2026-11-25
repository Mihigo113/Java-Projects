
package studentresults;

public class Student {

    // Student attributes
    private String registrationNumber;
    private String name;
    private int age;

    // Three module marks
    private double mark1;
    private double mark2;
    private double mark3;

    // Constructor
    public Student(String registrationNumber, String name,
                   int age, double mark1,
                   double mark2, double mark3) {

        this.registrationNumber = registrationNumber;
        this.name = name;
        this.age = age;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }

    // Getters
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Calculate average
    public double calculateAverage() {
        return (mark1 + mark2 + mark3) / 3.0;
    }

    // Check academic status
    public String getStatus() {
        if (calculateAverage() >= 50) {
            return "PASS";
        } else {
            return "FAIL";
        }
    }

    // Display student information
    public void displayInformation() {

        System.out.println("\n===== STUDENT RESULTS =====");

        System.out.println("Registration Number: "
                + registrationNumber);

        System.out.println("Student Name: " + name);

        System.out.println("Age: " + age);

        System.out.println("Module 1: " + mark1);

        System.out.println("Module 2: " + mark2);

        System.out.println("Module 3: " + mark3);

        System.out.printf("Average: %.2f%n", calculateAverage());

        System.out.println("Academic Status: " + getStatus());

        System.out.println("---------------------------");
    }
}
