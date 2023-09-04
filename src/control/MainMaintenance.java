/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.MainMaintenanceUI;
import dao.Initializer;
import java.io.IOException;
import utility.Command;
import utility.MessageUI;

/**
 *
 * @author Chew Lip Sin
 */
public class MainMaintenance {

    private final MainMaintenanceUI mainUI = new MainMaintenanceUI();
    private final CourseMaintenance courseMain = new CourseMaintenance();
    private final TutorManagement tutorMain = new TutorManagement();
    private final Initializer in = new Initializer();
    private final TutorialGroupManagement tutorialGroupManagement = new TutorialGroupManagement();
    private final ProgramMaintenance programMain = new ProgramMaintenance();

    public static void main(String[] args) throws IOException, InterruptedException {
        MainMaintenance mainMain = new MainMaintenance();
        mainMain.runMainMaintenance();
    }

    public void runMainMaintenance() throws IOException, InterruptedException {
        int choice;
        do {
            Command.cls();
            choice = mainUI.getMenuChoices();
            switch (choice) {

                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    programMain.runProgramMaintenance();
                    break;
                case 2:
                    tutorMain.runTutorManagement();
                    break;
                case 3:
                    tutorialGroupManagement.runTutorialGroupManagement();
                    break;
                case 4:
                    courseMain.runCourseMaintenance();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();

            }
        } while (choice != 0);
    }
}
