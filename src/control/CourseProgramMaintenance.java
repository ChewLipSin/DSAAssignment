/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ListInterface;
import adt.MapInterface;
import boundary.CourseMaintenanceUI;
import boundary.CourseProgramMaintenanceUI;
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
public class CourseProgramMaintenance {

    private DAO dAO = new DAO();
    private CourseProgramMaintenanceUI coursePUI = new CourseProgramMaintenanceUI();
    private Sort s = new Sort();
    private CourseMaintenanceUI courseUI = new CourseMaintenanceUI();

    public CourseProgramMaintenance(MapInterface<String, ListInterface<Program>> courseProgramList,
            ListInterface<Course> courseList, ListInterface<Program> programList) {
        courseProgramList = dAO.mapRetrieveFromFile("courseProgram.dat");
        courseList = dAO.retrieveFromFile("course.dat");
        programList = dAO.retrieveFromFile("program.dat");

    }

    public void runCourseProgramMaintenance(
            MapInterface<String, ListInterface<Program>> courseProgramList,
            ListInterface<Course> courseList,
            ListInterface<Program> programList) {
        int choice = 0;
        do {
            choice = coursePUI.getMenuChoices();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    addCourseProgram(courseProgramList, courseList, programList, "add");
                    break;
                case 2:
                    removeCourseProgram(courseProgramList, courseList, programList, "delete");
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();

            }
        } while (choice != 0);

    }

    private void addCourseProgram(MapInterface<String, ListInterface<Program>> courseProgramList, ListInterface<Course> courseList, ListInterface<Program> programList, String add) {
        s.insertionSort(courseList, "courseCode");
        courseUI.listAllCourses(courseList);
    }

    private void removeCourseProgram(MapInterface<String, ListInterface<Program>> courseProgramList, ListInterface<Course> courseList, ListInterface<Program> programList, String delete) {
        s.insertionSort(courseList, "courseCode");
        courseUI.listAllCourses(courseList);
    }

}
