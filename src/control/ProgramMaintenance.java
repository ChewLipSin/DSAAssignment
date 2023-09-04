package control;

import adt.*;
import boundary.ProgramUI;
import client.deleteProgram;
import client.modifyProgram;
import client.reportProgram;
import client.searchProgram;
import dao.DAO;
import dao.Initializer;
import entity.*;
import java.util.Iterator;
import utility.ConsoleColor;
import utility.InputValue;
import utility.MessageUI;
import static utility.MessageUI.printFormattedText;
import entity.programCodeComparator;
import utility.Command;

/**
 *
 * @author Lim Yi Leong
 */
public class ProgramMaintenance {

    private ListInterface<Program> pList = new ArrList<Program>();
    private ListInterface<TutorialProgram> tpList = new ArrList<TutorialProgram>();
    private final DAO DAO = new DAO();
    private final ProgramUI pU = new ProgramUI();
    private final programCodeComparator pCompare = new programCodeComparator();
    private TutorialProgramMaintenance tpm;
    private final static InputValue iv = new InputValue();

    public void runProgramMaintenance() {
        Initializer.inputProgram();
        Initializer.inputTutorial();
        Initializer.inputTutorialProgram();
        pList = DAO.retrieveFromFile("program.dat");
        tpList = DAO.retrieveFromFile("tutorialProgram.dat");
        int choice;
        Command.cls();
        do {
            choice = pU.getMenuChoice();
            switch (choice) {
                case 1:
                    Command.cls();
                    addProgram(pList);
                    break;
                case 2:
                    Command.cls();
                    searchProg(pList);
                    break;
                case 3:
                    Command.cls();
                    modifyProgram(pList);
                    break;
                case 4:
                    Command.cls();
                    deleteProgram(pList);
                    break;
                case 5:
                    Command.cls();
                    goTP();
                    break;
                case 6:
                    Command.cls();
                    printReport();
                    break;
                case 7:
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 7);
    }

    public void addNewProgram(ListInterface<Program> pList) {
        Program newProgram = pU.inputProgramDetails(pList);
        if (newProgram != null) {
            pList.add(newProgram);
            DAO.saveToFile(pList, "program.dat"); //System.out.print("Failed to save");
            pU.listAllPrograms(getAllPrograms(pList));
        } else {
            MessageUI.printFormattedText("\nExit Successfully", ConsoleColor.GREEN);
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
            ArrList.insertionSort(pList, pCompare, "asc");
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

    public void addProgram(ListInterface<Program> pList) {
        System.out.print("\n-----------------------> Adding Programme\n"
                + "----------------------------------------->\n\n");
        addNewProgram(pList);
    }

    public void searchProg(ListInterface<Program> pList) {
        int choiceS;
        do {
            System.out.print("\n--------------------------> Searching Programme\n"
                    + "       1. List All Programme\n"
                    + "       2. Search Program\n"
                    + "       3. Back to Programme Main Page\n");
            System.out.print("\nEnter the number to continue(1,2,3): ");
            choiceS = iv.readInteger();
            switch (choiceS) {
                case 1:
                    pU.listAllPrograms(getAllPrograms(pList));
                    break;
                case 2:
                    searchProgram sp = new searchProgram(pList);
                    break;
                case 3:
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
                    break;
            }
        } while (choiceS != 3);
    }

    public void modifyProgram(ListInterface<Program> pList) {
        System.out.print("\n--------------------> Modifying Programme\n"
                + "----------------------------------------->\n\n");
        pU.listAllPrograms(getAllPrograms(pList));
        modifyProgram mp;
        mp = new modifyProgram(pList);
    }

    public void deleteProgram(ListInterface<Program> pList) {
        System.out.print("\n--------------------> Deleting Programme\n"
                + "----------------------------------------->\n\n");
        pU.listAllPrograms(getAllPrograms(pList));
        deleteProgram dp;
        dp = new deleteProgram(pList);
    }

    public void printReport() {
        reportProgram rp;
        rp = new reportProgram();
    }

    public void goTP() {
        tpm = new TutorialProgramMaintenance();
        tpm.runTutorialProgramMaintenance();
    }

    public void displayPrograms(ListInterface<Program> pList) {
        String outputStr = getAllPrograms(pList);
        pU.listAllPrograms(outputStr);
    }

//    @SuppressWarnings("unchecked")
//    public static void main(String[] args) {
//        ProgramMaintenance programMaintenance = new ProgramMaintenance();
//        programMaintenance.runProgramMaintenance();
//    }
}
