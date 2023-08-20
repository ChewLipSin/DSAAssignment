package utility;

/**
 *
 * @author Chew Lip Sin
 */
public class MessageUI {

    public static void displayInvalidChoiceMessage() {
        printFormattedText("\nInvalid choice\n", ConsoleColor.RED);
    }

    public static void displayExitMessage() {
        printFormattedText("\nExiting system...", ConsoleColor.GREEN);

    }

    public static void displayInvalidCreditHourMessage(int creditHour) {
        if (creditHour > 20) {
            printFormattedText("Your input credit hour is more than the range\n", ConsoleColor.YELLOW);
        } else if (creditHour < 0) {
            printFormattedText("Your input credit hour is less than the range\n", ConsoleColor.YELLOW);
        }
    }

    public static void askConfirmationMessage(String val) {
        printFormattedText("Are you sure to " + val + " it?(1 is Yes and 0 is No): ", ConsoleColor.BRIGHTBLUE);
    }

    public static void displaySuccessConfirmationMessage(String val) {
        printFormattedText(val + "ed Successfully!\n", ConsoleColor.GREEN);
    }

    public static void displayInvalidFormat() {
        printFormattedText("Your input is not in correct format: ", ConsoleColor.YELLOW);
    }

    public static void printFormattedText(String text, ConsoleColor color) {
        System.out.print(color + text + ConsoleColor.RESET);
    }
}
