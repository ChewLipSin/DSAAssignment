package control;

import adt.*;
import boundary.ProgramUI;
import client.deleteProgram;
import client.modifyProgram;
import client.reportProgram;
import client.searchProgram;
import dao.ProgramDAO;
import dao.ProgramInitializer;
import entity.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import utility.ConsoleColor;
import utility.MessageUI;
import static utility.MessageUI.printFormattedText;

/**
 * @author Lim Yi Leong
 */
public class ProgramMaintenance {

    private ListInterface<Program> pList = new ArrList<>();
    private static ProgramDAO pDAO = new ProgramDAO();
    private static ProgramUI pU = new ProgramUI();
    private static TutorialProgramMaintenance tpm = new TutorialProgramMaintenance();
    private Scanner scanner = new Scanner(System.in);

    public ProgramMaintenance() {
        this.pList = pDAO.retrieveFromFile();
    }

    public void runProgramMaintenance() {
        ProgramInitializer pi = new ProgramInitializer();

//        pList = pDAO.retrieveFromFile();
        pList = pi.ProgramInitializer();
        System.out.println(pList);
        int choice = 0;
        do {
            choice = pU.getMenuChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    System.out.print("\n\n");
                    System.out.print("-----------------------> Adding Programme\n"
                            + "----------------------------------------->\n\n");
                    addNewProgram();
                    break;
                case 2:
                    int choiceS = 0;
                    do {
                        System.out.print("\n\n");
                        System.out.print("--------------------------> Searching Programme\n"
                                + "       1. List All Programme\n"
                                + "       2. Search Program\n"
                                + "       3. Back to Programme Main Page\n");
                        System.out.print("\nEnter the number to continue(1,2,3): ");
                        choiceS = scanner.nextInt();
                        switch (choiceS) {
                            case 1:
                                pU.listAllPrograms(getAllPrograms(pList));
                                break;
                            case 2:
                                new searchProgram(pList);
                                break;
                            case 3:
                                runProgramMaintenance();
                                break;
                            default:
                                MessageUI.displayInvalidChoiceMessage();
                                break;
                        }
                    } while (choiceS != 3);
                    break;

                case 3:
                    System.out.print("\n\n");
                    System.out.print("--------------------> Modifying Programme\n"
                            + "----------------------------------------->\n\n");
                    pU.listAllPrograms(getAllPrograms(pList));
                    new modifyProgram(pList);
                    break;
                case 4:
                    System.out.print("\n\n");
                    System.out.print("--------------------> Deleting Programme\n"
                            + "----------------------------------------->\n\n");
                    pU.listAllPrograms(getAllPrograms(pList));
                    new deleteProgram(pList);
                    break;
                case 5:
                    tpm.runTutorialProgramMaintenance();
                    break;
                case 6:
                    new reportProgram();
                    break;
                case 7:
                    //main
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public void addNewProgram() {
        Program newProgram = pU.inputProgramDetails();
        if (newProgram != null) {
            pList.add(newProgram);
            try {
                pDAO.saveToFile(pList);
                pU.listAllPrograms(getAllPrograms(pList));
            } catch (IOException ex) {
                //System.out.print("Failed to save");
            }
        } else {
            printFormattedText("\nExit Successfully", ConsoleColor.BRIGHTMAGENTA);
        }
    }

    public String getAllPrograms(ListInterface<Program> pList) {
        String outputStr = "";

        outputStr += "List of Programme:\n";
        outputStr += String.format("%-5s | %-15s | %-30s | %-80s | %-60s | %s\n",
                "No.", "Program Code", "Program Level", "Program Name", "Program Faculty", "Program Description");

        Iterator<Program> iterator = pList.getIterator();
        int programNumber = 1;

        while (iterator.hasNext()) {
            Program program = iterator.next();
            outputStr += String.format("%-5d | %-15s | %-30s | %-80s | %-60s | %s\n",
                    programNumber, program.getCode(), program.getLevel(), program.getName(),
                    program.getFaculty(), program.getDescription());
            programNumber++;
        }

        return outputStr;
    }

    public String getOneProgram(ListInterface<Program> pList, int index) {
        String outputStr = "";
        outputStr += "List of Programs:\n";
        outputStr += String.format("%-5s | %-15s | %-30s | %-80s | %-60s | %s\n",
                "No.", "Program Code", "Program Level", "Program Name", "Program Faculty", "Program Description");

        Iterator<Program> iterator = pList.getIterator();
        int programNumber = 1;
        Program program = null; // Initialize program outside the loop
        boolean found = false;  // Renamed 'stop' to 'found' for clarity

        while (iterator.hasNext()) {
            program = iterator.next();
            if (programNumber == index) {
                found = true; // Mark that the program has been found
                break; // Exit the loop since the program is found
            }
            programNumber++;
        }

        if (found) {
            outputStr += String.format("%-5d | %-15s | %-30s | %-80s | %-60s | %s\n",
                    programNumber, program.getCode(), program.getLevel(), program.getName(),
                    program.getFaculty(), program.getDescription());
        } else {
            outputStr += "Program not found.\n";
        }

        return outputStr;
    }

    public void displayPrograms(ListInterface<Program> pList) {
        String outputStr = getAllPrograms(pList);
        pU.listAllPrograms(outputStr);
    }

    public static void main(String[] args) {
        ProgramMaintenance programMaintenance = new ProgramMaintenance();
        programMaintenance.runProgramMaintenance();
    }
}
