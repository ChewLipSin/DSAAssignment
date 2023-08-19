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

//    private ListInterface<Course> courseList = new ArrList<>();
    private CourseDAO courseDAO = new CourseDAO();
    private CourseMaintenanceUI courseUI = new CourseMaintenanceUI();

    public CourseMaintenance(ListInterface<Course> courseList) {
        courseList = courseDAO.retrieveFromFile();
    }

    public void runCourseMaintenance(ListInterface<Course> courseList) {
        int choice = 0;
        do {
            choice = courseUI.getMenuChoices();
            switch (choice) {

                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
//                    addNewCourse(courseList);
                    courseUI.inputCourseDetails(courseList);
                    courseUI.listAllCourses(getAllCourses(courseList));
                    break;
                case 5:
                    courseUI.listAllCourses(getAllCourses(courseList));
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();

            }
        } while (choice != 0);

    }

    private void addNewCourse(ListInterface<Course> courseList) {
        Course newCourse = courseUI.inputCourseDetails(courseList);
        courseList.add(newCourse);
        courseDAO.saveToFile(courseList);
    }

    public String getAllCourses(ListInterface<Course> courseList) {
        int currentIndex = 0;
//        for (int i = 1; i <= courseList.size(); i++) {
//            outputStr += courseList.getEntry(i) + "\n";
//        }
        String outputStr = "";
        Iterator it = courseList.getIterator();

        while (it.hasNext()) {
//            System.out.println(it.next());
            outputStr += it.next() + "\n";
        }
        return outputStr;
    }

}
