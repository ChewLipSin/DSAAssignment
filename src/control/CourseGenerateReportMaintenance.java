/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ArrList;
import adt.DoublyLinkedList;
import adt.LinkedListInterface;
import adt.ListInterface;
import boundary.CourseGenerateReportMaintenanceUI;
import boundary.CourseMaintenanceUI;
import dao.DAO;
import entity.Course;
import entity.CourseProgram;
import entity.Program;
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

    public void runCourseGenerateReportMaintenance() {
        LinkedListInterface<CourseProgram> cp = new DoublyLinkedList<>();
        ListInterface<Course> courses = new ArrList<>();
        cp = cpDAO.dLLRetrieveFromFile("courseProgram.dat");
        courses = cDAO.retrieveFromFile("course.dat");
        CourseGenerateReportMaintenanceUI cReportUI = new CourseGenerateReportMaintenanceUI(cp, courses);

        int choice;
        System.out.println("\n\n");
        do {
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
//                    runTutorMaintenance(courseList);
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();

            }
        } while (choice != 0);
    }

    private void generateCourseProgramReport() {
        LinkedListInterface<CourseProgram> cp = new DoublyLinkedList<>();
        ListInterface<Course> courses = new ArrList<>();
        cp = cpDAO.dLLRetrieveFromFile("courseProgram.dat");
        courses = cDAO.retrieveFromFile("course.dat");
        CourseGenerateReportMaintenanceUI cReportUI = new CourseGenerateReportMaintenanceUI(cp, courses);
        cReportUI.displayCoursePReportHeader();
        cReportUI.displayCPFirstPart();
    }

    public static void main(String[] args) {
        LinkedListInterface<CourseProgram> cp = new DoublyLinkedList<>();
        ListInterface<Course> courses = new ArrList<>();
        CourseGenerateReportMaintenance ca = new CourseGenerateReportMaintenance();
        cp = ca.cpDAO.dLLRetrieveFromFile("courseProgram.dat");
        courses = cDAO.retrieveFromFile("course.dat");
        CourseGenerateReportMaintenanceUI cReportUI = new CourseGenerateReportMaintenanceUI(cp, courses);
        ca.runCourseGenerateReportMaintenance();
    }
}
