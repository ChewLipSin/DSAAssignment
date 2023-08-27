/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.ArrList;
import adt.DoublyLinkedList;
import adt.LinkedListInterface;
import adt.ListInterface;
import adt.OrderClause;
import entity.Course;
import entity.CourseProgram;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import utility.InputValue;
import utility.MessageUI;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseGenerateReportMaintenanceUI {

    private LinkedListInterface<CourseProgram> cp = new DoublyLinkedList<>();
    private ListInterface<Course> courses = new ArrList<>();

    public CourseGenerateReportMaintenanceUI(LinkedListInterface<CourseProgram> cp, ListInterface<Course> courses) {
        this.cp = cp;
        this.courses = this.courses;
    }

    private final InputValue iv = new InputValue();
    private final LocalDateTime myDateObj = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formattedDate = myDateObj.format(myFormatObj);

    public void displayHeader() {
        System.out.println("\t\t==============================================");
        System.out.println("\t\t||            Course Report Menu            ||");
        System.out.println("\t\t==============================================");
    }

    public void displayReportMenu() {
        System.out.println("\t\t1. Generate Course and Program Report");
        System.out.println("\t\t2. Generate Course Report");
        System.out.println("\t\t0. Exit");
    }

    public int getChoices() {
        int choice;
        do {
            System.out.print("Enter choice: ");
            choice = iv.readInteger();
            if (choice > 2 || choice < 0) {
                MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice > 2 || choice < 0);
        return choice;
    }

    public void displayCoursePReportHeader() {
        System.out.println("\t\t==============================================");
        System.out.println("\t\t||           Course Program Report           ||");
        System.out.println("\t\t==============================================");
    }

    public void displayCPFirstPart() {
        String line = "";
        sortByProgramID();
        sortById();
        System.out.println("");
        System.out.println(String.format("\t\t%-20s|%-20s|%-20s", "Course Code", "Program Code", "Main/Elective"));
        for (int i = 0; i < 65; i++) {
            line += "-";
        }
        System.out.println("\t\t" + line);
        Iterator<CourseProgram> it = cp.getIterator();
    }

    public void sortById() {
        cp.orderBy((c1, c2)
                -> c1.getCourseCode().compareTo(c2.getCourseCode()) < 0
                ? OrderClause.MOVE_FORWARD : OrderClause.MOVE_BACKWARD);
    }

    public void sortByProgramID() {
        cp.orderBy((c1, c2)
                -> c1.getProgramCode().compareTo(c2.getProgramCode()) < 0
                ? OrderClause.MOVE_FORWARD : OrderClause.MOVE_BACKWARD);
    }
}
