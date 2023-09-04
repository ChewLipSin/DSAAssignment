package boundary;

import adt.ListInterface;
import entity.Program;
import entity.Tutorial;
import entity.TutorialProgram;
import utility.ProgramValidator;
import utility.TutorialValidator;
import utility.ConsoleColor;
import utility.InputValue;
import static utility.MessageUI.printFormattedText;
/**
 *
 * @author Lim Yi Leong
 */
public class TutorialProgramUI {
    private final ProgramValidator pV = new ProgramValidator();
    private final TutorialValidator tV = new TutorialValidator();
    private final InputValue iv = new InputValue();

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

    public String inputTutorialProgramCode(ListInterface<Program> pList) {
        printFormattedText("\nA kindly reminder you can input 'EXI' to exit ^_^\n", ConsoleColor.CYAN);
        String code;
        do {
            System.out.println("\nThe programme code exists in programme :");
            pV.getProgramCode();
            
            System.out.print("\nEnter tutorial programme code : ");
            code = iv.readString().toUpperCase();
            if (code.equalsIgnoreCase("EXI")) {
                    return null;
            }
            if (!pV.isProgramCodeValid(code)) {
                printFormattedText("\nInvalid program code. Please try again.", ConsoleColor.RED);
            }
        } while (!pV.isProgramCodeValid(code));
        return code;
    }

    public String inputTutorialProgramName(String code) {
        System.out.println();
        return pV.getProgramName(code.toUpperCase());
    }

    public String inputTutorialProgramGroup(String code, ListInterface<Tutorial> tList) {
        printFormattedText("\n\nA kindly reminder you can input 'EXI' to exit ^_^\n", ConsoleColor.CYAN);
        String group;
        boolean stop = false;
        do {
            System.out.print("\nThe tutorial group exists in " + code + " :");
            tV.getTutorialGroupName(code);

            System.out.print("\n\nEnter tutorial program group name (ie.RSDG1) : ");
            group = iv.readString().toUpperCase();
            if (group.equalsIgnoreCase("EXI")) {
                return null;
            }
            // Check if the first three characters of 'group' match 'code'
            if (!group.startsWith(code.substring(0, 3))) {
                printFormattedText("\nThe first three characters of the group must match the program code.\n", ConsoleColor.RED);
            } else 
                if (!iv.isValidTutorialGroupName(group)){
                printFormattedText("\nPlease input follow the format example -> RSDG1, RSDG2...\n", ConsoleColor.RED);
            }else 
                if (tV.isTutorialGroupValid(group)) {
                printFormattedText("\nTutorial group already exists. Please try again.\n", ConsoleColor.RED);
            } else {
                stop = true;
            }
        } while (!stop);
        return group;
    }

    public int inputTutorialProgramNumStudents() {
        printFormattedText("\nA kindly reminder you can input '0' to exit ^_^\n", ConsoleColor.CYAN);
        printFormattedText("\n\nA group of Students -> Min:10 Max:30", ConsoleColor.CYAN);
        while (true) {
            System.out.print("\nEnter number of students (10-30): ");
            int input = iv.readInteger();
            if (input == 0){
                return 0;
            }
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
        printFormattedText("\nA kindly reminder you can input 'EXI' to exit ^_^\n", ConsoleColor.CYAN);
        System.out.print("\n\nEnter student name of group representative: ");
        String classRep = iv.readString().toUpperCase();
        if (classRep.equalsIgnoreCase("EXI")) {
                return null;
        }
        return classRep;
    }

    public int inputTutorialProgramIntakeYear() {
        printFormattedText("\nA kindly reminder you can input '0' to exit ^_^\n", ConsoleColor.CYAN);
        while (true) {
            System.out.print("\n\nEnter intake year (2022-2023): ");
            int input = iv.readInteger();
            if (input == 0) {
                return 0;
            }
            int year = input;
            if (year >= 2022 && year <= 2023) {
                return year;
            } else {
                printFormattedText("\nInvalid input! Please enter a year between 2022 and 2023.", ConsoleColor.RED);
            }
        }
    }

    public int inputTutorialProgramIntakeMonth() {
        printFormattedText("\nA kindly reminder you can input '0' to exit ^_^\n", ConsoleColor.CYAN);
        printFormattedText("\n\nIntake Available -> January = 1; June = 6; September = 9", ConsoleColor.CYAN);
        while (true) {
            System.out.print("\nEnter intake month: ");
            int input = iv.readInteger();
            if (input == 0) {
                return 0;
            }
            int digit = input;
            if (digit == 1 || digit == 6 || digit == 9 || digit >= 1 && digit <= 12) {
                return digit;
            } else {
                printFormattedText("\nInvalid input! Please enter 1(January), 6(June), or 9(September).", ConsoleColor.RED);
            }
        }
    }

    public TutorialProgram inputTutorialProgramDetails(ListInterface<Program> pList, ListInterface<Tutorial> tList) {
        String code = inputTutorialProgramCode(pList);
        if (code==null) {
            return null;
        }
        String name = inputTutorialProgramName(code);
        if (name==null) {
            return null;
        }
        String group = inputTutorialProgramGroup(code,tList);
        if (group==null) {
            return null;
        }
        int numStudents = inputTutorialProgramNumStudents();
        if (numStudents==0) {
            return null;
        }
        String classRap = inputTutorialProgramClassRep();
        if (classRap==null) {
            return null;
        }
        int intakeYear = inputTutorialProgramIntakeYear();
        if (intakeYear==0) {
            return null;
        }
        int intakeMonth = inputTutorialProgramIntakeMonth();
        if (intakeMonth==0) {
            return null;
        }
        if (!code.isEmpty() && !name.isEmpty() && !group.isEmpty() && !classRap.isEmpty()) {
            return new TutorialProgram(code, name, group, numStudents, classRap, intakeYear, intakeMonth);
        } else {
            printFormattedText("\nNot allow submit empty content !!!", ConsoleColor.RED);
            return null;
        }
    }
}
