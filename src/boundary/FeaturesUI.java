package boundary;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author fungc
 */
import utility.Validation;
import dao.DAO;
import adt.ArrList;
import adt.ListInterface;
import static boundary.TutorialGroupManagementUI.promptString;
import control.TutorialGroupManagement;
import static control.TutorialGroupManagement.findGroupByName;
import static control.TutorialGroupManagement.ttlgrpDAO;
import entity.TutorialGroup;
import entity.Student;
import utility.*;

import java.util.Scanner;

public class FeaturesUI {

    private static ListInterface<TutorialGroup> tutorialGroups = TutorialGroupManagement.tutorialGroups;
    private static ListInterface<Student> students;
    private static int studentsAddedCount = 0;
    private static int studentsRemovedCount = 0;
    private static int studentsChangedGroupCount = 0;

    public static void addStudent(Scanner scanner, TutorialGroup group) {
        String name = promptString("Enter student name: ");
        String id;
        boolean isIdUsed;
        do {
            students = group.listStudents();
            id = promptString("Enter student ID (xxWMRxxxxx / xxWMDxxxxx): ");
            isIdUsed = Validation.isStudentIDUsed(id, tutorialGroups, students);

            if (!Validation.isValidStudentID(id)) {
                System.out.println("Invalid student ID format. Please enter a valid format (xxyyyxxxxx).");
            } else if (isIdUsed) {
                System.out.println("Student ID is already used by another student. Please Re-enter.     ");
            }
        } while (!Validation.isValidStudentID(id) || isIdUsed);

        String email;
        do {
            email = promptString("Enter student email (xxx@student.tarc.edu.my): ");
            if (!Validation.isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email (xxx@student.tarc.edu.my).");
            }
        } while (!Validation.isValidEmail(email));

        Student studentToAdd = new Student(name, id, email);
        if (group.addStudent(studentToAdd)) {
            System.out.println("Student added successfully.");
            studentsAddedCount++;
            ttlgrpDAO.saveToFile(tutorialGroups, "TtlGrpStudents.dat");
        } else {
            System.out.println("Failed to add student. Group may be full.");
        }
    }

    public static void removeStudent(Scanner scanner, TutorialGroup group) {
        // Add your student removal logic here
        String nameToRemove = promptString("Enter student name to remove: ");
        String idToRemove = promptString("Enter student ID to remove: ");

        Student studentToRemove = new Student(nameToRemove, idToRemove);

        if (group.removeStudent(studentToRemove)) {
            System.out.println("Student removed successfully.");
            studentsRemovedCount++;
            ttlgrpDAO.saveToFile(tutorialGroups, "TtlGrpStudents.dat");
        } else {
            System.out.println("Student not found in the group.");
        }
    }

    public static void changeStudentGroup(Scanner scanner, TutorialGroup group) {
        // Add your student group change logic here
        System.out.print("Enter student name to change group: ");
        String nameToChange = scanner.nextLine();
        System.out.print("Enter student ID to change group: ");
        String idToChange = scanner.nextLine();

        // Check if student exists in the group
        students = group.listStudents();
        for (int i = 1; i <= students.size(); i++) {
            Student student = students.getEntry(i);
            if (student.getName().equalsIgnoreCase(nameToChange) && student.getId().equalsIgnoreCase(idToChange)) {

                Student studentToChange = student;
                System.out.print("Enter new tutorial group name: ");
                String newGroupName = scanner.nextLine();
                TutorialGroup newGroup = findGroupByName(newGroupName);

                if (newGroup != null) {
                    if (newGroup.addStudent(studentToChange)) {
                        group.removeStudent(studentToChange);
                        studentsChangedGroupCount++; // Increment the count
                        System.out.println("Student transferred successfully.");
                    } else {
                        System.out.println("Failed to transfer student. New group may be full.");
                    }
                } else {
                    System.out.println("Target group not found.");
                }
            }
        }
        ttlgrpDAO.saveToFile(tutorialGroups, "TtlGrpStudents.dat");

    }

    public static Student findStudent(Scanner scanner) {
        System.out.print("Enter student name to find: ");
        String nameToFind = scanner.nextLine();
        System.out.print("Enter student ID to find: ");
        String idToFind = scanner.nextLine();

        // You can return a Student object with the entered name and ID
        return new Student(nameToFind, idToFind);
    }

    public static void generateReport(TutorialGroup group) {
        System.out.println("Report for " + group.getGroupName());

        // List of students in the group
        students = group.listStudents();

        if (students.isEmpty()) {
            System.out.println("No students in the group.");
        } else {
            // Print headers
            System.out.println("==============================================================================");
            System.out.printf("%-28s %-15s %-30s%n", "Name", "ID", "Email");
            System.out.println("==============================================================================");

            // Print student information
            for (int i = 1; i <= students.size(); i++) {
                Student student = students.getEntry(i);
                System.out.printf("%-28s %-15s %-30s%n", student.getName(), student.getId(), student.getEmail());
            }
            System.out.println("------------------------------------------------------------------------------");
        }

        System.out.println("Summary :");
        System.out.println("Students Added          : " + studentsAddedCount);
        System.out.println("Students Removed        : " + studentsRemovedCount);
        System.out.println("Students Changed Group  : " + studentsChangedGroupCount);
        System.out.println("==============================================================================");
    }

}
