/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ListInterface;
import boundary.CourseMaintenanceUI;
import boundary.CourseProgramMaintenanceUI;
import dao.CourseDAO;
import entity.Course;
import entity.CourseProgram;
import utility.MessageUI;
import utility.Sort;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseProgramMaintenance {
    private CourseDAO courseDAO = new CourseDAO();
    private CourseProgramMaintenanceUI coursePUI = new CourseProgramMaintenanceUI();
    private Sort s = new Sort();

    public CourseProgramMaintenance(ListInterface<CourseProgram> courseProgramList) {
        courseProgramList = courseDAO.retrieveFromFile();
    }
    public void runCourseProgramMaintenance(ListInterface<CourseProgram> courseProgramList) {
        int choice = 0;
        do {
            choice = coursePUI.getMenuChoices();
            switch (choice) {

                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    addNewCourseProgram(courseProgramList);
                    break;
                case 2:
                    removeCourseProgram(courseProgramList);
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();

            }
        } while (choice != 0);

    }

    private void addNewCourseProgram(ListInterface<CourseProgram> courseProgramList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void removeCourseProgram(ListInterface<CourseProgram> courseProgramList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
