package utility;

import java.util.Scanner;

/**
 *
 * @author Lim Yi Leong
 */
public class InputValue {

    private final static Scanner sc = new Scanner(System.in);

    public int readInteger() {
        int number = 0;
        boolean isInteger = false;
        do {
            isInteger = false;
            try {
                number = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                MessageUI.printFormattedText("Invalid format, please enter in integer: ", ConsoleColor.YELLOW);
                sc.next();
                isInteger = true;
            }

        } while (isInteger);
        return number;
    }

    public String readString() {
        String input;
        do {
            input = sc.nextLine();
            input = input.trim();
            if (input.equals("")) {
                MessageUI.displayInvalidFormat();
            }
        } while (input.equals(""));
        return input;
    }

    public boolean isValidCode(String code) {
        return code.matches("[A-Za-z]{3}");
    }
    
    public boolean isValidTutorialGroupName(String groupName) {
    return groupName.matches("[A-Za-z]{3}G([1-9]|1\\d|2[0-9]|30)"); // 2[0-9] matches any two-digit number starting with 2 (20 to 29).
    //followed by a number from 1 to 30 
    }
}
