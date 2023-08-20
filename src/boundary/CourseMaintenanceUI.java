/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.ListInterface;
import entity.Course;
import entity.Course.Sem;
import java.util.Iterator;
import utility.MessageUI;
import utility.readValue;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseMaintenanceUI {

    int choice;
    readValue rv = new readValue();
    MessageUI msg = new MessageUI();

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
            if (choice > 9 || choice < 0) {
                msg.displayInvalidChoiceMessage();
            }
        } while (choice > 9 || choice < 0);

        return choice;
    }

    public Course inputCourseDetails(ListInterface<Course> courseList) {
        String courseCode = inputCourseCode(courseList);
        if ("0".equals(courseCode)) {
            return new Course(null, null, 0, null);
        }
        String title = inputTitle();
        if ("0".equals(title)) {
            return new Course(null, null, 0, null);
        }
        int creditHour = inputCreditHour();
        if (creditHour == 0) {
            return new Course(null, null, 0, null);
        }
        Sem semester = inputSemester();
        if (semester == null) {
            return new Course(null, null, 0, null);
        }
        System.out.println();
        return new Course(courseCode, title, creditHour, semester);
    }

    private String inputCourseCode(ListInterface<Course> courseList) {
        Iterator it = courseList.getIterator();
        int i = 1;
        String courseCode = "";
        boolean match = false;
        do {
            match = false;
            System.out.print("Enter Course Code(Enter '0' to exit):");
            courseCode = rv.readCourseCode();
            if ("0".equals(courseCode)) {
                return courseCode = "0";
            }
            courseCode = courseCode.toUpperCase();
            while (it.hasNext()) {
                it.next();
                String oldCourseCode = courseList.getEntry(i).getCourseCode();
                oldCourseCode = oldCourseCode.toUpperCase();
                match = oldCourseCode.equals(courseCode);
                i++;
                if (match) {
                    System.out.println("This " + courseCode + " course code already exist");
                    break;
                }
            }
        } while (match);
        courseCode = courseCode.toUpperCase();
        return courseCode;
    }

    private String inputTitle() {
        System.out.print("Enter the title(Enter '0' to exit): ");
        String title = rv.readCharInt();
        return title;

    }

    private int inputCreditHour() {
        int creditHour = 0;

        System.out.print("Enter credit hour(Enter '0' to exit): ");
        do {
            creditHour = rv.readInteger();
            MessageUI.displayInvalidCreditHourMessage(creditHour);
        } while (creditHour < 0 || creditHour > 20);
        return creditHour;
    }

    private Sem inputSemester() {
        int choice = 0;
        Sem semester = null;
        do {
            System.out.println("1. JAN");
            System.out.println("2. JUL");
            System.out.println("3. ALL");
            System.out.print("Select course semester: ");
            choice = rv.readInteger();
            switch (choice) {
                case 1:
                    semester = Sem.JAN;
                    break;
                case 2:
                    semester = Sem.JUL;
                    break;
                case 3:
                    semester = Sem.ALL;
                    break;
                default:
                    break;
            }
            if (choice < 0 || choice > 3) {
                MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice < 0 || choice > 3);
        return semester;
    }

    public void listAllCourses(String allCourses) {
        System.out.println("\nList of Products:\n" + allCourses);
    }

}
