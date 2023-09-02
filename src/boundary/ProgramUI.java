package boundary;

import adt.ArrList;
import adt.ListInterface;
import control.ProgramMaintenance;
import entity.Program;
import java.util.InputMismatchException;
import utility.ConsoleColor;
import utility.InputValue;
import static utility.MessageUI.printFormattedText;
/**
 *
 * @author Lim Yi Leong
 */
public class ProgramUI {

    private ListInterface<Program> pList = new ArrList<>();
    private ProgramMaintenance pM = new ProgramMaintenance();
    private InputValue iv = new InputValue();
    private static final String[] levels = {"Foundation", "Diploma", "Bachelor Degree", "Master", "Doctor of Philosophy"};
    private static final String[] faculties = {
        "FOCS - Faculty of Computing and Information Technology",
        "FSSH - Faculty of Social Science and Humanities",
        "FOAS - Faculty of Applied Sciences",
        "FOET - Faculty of Engineering and Technology",
        "FAFB - Faculty of Accountancy, Finance & Business",
        "FOBE - Faculty of Build Environment",
        "FCCI - Faculty of Communication & Creative Industries"
    };

    public int getMenuChoice() {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("\n\n");
                System.out.print("+-----------------------------+\n|");
                printFormattedText("  PROGRAMME MANAGEMENT MENU  ", ConsoleColor.BRIGHTRED);
                System.out.print("|\n+-----------------------------+\n|");
                printFormattedText(" 1. Add new program          ", ConsoleColor.YELLOW);
                System.out.print("|\n|");
                printFormattedText(" 2. Find program details     ", ConsoleColor.GREEN);
                System.out.print("|\n|");
                printFormattedText(" 3. Modify program details   ", ConsoleColor.BRIGHTGREEN);
                System.out.print("|\n|");
                printFormattedText(" 4. Delete program           ", ConsoleColor.BRIGHTBLUE);
                System.out.print("|\n|");
                printFormattedText(" 5. Programme course service ", ConsoleColor.CYAN);
                System.out.print("|\n|");
                printFormattedText(" 6. Programme report         ", ConsoleColor.MAGENTA);
                System.out.print("|\n|");
                printFormattedText(" 0. Back to main menu        ", ConsoleColor.BRIGHTMAGENTA);
                System.out.print("|\n");
                System.out.print("+-----------------------------+\n");
                System.out.print("Enter choice: ");

                choice = iv.readInteger();
                validInput = true;
            } catch (InputMismatchException e) {
                printFormattedText("\nInvalid input. Please enter a valid integer choice.", ConsoleColor.RED);
            }
        }
        System.out.println();
        return choice;
    }

    public void listAllPrograms(String outputStr) {
        System.out.println(outputStr);
    }

    public String inputProgramCode(ListInterface<Program> pList) {
        printFormattedText("\nA kindly reminder you can input 'EXI' to exit ^_^\n", ConsoleColor.CYAN);
        while (true) {
            System.out.print("\n\nEnter program code (3 Character; ie,'RDS'  ): ");
            String code = iv.readString().toUpperCase();
            if (code.length() == 3) {
                if (code.equals("EXI")) {
                    return null;
                }
                if (iv.isValidCode(code)){
                if (isCodeUnique(code, pList)) {
                    return code;
                } else {
                    printFormattedText("\nProgram code already exists. Please enter a unique program code.", ConsoleColor.RED);
                }
                } else {
                printFormattedText("\nInvalid code format. Please enter a 3-character program code.", ConsoleColor.RED);
            }
            }else
                printFormattedText("\nInvalid code length. Please enter a 3-character program code.", ConsoleColor.RED);
        }
    }

    public String inputProgramLevel() {
        printFormattedText("\nA kindly reminder you can input '0' to exit ^_^\n", ConsoleColor.CYAN);
        while (true) {
            System.out.print("\n\nEnter number of program level (1-5):\n");
            displayOptions(levels);
            int levelChoice = iv.readInteger();
            if (levelChoice == 0) {
                return null;
            }
            if (levelChoice >= 1 && levelChoice <= 5) {
                return getLevelFromChoice(levelChoice);
            } else {
                printFormattedText("\nInvalid input. Please enter a value between 1 and 5.", ConsoleColor.RED);
            }
        }
    }

    public String inputProgramName() {
        printFormattedText("\nA kindly reminder you can input 'Exi' to exit ^_^\n", ConsoleColor.CYAN);
        System.out.print("\n\nEnter program name: ");
        String name = iv.readString().toUpperCase();
        if (name.equals("EXI")) {
            return null;
        }
        return name;
    }

    public String inputProgramFaculty() {
        printFormattedText("\nA kindly reminder you can input '0' to exit ^_^\n", ConsoleColor.CYAN);
        while (true) {
            System.out.println("\nSelect the faculty of the program (1-7):\n");
            displayOptions(faculties);
            int facultyChoice = iv.readInteger();
            if (facultyChoice == 0) {
                return null;
            }
            if (facultyChoice >= 1 && facultyChoice <= 7) {
                return getFacultyFromChoice(facultyChoice);
            } else {
                printFormattedText("\nInvalid input. Please enter a value between 1 and 7.", ConsoleColor.RED);
            }
        }
    }

    public String inputProgramDescription() {
        printFormattedText("\nA kindly reminder you can input 'Exi' to exit ^_^\n", ConsoleColor.CYAN);
        System.out.print("\n\nEnter program description: ");
        String descp = iv.readString().toUpperCase();
        if (!descp.equals("EXI")) {
            return descp;
        }
        return null;
    }

    public Program inputProgramDetails(ListInterface<Program> pList) {
        String code = inputProgramCode(pList);
        if (code==null) {
            return null;
        }
        String level = inputProgramLevel();
        if (level==null){
            return null;
        }
        String name = inputProgramName();
        if (name==null) {
            return null;
        }
        String faculty = inputProgramFaculty();
        if (faculty==null){
            return null;
        }
        String description = inputProgramDescription();
        if (description==null) {
            return null;
        }
        System.out.println();
        if (!code.isEmpty() && !name.isEmpty() && !description.isEmpty() && isCodeUnique(code, pList) && isNameUnique(name, pList)) {
            return new Program(code, level, name, faculty, description);
        } else {
            return null;
        }
    }

    private String getLevelFromChoice(int choice) {
        return levels[choice - 1];
    }

    private String getFacultyFromChoice(int choice) {
        return faculties[choice - 1];
    }

    public static String[] getLevels() {
        return levels;
    }

    public static String[] getFaculties() {
        return faculties;
    }

    public void displayOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Choose an option : ");
    }

    private boolean isCodeUnique(String code, ListInterface<Program> pList) {
        for (int i = 1; i <= pList.size(); i++) {
            Program program = pList.getEntry(i);
            if (program.getCode().toUpperCase().equals(code.toUpperCase())) {
                return false;
            }
        }
        return true; // Code is unique
    }

    private boolean isNameUnique(String name, ListInterface<Program> pList) {
        for (int i = 1; i < pList.size(); i++) {
            Program program = pList.getEntry(i);
            if (program.getName().toUpperCase().equals(name.toUpperCase())) {
                return false;
            }
        }
        return true; // Name is unique
    }

}
