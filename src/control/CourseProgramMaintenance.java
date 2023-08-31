/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.DoublyLinkedList;
import adt.LinkedListInterface;
import adt.ListInterface;
import boundary.CourseMaintenanceUI;
import boundary.CourseProgramMaintenanceUI;
import dao.DAO;
import dao.Initializer;
import entity.Course;
import entity.CourseProgram;
import entity.Program;
import utility.Command;
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
    private final CourseMaintenanceUI courseUI = new CourseMaintenanceUI();
    private final Initializer in = new Initializer();

    public void runCourseProgramMaintenance(ListInterface<Course> courseList, ListInterface<Program> programList) {
        LinkedListInterface<CourseProgram> courseProgramList = cpDAO.dLLRetrieveFromFile("courseProgram.dat");
        int choice;
        do {
            choice = coursePUI.getMenuChoice();
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
                Command.cls();
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
                Command.cls();
                coursePUI.removeCourseProgramUI(courseProgramList, courseList, programList, choice);
            }
        } while (choice != 0);
    }

    public void deleteCourse(Course course, LinkedListInterface<CourseProgram> courseProgramList) {
        LinkedListInterface<CourseProgram> cp1 = new DoublyLinkedList<>();
        for (int i = 0; i < courseProgramList.sizeOf(); i++) {
            if (courseProgramList.get(i).getCourseCode().equals(course.getCourseCode())) {
                cp1.add(courseProgramList.get(i));
            }
        }
        for (int i = 0; i < cp1.sizeOf(); i++) {
            courseProgramList.remove(cp1.get(i));
        }
        cpDAO.saveToFile(courseProgramList, "courseProgram.dat");
    }

    public void modifyCourse(Course course, Course oldCourse, LinkedListInterface<CourseProgram> courseProgramList) {
        LinkedListInterface<CourseProgram> cp1 = new DoublyLinkedList<>();
        for (int i = 0; i < courseProgramList.sizeOf(); i++) {
            if (courseProgramList.get(i).getCourseCode().equals(oldCourse.getCourseCode())) {
                cp1.add(new CourseProgram(course.getCourseCode(), courseProgramList.get(i).getProgramCode(),
                        courseProgramList.get(i).isIsElective()));
                for (int j = 0; j < cp1.sizeOf(); j++) {
                    courseProgramList.set(i, cp1.get(j));
                }
            }
        }

        cpDAO.saveToFile(courseProgramList, "courseProgram.dat");
    }
}
