package control;

import adt.ArrList;
import adt.ListInterface;
import boundary.TutorialProgramUI;
import client.deleteTutorialInProgram;
import client.searchTutorialProgram;
import dao.tDAO;
import entity.Program;
import entity.Tutorial;
import entity.TutorialProgram;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import utility.MessageUI;
/**
 *
 * @author Lim Yi Leong
 */
public class TutorialProgramMaintenance {

    private ListInterface<TutorialProgram> tpList = new ArrList<>();
    private ListInterface<Program> pList = new ArrList<>();
    private ListInterface<Tutorial> tList = new ArrList<>();
    private final tDAO DAO = new tDAO();
    private final TutorialProgramUI tpU = new TutorialProgramUI();
    private final ProgramMaintenance pM = new ProgramMaintenance();
     private Scanner scanner = new Scanner(System.in);

    public TutorialProgramMaintenance() {
        this.tpList = DAO.retrieveFromFile("tutorialProgram.dat");
    }
  
    public void runTutorialProgramMaintenance() {
        int choice = 0;
        tpList = DAO.retrieveFromFile("tutorialProgram.dat");
        pList = DAO.retrieveFromFile("program.dat");
        tList = DAO.retrieveFromFile("tutorial.dat");
        do {
            choice = tpU.getMenuChoice();
            switch (choice) {
                case 1:
                    System.out.print("\n\n");
                    System.out.print("-----------> Adding Tutorial To Programme\n"
                                   + "----------------------------------------->\n");
                    addNewTutorialProgram(pList,tList);
                    tpU.listAllTutorialPrograms(getAllTutorialPrograms(tpList));
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
                                tpU.listAllTutorialPrograms(getAllTutorialPrograms(tpList));
                                break;
                            case 2:
                                new searchTutorialProgram(tpList);
                                break;
                            case 3:
                                runTutorialProgramMaintenance();
                                break;
                            default:
                                MessageUI.displayInvalidChoiceMessage();
                                break;
                        }
                    } while (choiceS != 3);
                    break;
                case 3:
                    System.out.print("\n\n");
                    System.out.print("-------> Deleting Tutorial From Programme\n"
                                   + "----------------------------------------->\n");
                    tpU.listAllTutorialPrograms(getAllTutorialPrograms(tpList));
                    new deleteTutorialInProgram(tpList);
                    break;
                case 4:
                    pM.runProgramMaintenance();
                    break;
                case 5:
                    //main
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
        //main page
    }

    public void addNewTutorialProgram(ListInterface<Program> pList, ListInterface<Tutorial> tList) {
        TutorialProgram newProgram = tpU.inputTutorialProgramDetails(pList, tList);
        if (newProgram != null) {
        tpList.add(newProgram);
        try {
            DAO.saveToFile(tpList,"tutorialProgram.dat");
        } catch (IOException ex) {
            System.out.print("Failed to save");
        }
        }
    }

    public String getAllTutorialPrograms(ListInterface<TutorialProgram> tutorialProgramList) {
        String outputStr = "";
        outputStr += "List of Tutorial Programme:\n";
        outputStr += String.format("%-5s | %-15s | %-80s | %-20s | %-25s | %-30s | %s\n",
                "No.", "Program Code", "Program Name", "Tutorial Group", "Number of Students", "Class Rap", "Intake");

        Iterator<TutorialProgram> iterator = tutorialProgramList.getIterator();
        int tutorialNumber = 1;

        while (iterator.hasNext()) {
            TutorialProgram tutorialProgram = iterator.next();
            outputStr += String.format("%-5d | %-15s | %-80s | %-20s | %-25d | %-30s | %d-%02d\n",
                    tutorialNumber, tutorialProgram.getCode(), tutorialProgram.getProgramname(),
                    tutorialProgram.getGroupname(), tutorialProgram.getNumStudent(),
                    tutorialProgram.getClassRap(), tutorialProgram.getIntakeYear(), tutorialProgram.getIntakeMonth());
            tutorialNumber++;
        }

        return outputStr;
    }
    
    public String getOneTutorialProgram(ListInterface<TutorialProgram> tutorialProgramList, int index) {
    String outputStr = "";
        outputStr += "List of Tutorial Programme:\n";
        outputStr += String.format("%-5s | %-15s | %-80s | %-10s | %-25s | %30s | %20s\n",
                "No. | Program Code | Program Name | Tutorial Group | Number of Students | Class Rap | Intake\n");

    Iterator<TutorialProgram> iterator = tutorialProgramList.getIterator();
    int tutorialNumber = 1;
    TutorialProgram tutorialProgram = null; // Initialize tutorialProgram outside the loop
    boolean found = false;  // Renamed 'stop' to 'found' for clarity

    while (iterator.hasNext()) {
        tutorialProgram = iterator.next();
        if (tutorialNumber == index) {
            found = true; 
            break;
        }
        tutorialNumber++;
    }

    if (found) {
        outputStr += String.format("%-5s | %-15s | %-80s | %-10s | %-25d | %30s | %d-%02d\n",
                    tutorialNumber, tutorialProgram.getCode(), tutorialProgram.getProgramname(),
                    tutorialProgram.getGroupname(), tutorialProgram.getNumStudent(),
                    tutorialProgram.getClassRap(), tutorialProgram.getIntakeYear(), tutorialProgram.getIntakeMonth());
    } else {
        outputStr += "Program not found.\n";
    }

    return outputStr;
}


    public void displayTutorialPrograms(ListInterface<TutorialProgram> tpList) {
        String outputStr = getAllTutorialPrograms(tpList);
        tpU.listAllTutorialPrograms(outputStr);
    }
      
}
