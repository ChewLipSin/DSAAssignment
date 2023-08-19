/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import boundary.CourseMaintenanceUI;
import control.CourseMaintenance;
import entity.Course;
import java.io.IOException;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseInitializer {

    //  Method to return a collection of with hard-coded entity values
    public ListInterface<Course> initializeCourse() {
        ListInterface<Course> cList = new ArrList<>();
        cList.add(new Course("BAIT1013", "Introduction of Computer Networks", 4));
        cList.add(new Course("BAMS1623", "Discrete Mathematics", 3));
        cList.add(new Course("BAMS1054", "Probability and Statistics", 4));
        cList.add(new Course("BJEL1713", "English for Tertiary Studies", 3));
        cList.add(new Course("BJEL1723", "Academic English", 3));
        cList.add(new Course("BACS1053", "Database Management", 4));
        return cList;
    }

    public static void main(String[] args) throws IOException {
        CourseMaintenanceUI courseUI = new CourseMaintenanceUI();
        CourseMaintenance courseMain = new CourseMaintenance();
        // To illustrate how to use the initializeProducts() method
        CourseInitializer c = new CourseInitializer();
        CourseDAO d = new CourseDAO();
        ListInterface<Course> courseList = c.initializeCourse();
        d.saveToFile(courseList);
        courseList.clear();
        ListInterface<Course> courseList2 = d.retrieveFromFile();
        System.out.println("\nCourse:\n" + courseList);
        System.out.println("\nCourse:\n" + courseList2);
        courseUI.listAllCourses(courseMain.getAllCourses());

    }

}
