package utility;

/**
 *
 * @author Chew Lip Sin
 */
public class MessageUI {

    public static void displayInvalidChoiceMessage() {
        System.out.println("\nInvalid choice");
    }

    public static void displayExitMessage() {
        System.out.println("\nExiting system");
    }

    public static void displayInvalidCreditHourMessage(int creditHour) {
        if (creditHour > 20) {
            System.out.println("Your input credit hour is more than the range");
        } else if (creditHour < 0) {
            System.out.println("Your input credit hour is less than the range");
        }
    }

    public static void displayInvalidFormat() {
        System.out.print("Your input is not in correct format: ");
    }
}
