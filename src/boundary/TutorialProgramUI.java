package boundary;

import adt.ListInterface;
import control.TutorialProgramMaintenance;
import entity.Program;
import entity.TutorialProgram;
import utility.ProgramValidator;
import utility.TutorialValidator;
import utility.ConsoleColor;
import utility.InputValue;
import static utility.MessageUI.printFormattedText;

/**
 * @author Lim Yi Leong
 */
public class TutorialProgramUI {

    private ListInterface<TutorialProgram> tpList;
    private ListInterface<Program> pList;
    private TutorialProgramMaintenance tpM;
    private ProgramValidator pV = new ProgramValidator();
    private InputValue iv = new InputValue();

    public int getMenuChoice() {
        System.out.print("\n\n\n");
        System.out.print("  +-------------------------------------------------+\n");
        System.out.print("+-------------------------------------------------+ |\n");
        System.out.print("|                                                 | |\n|");
        printFormattedText("     PROGRAMME TUTORIAL MANAGEMENT MENU          ", ConsoleColor.BRIGHTRED);
        System.out.print("| |\n|                                                 ");
        System.out.print("| |\n|");
        printFormattedText("        1. Add Tutorial to Programme             ", ConsoleColor.MAGENTA);
        System.out.print("| |\n|");
        printFormattedText("        2. Find Tutorial in Programme            ", ConsoleColor.MAGENTA);
        System.out.print("| |\n|");
        printFormattedText("        3. Delete Tutorial in Programme          ", ConsoleColor.MAGENTA);
        System.out.print("| |\n|");
        printFormattedText("        4. Back to Programme Management          ", ConsoleColor.MAGENTA);
        System.out.print("| |\n");
        System.out.print("|                                                 | +\n");
        System.out.print("+-------------------------------------------------+\n");
        System.out.print("Enter choice: ");
        int choice = iv.readInteger();
        System.out.println();
        return choice;
    }

    public void listAllTutorialPrograms(String outputStr) {
        System.out.print("\n\n");
        System.out.println(outputStr);
    }

    public String inputTutorialProgramCode() {
        String code;
        do {
            System.out.print("\nEnter tutorial programme code: ");
            code = iv.readString();
            if (!pV.isProgramCodeValid(code)) {
                printFormattedText("\nInvalid program code. Please try again.", ConsoleColor.RED);
            }
        } while (!pV.isProgramCodeValid(code));
        return code;
    }

    public String inputTutorialProgramName(String code) {
        return pV.getProgramName(code);
    }

    public String inputTutorialProgramGroup() {
        String group;
        do {
            System.out.print("\n\nEnter tutorial program group name: ");
            group = iv.readString();
            if (!TutorialValidator.isTutorialGroupValid(group)) {
                printFormattedText("\nInvalid tutorial group name. Please try again.", ConsoleColor.RED);
            }
        } while (!TutorialValidator.isTutorialGroupValid(group));
        return group;
    }

    public int inputTutorialProgramNumStudents() {
        printFormattedText("\n\nA group of Students -> Min:10 Max:30", ConsoleColor.CYAN);
        while (true) {
            System.out.print("\nEnter number of students (10-30): ");
            int input = iv.readInteger();

            if (input >= 10 && input <= 30) {
                int num = input;
                if (num >= 10 && num <= 30) {
                    return num;
                } else {
                    printFormattedText("\nInvalid input! Please enter a number between 10 and 30.", ConsoleColor.RED);
                }
            } else {
                printFormattedText("\nInvalid input! Please enter a valid two-digit number.", ConsoleColor.RED);
            }
        }
    }

    public String inputTutorialProgramClassRep() {
        System.out.print("\n\nEnter student name of group representative: ");
        String classRep = iv.readString();
        return classRep;
    }

    public int inputTutorialProgramIntakeYear() {
        while (true) {
            System.out.print("\n\nEnter intake year (2022-2023): ");
            int input = iv.readInteger();

            int year = input;
            if (year >= 2022 && year <= 2023) {
                return year;
            } else {
                printFormattedText("\nInvalid input! Please enter a year between 2022 and 2023.", ConsoleColor.RED);
            }
        }
    }

    public int inputTutorialProgramIntakeMonth() {
        printFormattedText("\n\nIntake Available -> January = 1; June = 6; September = 9", ConsoleColor.CYAN);
        while (true) {
            System.out.print("\nEnter intake month: ");
            int input = iv.readInteger();
            int digit = input;
            if (digit == 1 || digit == 6 || digit == 9 || digit >= 1 && digit <= 12) {
                return digit;
            } else {
                printFormattedText("\nInvalid input! Please enter 1(January), 6(June), or 9(September).", ConsoleColor.RED);
            }
        }
    }

    public TutorialProgram inputTutorialProgramDetails() {
        String code = inputTutorialProgramCode();
        String name = inputTutorialProgramName(code);
        String group = inputTutorialProgramGroup();
        int numStudents = inputTutorialProgramNumStudents();
        String classRap = inputTutorialProgramClassRep();
        int intakeYear = inputTutorialProgramIntakeYear();
        int intakeMonth = inputTutorialProgramIntakeMonth();
        if (!code.isEmpty() && !name.isEmpty() && !group.isEmpty() && !classRap.isEmpty()) {
            return new TutorialProgram(code, name, group, numStudents, classRap, intakeYear, intakeMonth);
        } else {
            printFormattedText("\nNot allow submit empty content !!!", ConsoleColor.RED);
            return null;
        }
    }
}
