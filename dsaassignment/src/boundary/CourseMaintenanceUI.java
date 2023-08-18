/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.Course;
import java.util.Scanner;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseMaintenanceUI {

    Scanner sc = new Scanner(System.in);
    int choice;

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
            try {
                choice = sc.nextInt();
                sc.nextLine();
                System.out.println();
            } catch (Exception e) {
                System.out.print("Invalid, please enter a number:");
                sc.next();
            }
        } while (choice > 9 || choice < 0);

        return choice;
    }

    public Course inputCourseDetails() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
