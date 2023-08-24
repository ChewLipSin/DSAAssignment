/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templat1es/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import boundary.CourseMaintenanceUI;
import control.CourseMaintenance;
import entity.Course;
import utility.Sort;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseInitializer {

    //  Method to return a collection of with hard-coded entity values
    public ListInterface<Course> initializeCourse() {
        ListInterface<Course> cList = new ArrList<>();
        cList.add(new Course("BAIT1013", "Introduction of Computer Networks", 4, Course.Sem.JAN));
        cList.add(new Course("BAMS1623", "Discrete Mathematics", 3, Course.Sem.ALL));
        cList.add(new Course("BAMS1054", "Probability and Statistics", 4, Course.Sem.JUL));
        cList.add(new Course("BJEL1713", "English for Tertiary Studies", 3, Course.Sem.JAN));
        cList.add(new Course("BJEL1723", "Academic English", 3, Course.Sem.JUL));
        cList.add(new Course("BACS1053", "Database Management", 4, Course.Sem.ALL));
        cList.add(new Course("BMCS2013", "Data Engineering", 4, Course.Sem.ALL));
        cList.add(new Course("AACS1304", "System Analysis and Design", 3, Course.Sem.JUL));
        cList.add(new Course("BAIT1043", "System Analysis and Design", 3, Course.Sem.JAN));
        cList.add(new Course("AMIT1713", "IT Fundamentals and Applications", 3, Course.Sem.ALL));
        cList.add(new Course("BMIT1723", "IT Fundamentals and Applications", 3, Course.Sem.ALL));
        cList.add(new Course("ABFA1173", "Principles of Accounting ", 3, Course.Sem.ALL));
        cList.add(new Course("BBFA1173", "Principles of Accounting ", 3, Course.Sem.ALL));
        cList.add(new Course("BBMF3203", "Research Method", 3, Course.Sem.ALL));
        cList.add(new Course("MPU-2312", "Civic Consciousness and Volunteerism", 2, Course.Sem.ALL));
        cList.add(new Course("MPU-2212", "Bahasa Kebangsaan A", 2, Course.Sem.ALL));

        return cList;
    }

    public static void main(String[] args) {
        CourseInitializer c = new CourseInitializer();
        ListInterface<Course> courseList = c.initializeCourse();
        CourseMaintenanceUI courseUI = new CourseMaintenanceUI();
        CourseMaintenance courseMain = new CourseMaintenance(courseList);
        Sort s = new Sort();
        // To illustrate how to use the initializeProducts() method
        CourseDAO d = new CourseDAO();
        d.saveToFile(courseList);
        ListInterface<Course> courseList2 = d.retrieveFromFile();
//        System.out.println("\nCourse:\n" + courseList);
//        System.out.println("\nCourse:\n" + courseList2);

//        courseMain.listedCourse(courseList);
        CourseMaintenance cm = new CourseMaintenance(courseList);
//        s.insertionSort(courseList,"courseCode");
        Sort.quickSort(courseList, 1, courseList.size(), "updatedAt");
        System.out.println(courseList);
        Sort.quickSort(courseList, 1, courseList.size(), "createdAt");
        System.out.println(courseList);
        s.insertionSortDes(courseList, "courseCode");
        System.out.println(courseList);
//        System.out.println("\nCourse:\n" + courseList);
        cm.runCourseMaintenance(courseList);

    }

}
