package control;

import java.util.Scanner;
/**
 *
 * @author Lim Yi Leong
 */
public class mainProgramUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Program Management");
        System.out.println("2. Option B");
        System.out.println("3. Option C");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        performAction(choice);
        scanner.close();

    }

    public static void performAction(int choice) {
        switch (choice) {
            case 1:
                System.out.println("You choose Program Management");
                new ProgramMenu();
                break;
            case 2:
                System.out.println("You chose Option B");
                // Add code for Option B action
                break;
            case 3:
                System.out.println("You chose Option C");
                // Add code for Option C action
                break;
            default:
                System.out.println("Invalid choice");
                // Add code for handling invalid choice
                break;
        }
    }
}
