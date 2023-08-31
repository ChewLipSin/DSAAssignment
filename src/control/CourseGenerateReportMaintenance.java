/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ArrList;
import adt.ArrayStack;
import adt.DoublyLinkedList;
import adt.LinkedListInterface;
import adt.ListInterface;
import adt.StackInterface;
import boundary.CourseGenerateReportMaintenanceUI;
import boundary.CourseMaintenanceUI;
import dao.DAO;
import entity.Course;
import entity.CourseProgram;
import entity.Program;
import java.io.IOException;
import utility.Command;
import utility.MessageUI;
import utility.Sort;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseGenerateReportMaintenance {

    private static final DAO<Course> cDAO = new DAO<>();
    private static final DAO<Program> pDAO = new DAO<>();
    private final DAO<CourseProgram> cpDAO = new DAO<>();
    private final Sort s = new Sort();

    public void runCourseGenerateReportMaintenance() throws IOException, InterruptedException {
        LinkedListInterface<CourseProgram> cp = new DoublyLinkedList<>();
        ListInterface<Course> courses = new ArrList<>();
        cp = cpDAO.dLLRetrieveFromFile("courseProgram.dat");
        courses = cDAO.retrieveFromFile("course.dat");
        CourseGenerateReportMaintenanceUI cReportUI = new CourseGenerateReportMaintenanceUI(cp, courses);

        int choice;
        System.out.println("\n\n");
        do {
            Command.cls();
            cReportUI.displayHeader();
            cReportUI.displayReportMenu();
            choice = cReportUI.getChoices();
            switch (choice) {

                case 0:
                    break;
                case 1:
                    generateCourseProgramReport();
                    break;
                case 2:
                    generateCourseReport();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();

            }
        } while (choice != 0);
    }

    private void generateCourseProgramReport() throws IOException, InterruptedException {
        LinkedListInterface<CourseProgram> cp = new DoublyLinkedList<>();
        ListInterface<Course> courses = new ArrList<>();
        cp = cpDAO.dLLRetrieveFromFile("courseProgram.dat");
        courses = cDAO.retrieveFromFile("course.dat");
        CourseGenerateReportMaintenanceUI cReportUI = new CourseGenerateReportMaintenanceUI(cp, courses);
        cReportUI.progress();
        cReportUI.displayCoursePReportHeader();

        cReportUI.displayCourseProgramReport();
    }

    private void generateCourseReport() {
        LinkedListInterface<CourseProgram> cp = new DoublyLinkedList<>();
        ListInterface<Course> courses = new ArrList<>();
        StackInterface<String> choice = new ArrayStack<>();
        StackInterface<String> arrangeChoice = new ArrayStack<>();
        String line = "";
        boolean report = false;
        cp = cpDAO.dLLRetrieveFromFile("courseProgram.dat");
        courses = cDAO.retrieveFromFile("course.dat");
        CourseGenerateReportMaintenanceUI cReportUI = new CourseGenerateReportMaintenanceUI(cp, courses);
        choice = cReportUI.getCourseReportMenu(choice);
        for (int i = 0; i < 65; i++) {
            line += "-";
        }
        if (!choice.isEmpty()) {
            cReportUI.progress();
            cReportUI.displayCourseReportHeader();
        }
        while (!choice.isEmpty()) {
            arrangeChoice.push(choice.pop());
        }
        while (!arrangeChoice.isEmpty()) {
            String choice2 = arrangeChoice.pop();
            if (choice2.equals("Course Code Report")) {
                cReportUI.displayCourseCodeReport();
            }

            if (choice2.equals("Credit Hour Report")) {
                cReportUI.displayCreditHoursReport();
            }

            if (choice2.equals("Semester Report")) {
                cReportUI.displayCourseSemesterReport();
            }
            report = true;

        }
        if (report) {
            System.out.println("\t\t\t" + line);
            cReportUI.displayReportFooter();
            System.out.println("");
            System.out.print("\t\t\t");
            Command.pressEnterToContinue();
        }
    }
}
