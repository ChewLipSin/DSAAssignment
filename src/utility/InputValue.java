package utility;

import adt.ArrList;
import adt.ListInterface;
import control.ProgramMaintenance;
import entity.Program;
import java.util.Scanner;
/**
 *
 * @author Lim Yi Leong
 */
public class InputValue {

    Scanner sc = new Scanner(System.in);
    private ListInterface<Program> pList = new ArrList<>();
    private ProgramMaintenance pM = new ProgramMaintenance();

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
    return code.matches("[A-Za-z]{3}"); // Check if the code consists only of letters and has a length of 3
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