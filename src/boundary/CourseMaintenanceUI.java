/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.ListInterface;
import entity.Course;
import java.util.Iterator;
import java.util.Scanner;
import utility.readValue;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseMaintenanceUI {

    int choice;
    readValue rv = new readValue();

    public int getMenuChoices() {
        System.out.println("\nCourse Management Subsystem Menu");
        System.out.println("1. Add new course");
        System.out.println("2. Remove course");
        System.out.println("3. Search course");
        System.out.println("4. Amend course details");
        System.out.println("5. List all course");
        System.out.println("6. Add programme to a course");
        System.out.println("7. Remove programme from a course");
        System.out.println("8. Generate Report");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
        do {
            choice = rv.readInteger();
        } while (choice > 9 || choice < 0);

        return choice;
    }

    public Course inputCourseDetails(ListInterface<Course> courseList) {
        String courseCode = inputCourseCode(courseList);
        String title = inputTitle(courseList);
        int creditHour = inputCreditHour(courseList);
        System.out.println();
        return new Course(courseCode, title, creditHour);
    }

    private String inputCourseCode(ListInterface<Course> courseList) {
        Iterator it = courseList.getIterator();
        int i = 0;
        while (it.hasNext()) {
            it.next();
            
            i++;
        }

//        courseList.contains();
        System.out.println("Enter Course Code");
        String courseCode = rv.readString();
        return courseCode;
    }

    private String inputTitle(ListInterface<Course> courseList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private int inputCreditHour(ListInterface<Course> courseList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void listAllCourses(String allCourses) {
        System.out.println("\nList of Products:\n" + allCourses);
    }

}
