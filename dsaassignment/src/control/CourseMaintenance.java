/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.CourseMaintenanceUI;
import dao.CourseDAO;
import entity.Course;
import java.io.IOException;
import java.util.Iterator;
import utility.MessageUI;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseMaintenance {

    private ListInterface<Course> courseList = new ArrList<>();
    private CourseDAO courseDAO = new CourseDAO();
    private CourseMaintenanceUI courseUI = new CourseMaintenanceUI();

    public CourseMaintenance() {
        courseList = courseDAO.retrieveFromFile();
    }

    public void runCourseMaintenance() throws IOException {
        int choice = 0;
        do {
            choice = courseUI.getMenuChoices();
            switch (choice) {

                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    addNewCourse();
                    courseUI.listAllCourses(getAllCourses());
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();

            }
        } while (choice != 0);

    }

    private void addNewCourse() throws IOException {
        Course newCourse = courseUI.inputCourseDetails();
        courseList.add(newCourse);
        courseDAO.saveToFile(courseList);
    }

    public String getAllCourses() {
        int currentIndex = 0;
//        for (int i = 1; i <= courseList.size(); i++) {
//            outputStr += courseList.getEntry(i) + "\n";
//        }
        String outputStr ="";
        Iterator it = courseList.getIterator();

        while (it.hasNext()) {
//            System.out.println(it.next());
            outputStr += it.next() + "\n";
        }
        return outputStr;
    }

}
