package client;

import adt.ArrList;
import adt.ListInterface;
import boundary.ProgramUI;
import control.ProgramMaintenance;
import dao.ProgramDAO;
import entity.Program;
import utility.MessageUI;
import java.io.IOException;
import java.util.Scanner;
import utility.ConsoleColor;
import static utility.MessageUI.printFormattedText;

/**
 * @author Lim Yi Leong
 */
public class deleteProgram {

    private ListInterface<Program> pList = new ArrList<>();
    ;
    private static ProgramDAO pDAO = new ProgramDAO();
    private static ProgramUI pU = new ProgramUI();
    private ProgramMaintenance pM = new ProgramMaintenance();
    private Scanner scanner;

    public deleteProgram(ListInterface<Program> pList) {
        this.pList = pList;
        scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            printFormattedText("Enter the number of the program to delete (or 'q' to quit): ", ConsoleColor.BRIGHTBLUE);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                quit = true;
            } else {
                try {
                    int programNumber = Integer.parseInt(input);
                    if (programNumber >= 1 && programNumber <= pList.size()) {
                        deleteP(programNumber - 1);
                        quit = true;
                    } else {
                        printFormattedText("\nInvalid program number.", ConsoleColor.RED);
                    }
                } catch (NumberFormatException e) {
                    printFormattedText("\nInvalid input. Please enter a number or 'q' to quit.", ConsoleColor.RED);
                }
            }
        }

    }

    public void deleteP(int index) {
        boolean quit = false;
        while (!quit) {

            if (index >= 0 && index < pList.size()) {
                Program program = pList.getEntry(index);

                printFormattedText("\nAre you sure you want to delete the program? (y/n): ", ConsoleColor.BRIGHTBLUE);
                String confirmation = scanner.nextLine().toLowerCase();

                if (confirmation.toLowerCase().equals("y")) {
                    // Delete the program
                    pList.remove(index);
                    try {
                        pDAO.saveToFile(pList);
                        printFormattedText("Program deleted successfully.", ConsoleColor.GREEN);

                        // Ask user if they want to print the updated list
                        System.out.print("Do you want to print the updated program list? (y/n): ");
                        String choice = scanner.nextLine().toLowerCase();
                        if (choice.equals("y")) {
                            String outputStr = pM.getAllPrograms(pList);
                            pU.listAllPrograms(outputStr);
                        }
                        quit = true;
                    } catch (IOException ex) {
                        printFormattedText("\nAn error occurred while saving the program list.", ConsoleColor.RED);
                    }
                } else {
                    printFormattedText("\nProgram not deleted.", ConsoleColor.RED);
                }
            } else {
                printFormattedText("\nInvalid program index.", ConsoleColor.RED);
            }
        }
    }

}
