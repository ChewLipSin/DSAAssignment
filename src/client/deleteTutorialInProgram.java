package client;

import adt.ArrList;
import adt.ListInterface;
import boundary.TutorialProgramUI;
import control.TutorialProgramMaintenance;
import dao.TutorialPrDAO;
import entity.TutorialProgram;
import java.io.IOException;
import java.util.Scanner;
import utility.ConsoleColor;
import static utility.MessageUI.printFormattedText;

/**
 * @author Lim Yi Leong
 */
public class deleteTutorialInProgram {

    private ListInterface<TutorialProgram> tpList = new ArrList<>();
    private static final TutorialProgramMaintenance tpM = new TutorialProgramMaintenance();
    private static final TutorialProgramUI tpU = new TutorialProgramUI();
    private static final TutorialPrDAO tpDAO = new TutorialPrDAO();
    private Scanner scanner;

    public deleteTutorialInProgram(ListInterface<TutorialProgram> tutorialGroupList) {
        this.tpList = tutorialGroupList;
        scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            printFormattedText("\nEnter the number of the tutorial group to delete from programme (or 'q' to quit): ", ConsoleColor.BRIGHTBLUE);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                quit = true;
            } else {
                try {
                    int tpNumber = Integer.parseInt(input);
                    if (tpNumber >= 1 && tpNumber <= tpList.size()) {
                        deleteTP(tpNumber - 1);
                        quit = true;
                    } else {
                        printFormattedText("\nInvalid tutorial program number.", ConsoleColor.RED);
                    }
                } catch (NumberFormatException e) {
                    printFormattedText("\nInvalid input. Please enter a number or 'q' to quit.", ConsoleColor.RED);
                }
            }
        }
    }

    public void deleteTP(int index) {
        boolean quit = false;
        while (!quit) {
            if (index >= 0 && index < tpList.size()) {
                TutorialProgram tutorialProgram = tpList.getEntry(index);

                // Ask for user confirmation
                printFormattedText("\nAre you sure you want to delete the tutorial group? (y/n): ", ConsoleColor.BRIGHTBLUE);
                String confirmation = scanner.nextLine().toLowerCase();

                if (confirmation.toLowerCase().equals("y")) {
                    // Delete the program
                    tpList.remove(index);
                    try {
                        tpDAO.saveToFile(tpList);
                        printFormattedText("\nProgram deleted successfully.", ConsoleColor.GREEN);

                        // Ask user if they want to print the updated list
                        printFormattedText("\nDo you want to print the updated program list? (y/n): ", ConsoleColor.BRIGHTBLUE);
                        String choice = scanner.nextLine().toLowerCase();
                        if (choice.equals("y")) {
                            String outputStr = tpM.getAllTutorialPrograms(tpList);
                            tpU.listAllTutorialPrograms(outputStr);
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
