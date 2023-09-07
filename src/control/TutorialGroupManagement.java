/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package control;

/**
 *
 * @author Fung Chun Xiang
 */
import adt.*;
import entity.TutorialGroup;
import entity.Student;
import boundary.TutorialGroupManagementUI;
import boundary.FeaturesUI;
import dao.*;
import utility.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TutorialGroupManagement {

    public static ListInterface<TutorialGroup> tutorialGroups = new adt.ArrList<>();
    public static DAO<TutorialGroup> ttlgrpDAO = new DAO<>();

    public TutorialGroupManagement() {
        tutorialGroups = ttlgrpDAO.retrieveFromFile("TtlGrpStudents.dat");
    }

    public void runTutorialGroupManagement() {

        TutorialGroupManagement tutorialGroupManagement = new TutorialGroupManagement();

        Scanner scanner = new Scanner(System.in);

        boolean continueProgram = true;
        while (continueProgram) {
            continueProgram = tutorialGroupManagement(scanner, tutorialGroups);
        }

    }

    private static boolean tutorialGroupManagement(Scanner scanner, ListInterface<TutorialGroup> tutorialGroups) {

        TutorialGroupManagementUI.printHeader();
        TutorialGroupManagementUI.listGroup(tutorialGroups);
        TutorialGroup selectedGroup = TutorialGroupManagementUI.getSelectedTutorialGroup(tutorialGroups);
        if (selectedGroup != null) {

            ListInterface<Student> students = new ArrList();
            while (true) {
                TutorialGroupManagementUI.displayTutorialGroupMenu(selectedGroup);

                int choice;

                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid option.");
                    scanner.nextLine(); // Consume the newline
                    continue;
                }

                switch (choice) {
                    case 1:
                        // Add Student
                        System.out.println();
                        FeaturesUI.addStudent(scanner, selectedGroup);
                        break;

                    case 2:
                        // Remove Student
                        System.out.println();
                        FeaturesUI.removeStudent(scanner, selectedGroup);
                        break;

                    case 3:
                        // List Students
                        System.out.println();
                        students = selectedGroup.listStudents();
                        if (students.isEmpty()) {
                            System.out.println("No students in the group.");
                        } else {
                            System.out.println("List of students in " + selectedGroup.getGroupName() + ":");

                            // Print headers
                            System.out.printf("%-28s %-15s %-30s%n", "Name", "ID", "Email");
                            System.out.println("==============================================================================");

                            // Print student information
                            for (int i = 1; i <= students.size(); i++) {
                                Student s = students.getEntry(i);
                                System.out.printf("%-28s %-15s %-30s%n", s.getName(), s.getId(), s.getEmail());
                            }
                        }
                        break;

                    case 4:
                        // Change Student Tutorial Group
                        System.out.println();
                        FeaturesUI.changeStudentGroup(scanner, selectedGroup);
                        break;

                    case 5:
                        // Find Student
                        System.out.println();
                        Student studentToFind = FeaturesUI.findStudent(scanner);

                        students = selectedGroup.listStudents();
                        boolean found = false;
                        for (int i = 1; i <= students.size(); i++) {
                            Student student = students.getEntry(i);
                            if (student.getName().equalsIgnoreCase(studentToFind.getName()) || 
                                    student.getId().equalsIgnoreCase(studentToFind.getId())) {
                                found = true;
                                System.out.println("Student found in the group:");
                                System.out.println(student); // Display the student information
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("Student not found in the group.");
                        }
                        break;

                    case 6:
                        // Generate Reports
                        System.out.println();
                        FeaturesUI.generateReport(selectedGroup);
                        break;

                    case 0:
                        MessageUI.displayExitMessage();
                        System.out.println();
                        return true;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                if (choice == 0) {
                    break;
                }
            }
        }
        return false;
    }

    public static ListInterface<TutorialGroup> filterTutorialGroups(ListInterface<TutorialGroup> tutorialGroups, String keyword) {
        ListInterface<TutorialGroup> filteredGroups = new ArrList<>();

        for (int i = 1; i <= tutorialGroups.size(); i++) {
            TutorialGroup group = tutorialGroups.getEntry(i);
            if (group.getGroupName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredGroups.add(group);
            }
        }

        return filteredGroups;
    }

    public static TutorialGroup findGroupByName(String groupName) {
        for (int i = 1; i <= tutorialGroups.size(); i++) {
            TutorialGroup group = tutorialGroups.getEntry(i);
            if (group.getGroupName().equalsIgnoreCase(groupName)) {
                return group;
            }
        }
        return null; // Return null if the group is not found
    }
    
//      public static void main(String[] args) {
//        tutorialGroups = Initializer.initializeTutorialGroups();
//            ttlgrpDAO.saveToFile(tutorialGroups, "TtlGrpStudents.dat");
//        TutorialGroupManagement tutorialGroupManagement = new TutorialGroupManagement();
//
//        Scanner scanner = new Scanner(System.in);
//
//        boolean continueProgram = true;
//        while (continueProgram) {
//            continueProgram = tutorialGroupManagement(scanner, tutorialGroups);
//        }
//
//        System.out.println("Exiting the program...");
//        scanner.close();
//    }
}
