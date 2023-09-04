package client;

import adt.ArrList;
import adt.ListInterface;
import boundary.ProgramUI;
import control.ProgramMaintenance;
import dao.tDAO;
import entity.Program;
import java.io.IOException;
import java.util.Scanner;
import utility.ConsoleColor;
import static utility.MessageUI.printFormattedText;
/**
 *
 * @author Lim Yi Leong
 */
public class modifyProgram {

    private ListInterface<Program> pList = new ArrList<Program>();
    private final tDAO DAO = new tDAO();
    private final ProgramUI pU = new ProgramUI();
    private final ProgramMaintenance pM = new ProgramMaintenance();
    private final Scanner scanner;

    public modifyProgram(ListInterface<Program> pList) {
        this.pList = pList;
        scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            printFormattedText("\nEnter the number of the program to modify (or 'q' to quit): ", ConsoleColor.BRIGHTBLUE);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                quit = true;
            }
                try {
                    int programNumber = Integer.parseInt(input);
                    if (programNumber >= 1 && programNumber <= pList.size()) {
                        modifyP(programNumber);
                        quit = true;
                    } else {
                        printFormattedText("\nInvalid program number.", ConsoleColor.RED);
                    }
                } catch (NumberFormatException e) {
                    printFormattedText("\nInvalid input. Please enter a number or 'q' to quit.", ConsoleColor.RED);
                }
        }
    }

    public void modifyP(int index) {
        if (index >= 0 && index <= pList.size()) {
            Program program = pList.getEntry(index);

            printFormattedText("\nYou are Modifying Program: " + program.getCode(), ConsoleColor.CYAN);
            boolean quit = false;
            String newCode = program.getCode();
            String input = null;
            while (!quit) {
                printFormattedText("\nReminder : Are you sure you want to modify the program code? (Yes = 'y' or No = 'n'): ", ConsoleColor.BRIGHTBLUE);
                input = scanner.nextLine().toLowerCase();

                if (input.equals("n") || input.equals("no")) {
                    quit = true;
                } else if (input.equals("y") || input.equals("yes")) {
                    newCode = pU.inputProgramCode(pList);
                    quit = true;
                } else {
                    printFormattedText("Invalid input. Please enter 'y' or 'n'.", ConsoleColor.RED);
                }
            }

            printFormattedText("\nYou are Modifying Program: " + newCode, ConsoleColor.CYAN);
            String newName = pU.inputProgramName();
            printFormattedText("\nYou are Modifying Program: " + newCode, ConsoleColor.CYAN);
            String newLevel = pU.inputProgramLevel();
            printFormattedText("\nYou are Modifying Program: " + newCode, ConsoleColor.CYAN);
            String newFaculty = pU.inputProgramFaculty();
            printFormattedText("\nYou are Modifying Program: " + newCode, ConsoleColor.CYAN);
            String newDescription = pU.inputProgramDescription();

            program.setCode(newCode);
            program.setName(newName);
            program.setLevel(newLevel);
            program.setFaculty(newFaculty);
            program.setDescription(newDescription);
            pList.replace(index, program);
            try {
                DAO.saveToFile(pList,"program.dat");
                printFormattedText("\nProgram modified successfully.", ConsoleColor.GREEN);

                System.out.println("\nUpdated Program Details:");
                String outputStr = pM.getOneProgram(pList, index);
                pU.listAllPrograms(outputStr);
            } catch (IOException ex) {
                printFormattedText("\nAn error occurred while saving the program list.", ConsoleColor.RED);
            }
        } else {
            printFormattedText("\nInvalid program index.", ConsoleColor.RED);
        }
    }
}
