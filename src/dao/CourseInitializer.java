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
        cList.add(new Course("BAIT1013", "Introduction of Computer Networks", 4, Course.Sem.JAN));
        cList.add(new Course("BAMS1623", "Discrete Mathematics", 3, Course.Sem.JAN));
        cList.add(new Course("BAMS1054", "Probability and Statistics", 4, Course.Sem.JAN));
        cList.add(new Course("BJEL1713", "English for Tertiary Studies", 3, Course.Sem.JAN));
        cList.add(new Course("BJEL1723", "Academic English", 03, Course.Sem.JUL));
        cList.add(new Course("BACS1053", "Database Management", 4, Course.Sem.ALL));
        return cList;
    }

    public static void main(String[] args) {
        CourseInitializer c = new CourseInitializer();
        ListInterface<Course> courseList = c.initializeCourse();
        CourseMaintenanceUI courseUI = new CourseMaintenanceUI();
        CourseMaintenance courseMain = new CourseMaintenance(courseList);
        // To illustrate how to use the initializeProducts() method
        CourseDAO d = new CourseDAO();
        d.saveToFile(courseList);
        ListInterface<Course> courseList2 = d.retrieveFromFile();
        System.out.println("\nCourse:\n" + courseList);
        System.out.println("\nCourse:\n" + courseList2);
        courseUI.listAllCourses(courseMain.getAllCourses(courseList));
        CourseMaintenance cm = new CourseMaintenance(courseList);
        cm.runCourseMaintenance(courseList);

    }

}
