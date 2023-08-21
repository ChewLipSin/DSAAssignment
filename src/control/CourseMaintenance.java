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
import utility.*;

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

    public CourseMaintenance() {
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
                    addNewCourse(courseList);
                    courseUI.listAllCourses(getAllCourses(courseList));
                    Command.pressEnterToContinue();
                    break;
                case 2:
                    removeCourse(courseList);
                    courseUI.listAllCourses(getAllCourses(courseList));
                    Command.pressEnterToContinue();
                case 3:
                    searchCourse(courseList);
                    Command.pressEnterToContinue();
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
        boolean confirm = false;
        boolean newCourseFull = false;
        Course newCourse = courseUI.inputCourseDetails(courseList);

        newCourseFull = (!newCourse.equals(new Course(null, null, 0, null)));
        if (newCourseFull == true) {
            confirm = addConfirmation(newCourse);
            if (confirm == true) {
                courseList.add(newCourse);
                courseDAO.saveToFile(courseList);
                MessageUI.displaySuccessConfirmationMessage("Added");
                Command.pressEnterToContinue();

            }
        }
    }

    public String getAllCourses(ListInterface<Course> courseList) {
//        int currentIndex = 0;
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

    public boolean addConfirmation(Course newCourse) {
        courseUI.displayNewCourse(newCourse);
        boolean confirm = courseUI.getConfirmationChoice("add");
        return confirm;
    }

    private void removeCourse(ListInterface<Course> courseList) {
        boolean confirm = false;
        boolean deleteCourse = false;
        int courseSelected = courseUI.inputRemoveCode(courseList);

        deleteCourse = (courseSelected > -1);
        if (deleteCourse == true) {
            confirm = deleteConfirmation(courseList.getEntry(courseSelected));
            if (confirm == true) {
                courseList.remove(courseSelected);
                courseDAO.saveToFile(courseList);
                MessageUI.displaySuccessConfirmationMessage("Removed");
                Command.pressEnterToContinue();
            }

        }
    }

    private boolean deleteConfirmation(Course courseSelected) {
        courseUI.displayCourse(courseSelected);
        boolean confirm = courseUI.getConfirmationChoice("remove");
        return confirm;
    }

    private void searchCourse(ListInterface<Course> courseList) {
//        Sort.heapSort(courseList);
        System.out.println(courseList);
    }

}
