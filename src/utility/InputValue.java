
package utility;

import java.util.Scanner;

/**
 *
 * @author Chew Lip Sin
 */
public class InputValue {

    Scanner sc = new Scanner(System.in);
    private final static Scanner in = new Scanner(System.in);

    public int readInteger() {
        int number = 0;
        boolean isInteger = false;
        do {
            isInteger = false;
            try {
                number = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                MessageUI.printFormattedText("Invalid format, please enter in integer: ", ConsoleColor.RED);
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

    public String readCourseCode() {
        String input;
        boolean match = false;
        do {
            input = sc.nextLine();

            match = input.matches("^[ABFMabfm][A-Za-z]{3}\\d{4}$|^[Mm][Pp][Uu][-]\\d{4}$");
            if ("0".equals(input)) {
                match = true;
                return "0";
            }
            if (!match) {
                MessageUI.displayInvalidFormat();
            }
        } while (!match);
        return input;
    }

    public static int inputInt(String prompt) {
        boolean success = false;
        int result = 0;
        do {
            try {
                System.out.print(prompt);
                result = in.nextInt();
                in.nextLine();
                success = true;
            } catch (Exception ex) {
                in.nextLine();
                System.out.println("** Input is not a number, try again. **");
            }
        } while (!success);
        return result;
    }

    public static int inputChoice(String prompt, int min, int max) {
        int input;
        while (true) {
            try {
                System.out.print(prompt);
                String inputStr = in.nextLine().trim();

                if (inputStr.isEmpty()) {
                    return 8;
                }

                input = Integer.parseInt(inputStr);

                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return input;
    }

    public static int intChoice(String prompt, int min, int max) {
        int input;
        while (true) {
            try {
                System.out.print(prompt);
                String inputStr = in.nextLine().trim();

                if (inputStr.isEmpty()) {
                    System.out.println("Input cannot be empty. Please enter a number between " + min + " and " + max + ".");
                    continue;
                }

                input = Integer.parseInt(inputStr);

                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException ex) {
                System.out.println("** Input is not a number, try again. **");
            }
        }
        return input;
    }

    public static String inputString(String prompt) {
        System.out.print(prompt);
        String input = in.nextLine();
        return input;
    }

    public static String enterString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = in.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("** Input cannot be blank. Please try again. **");
            }
        } while (input.trim().isEmpty());
        return input;
    }

    public static char inputChar(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = in.nextLine().trim();
            if (input.trim().isEmpty()) {
                System.out.println("** Input cannot be blank. Please try again. **");
            }
        } while (input.trim().isEmpty());
        return input.charAt(0);
    }

    public static char inputYN() {
        char inputYN = 0;
        boolean success = false;

        do {
            String input = in.nextLine();

            if (input.length() == 1 && (input.charAt(0) == 'y' || input.charAt(0) == 'n')) {
                inputYN = input.charAt(0);
                success = true;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        } while (!success);

        return inputYN;
    }

    public static void closeInput() {
        in.close();
    }

    public boolean isValidCode(String code) {
        return code.matches("[A-Za-z]{3}");
    }

    public boolean isValidTutorialGroupName(String groupName) {
        return groupName.matches("[A-Za-z]{3}G([1-9]|1\\d|2[0-9]|30)"); // 2[0-9] matches any two-digit number starting with 2 (20 to 29).
        //followed by a number from 1 to 30 
    }
//    public String readAlphaInt() {
//        String input;
//        boolean matchs;
//        String regex = "\\dA-Za-z\\s-]+";
//        do {
//        input = sc.nextLine();
//        matchs = input.matches(regex);
//
//        if ("0".equals(input)) {
//            matchs = true;
//            return input;
//        }
//
//            if (!matchs) {
//                MessageUI.displayInvalidFormat();
//            }
//        } while (!matchs);
//        return input;
//    }
}
