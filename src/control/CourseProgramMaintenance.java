/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.LinkedListInterface;
import adt.ListInterface;
import boundary.CourseMaintenanceUI;
import boundary.CourseProgramMaintenanceUI;
import dao.DAO;
import entity.Course;
import entity.CourseProgram;
import entity.Program;
import utility.MessageUI;
import utility.Search;
import utility.Sort;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseProgramMaintenance {

    private static final DAO<Course> cDAO = new DAO<>();
    private static final DAO<Program> pDAO = new DAO<>();
    private static final DAO<CourseProgram> cpDAO = new DAO<>();
    private final CourseProgramMaintenanceUI coursePUI = new CourseProgramMaintenanceUI();
    private final Sort s = new Sort();
    private final Search search = new Search();
    private final CourseMaintenanceUI courseUI;

    public CourseProgramMaintenance(LinkedListInterface<CourseProgram> courseProgramList,
            ListInterface<Course> courseList, ListInterface<Program> programList) {
        this.courseUI = new CourseMaintenanceUI();
        courseProgramList = cpDAO.dLLRetrieveFromFile("courseProgram.dat");
        courseList = cDAO.retrieveFromFile("course.dat");
        programList = pDAO.retrieveFromFile("program.dat");

    }

    public void runCourseProgramMaintenance(
            LinkedListInterface<CourseProgram> courseProgramList,
            ListInterface<Course> courseList,
            ListInterface<Program> programList) {
        int choice;
        do {
            choice = coursePUI.getMenuChoices();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    addCourseProgram(courseProgramList, courseList, programList);
                    break;
                case 2:
                    removeCourseProgram(courseProgramList, courseList, programList);
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();

            }
        } while (choice != 0);

    }

    private void addCourseProgram(LinkedListInterface<CourseProgram> courseProgramList, ListInterface<Course> courseList, ListInterface<Program> programList) {
        s.insertionSort(courseList, "courseCode");

        int choice;
        do {
            courseProgramList = cpDAO.dLLRetrieveFromFile("courseProgram.dat");
            courseUI.listAllCourses(courseList);
            choice = coursePUI.getCourseChoices(courseList);
            if (choice != 0) {
                coursePUI.addCourseProgramUI(courseProgramList, courseList, programList, choice);
            }
        } while (choice != 0);

    }

    private void removeCourseProgram(LinkedListInterface<CourseProgram> courseProgramList, ListInterface<Course> courseList, ListInterface<Program> programList) {
        s.insertionSort(courseList, "courseCode");
        int choice;
        do {
            courseProgramList = cpDAO.dLLRetrieveFromFile("courseProgram.dat");
            courseUI.listAllCourses(courseList);
            choice = coursePUI.getCourseChoices(courseList);
            if (choice != 0) {
                coursePUI.removeCourseProgramUI(courseProgramList, courseList, programList, choice);
            }
        } while (choice != 0);
    }

    public static void main(String[] args) {
        LinkedListInterface<CourseProgram> courseProgramList = cpDAO.dLLRetrieveFromFile("courseProgram.dat");
        for (int i = 0; i < courseProgramList.sizeOf(); i++) {
            System.out.println(String.format("%s  %s  %s", courseProgramList.get(i).getCourseCode(), courseProgramList.get(i).getProgramCode(), courseProgramList.get(i).isIsElective()));

        }
        ListInterface<Course> courseList = cDAO.retrieveFromFile("course.dat");
        ListInterface<Program> programList = pDAO.retrieveFromFile("program.dat");
        CourseProgramMaintenance cpm = new CourseProgramMaintenance(courseProgramList, courseList, programList);
        cpm.runCourseProgramMaintenance(courseProgramList, courseList, programList);
    }
}
