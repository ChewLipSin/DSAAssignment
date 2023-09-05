/**
 *
 * @author fungc
 */
package boundary;

import adt.ArrList; // Make sure to import the correct ArrayList class
import adt.ListInterface;
import control.TutorialGroupManagement;
import entity.TutorialGroup;
import entity.Student;

import java.util.Scanner;

public class TutorialGroupManagementUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void listGroup(ListInterface<TutorialGroup> tutorialGroups) {
        if (tutorialGroups.isEmpty()) {
            System.out.println("No tutorial groups available.");
            return;
        }
        
        System.out.println("All Available Tutorial Groups:");
        System.out.println("====================================");
        System.out.printf("%-5s %-15s %s%n", "No.", "Group Name", "Total Students");
        System.out.println("------------------------------------");

        int index = 1;
        for (int i = 1; i <= tutorialGroups.size(); i++) {
            TutorialGroup group = tutorialGroups.getEntry(i);
            ListInterface<Student> students = group.listStudents();
            System.out.printf("%-5d %-15s %d%n",
                    index, group.getGroupName(), students.size());
            
            index++;
        }
        System.out.println("------------------------------------");
    }
    
    public static TutorialGroup getSelectedTutorialGroup(ListInterface<TutorialGroup> tutorialGroups) {

        System.out.println("Enter programme code to filter tutorial groups.(leave blank to skip)");
        System.out.print(" (e.g. RDS) --> ");
        String keyword = scanner.nextLine();
        ListInterface<TutorialGroup> filteredGroups = TutorialGroupManagement.filterTutorialGroups(tutorialGroups, keyword);
        if (!filteredGroups.isEmpty()) {
            System.out.println("Filtered tutorial groups:");
            listGroup(filteredGroups);
            System.out.println(); // Add an empty line for better readability
        }

        int selectedGroupIndex;
        while (true) {
            System.out.print("Select a tutorial group (enter No. [0 - exit]): ");
            String input = scanner.nextLine();

            if (input.matches("\\d+")) {
                selectedGroupIndex = Integer.parseInt(input);
                if (selectedGroupIndex == 0 || (selectedGroupIndex >= 1 && selectedGroupIndex <= filteredGroups.size())) {
                    break; // Exit the loop if valid index or 0 is entered
                } else {
                    System.out.println("Invalid group selection. Please enter a valid index or 0 to exit.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }

        if (selectedGroupIndex == 0) {
            return null;
        }

        return filteredGroups.getEntry(selectedGroupIndex);  // Use filteredGroups here
    }

    public static void displayTutorialGroupMenu(TutorialGroup group) {
        System.out.println("\nSelected Tutorial Group: " + group.getGroupName());
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. List Students");
        System.out.println("4. Change Student Tutorial Group");
        System.out.println("5. Find Student");
        System.out.println("6. Generate Reports");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    public static int promptInt(String message) {
        // Your input handling code here
        System.out.print(message);
        return scanner.nextInt();
    }

    public static String promptString(String message) {
        // Your input handling code here
        System.out.print(message);
        return scanner.nextLine();
    }
    
    public static boolean promptContinue() {
        System.out.print("Do you want to continue to all groups list? (yes/no): ");
        String continueChoice = scanner.nextLine();
        return continueChoice.equalsIgnoreCase("yes");
    }
}

