/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import control.TutorManagement;
import entity.Tutor;
import dao.*;
import java.util.Scanner;
import utility.InputValue;

/**
 *
 * @author Eugene Teoh
 */
public class TutorManagementUI {

    Scanner scanner = new Scanner(System.in);

    public static int getMenuChoice() {
        System.out.println("\n- MAIN MENU -");
        System.out.println("1. Add New Tutor");
        System.out.println("2. Search Tutor");
        System.out.println("3. Remove Existing Tutor");
        System.out.println("4. Edit Tutor Info");
        System.out.println("5. List All Tutors");
        System.out.println("6. Filter Tutor");
        System.out.println("7. Report");
        System.out.println("0. Quit");
        int choice = InputValue.inputInt("Enter Choice: ");
        System.out.println();
        return choice;
    }

    public static void listAllTutors(String outputTutor) {
        System.out.println("\nList of Tutors: \n" + outputTutor);
    }

    public static void printTutorDetails(Tutor tutor) {
        System.out.println("Tutor Informations");
        System.out.println("Tutor name:" + tutor.getName());
        System.out.println("Tutor Email: " + tutor.getEmail());
        System.out.println("Faculty: " + tutor.getFaculty());
        System.out.println("Profession: " + tutor.getProfession());
    }

    public static String inputTutorName() {
        String name = InputValue.enterString("Enter Tutor Name: ");
        return name;
    }

    public static String inputTutorEmail() {
        String email;
        do {
            email = InputValue.enterString("Enter tutor Email: ");
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email.");
            }
        } while (!isValidEmail(email));
        return email;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    public static int chooseFaculty() {
        System.out.print("Choose tutor Faculty: \n");
        System.out.println("1. FACULTY OF COMPUTING AND INFORMATION TECHNOLOGY");
        System.out.println("2. FACULTY OF ACCOUNTANCY, FINANCE AND BUSINESS");
        System.out.println("3. FACULTY OF APPLIED SCIENCES");
        System.out.println("4. FACULTY OF BUILT ENVIRONMENT");
        System.out.println("5. FACULTY OF ENGINEERING AND TECHNOLOGY");
        System.out.println("6. FACULTY OF COMMUNICATION & CREATIVE INDUSTRIES");
        System.out.println("7. FACULTY OF SOCIAL SCIENCES AND HUMANITIES");
        int choice = InputValue.intChoice("Enter Choice : ", 1, 7);
        System.out.println();
        return choice;
    }

    public static String inputProfession() {
        String profession = InputValue.enterString("Enter tutor profession: ");
        return profession;
    }

    public static Tutor inputTutorDetails() {
        String tutorName = inputTutorName();
        String tutorEmail = inputTutorEmail();
        int faculty = chooseFaculty();
        String profession = inputProfession();
        System.out.println();
        return new Tutor(tutorName, tutorEmail, faculty, profession);
    }

    public static String searchTutorDetails(Tutor tutor) {
        System.out.println("Searching Tutor details : " + tutor.getName());
        System.out.println("Name: " + tutor.getName());
        System.out.println("Email: " + tutor.getEmail());
        System.out.println("Faculty: " + tutor.getSfaculty());
        System.out.println("Profession: " + tutor.getProfession());

        return null;
    }

    public static void editExistTutor() {

        String target = InputValue.enterString("Enter the tutor name to edit: ");

        int resultIndex = TutorManagement.searchTutor(TutorManagement.tutorList, target);

        if (resultIndex != -1) {
            Tutor existingTutor = TutorManagement.tutorList.getEntry(resultIndex + 1); // Adding 1 to convert to 1-based index
            editTutorDetail(existingTutor);
            TutorManagement.tutorDAO.saveToFile(TutorManagement.tutorList, "tutors.dat");
        } else {
            System.out.println("Tutor not found.");
        }
    }

    public static String editTutorDetail(Tutor tutor) {
        System.out.println("Editing Tutor details : " + tutor.getName());
        System.out.println("Name: " + tutor.getName());
        System.out.println("Email: " + tutor.getEmail());
        System.out.println("Faculty: " + tutor.getSfaculty());
        System.out.println("Profession: " + tutor.getProfession());
        String newName = InputValue.inputString("Enter new Name (leave blank to keep unchanged): ");
        String newEmail;
        do {
            newEmail = InputValue.inputString("Enter new Email (leave blank to keep unchanged): ");
            if (!newEmail.isEmpty() && !isValidEmail(newEmail)) {
                System.out.println("Invalid email format. Please enter a valid email.");
            }
        } while (!newEmail.isEmpty() && !isValidEmail(newEmail));
        System.out.println("Current Faculty: " + tutor.getFaculty());
        System.out.println("Choose a new Faculty:");
        System.out.println("1. FACULTY OF COMPUTING AND INFORMATION TECHNOLOGY");
        System.out.println("2. FACULTY OF ACCOUNTANCY, FINANCE AND BUSINESS");
        System.out.println("3. FACULTY OF APPLIED SCIENCES");
        System.out.println("4. FACULTY OF BUILT ENVIRONMENT");
        System.out.println("5. FACULTY OF ENGINEERING AND TECHNOLOGY");
        System.out.println("6. FACULTY OF COMMUNICATION & CREATIVE INDUSTRIES");
        System.out.println("7. FACULTY OF SOCIAL SCIENCES AND HUMANITIES");
        int newFaculty = InputValue.intChoice("Enter Choice (leave blank to keep unchanged): ", 1, 7);
        String newProfession = InputValue.inputString("Enter new Profession (leave blank to keep unchanged): ");

        if (!newName.isEmpty()) {
            tutor.setName(newName);
        }
        if (!newEmail.isEmpty()) {
            tutor.setEmail(newEmail);
        }
        if (newFaculty != 0) {
            tutor.setFaculty(newFaculty);
        }
        if (!newProfession.isEmpty()) {
            tutor.setProfession(newProfession);
        }

        System.out.println("Tutor details updated.");
        return "Tutor details updated.";
    }

    public static void searchTutor() {
        String target = InputValue.enterString("Enter the tutor name to search: ");

        int resultIndex = TutorManagement.searchTutor(TutorManagement.tutorList, target);

        if (resultIndex != -1) {
            Tutor findTutor = TutorManagement.tutorList.getEntry(resultIndex + 1); // Adding 1 to convert to 1-based index
            searchTutorDetails(findTutor);
        } else {
            System.out.println("Tutor not found.");
        }
    }

    public static void generateFacultyReport() {

        TutorManagement.tutorList.bubbleSort();
        String currentFaculty = "";
        for (int i = 1; i <= TutorManagement.tutorList.size(); i++) {
            Tutor tutor = TutorManagement.tutorList.getEntry(i);
            if (!tutor.getSfaculty().equals(currentFaculty)) {
                currentFaculty = tutor.getSfaculty();
                System.out.println("Faculty: " + currentFaculty);
                System.out.println("----------------------");
            }

            System.out.println("Tutor Name: " + tutor.getName());
            System.out.println("Email: " + tutor.getEmail());
            System.out.println("Profession: " + tutor.getProfession());
            System.out.println();
        }
    }

    public static void removeTutor() {
        String target = InputValue.enterString("Enter the tutor name to remove: ");

        int resultIndex = TutorManagement.searchTutor(TutorManagement.tutorList, target);

        if (resultIndex != -1) {
            Tutor tutorRemove = TutorManagement.tutorList.getEntry(resultIndex + 1); // Adding 1 to convert to 1-based index

            deleteTutor(tutorRemove);

            System.out.print("Are you sure to remove this tutor? (y/n): ");
            char confirmation = InputValue.inputYN();
            if (confirmation == 'y') {
                TutorManagement.tutorList.remove(resultIndex + 1); // Remove the tutor from the list
                TutorManagement.tutorDAO.saveToFile(TutorManagement.tutorList, "tutors.dat"); // Save the updated list
                System.out.println("Tutor removed successfully.");
            } else {
                System.out.println("Tutor removal canceled.");
            }
        } else {
            System.out.println("Tutor not found.");
        }
    }

    public static void filterTutor() {
        TutorManagement.tutorList.bubbleSort();
        char targetAlphabet = InputValue.inputChar("Enter the target alphabet: ");
        boolean found = false;

        System.out.println("Tutors with names starting with '" + targetAlphabet + "':");

        for (int i = 1; i <= TutorManagement.tutorList.size(); i++) {
            Tutor tutor = TutorManagement.tutorList.getEntry(i);
            if (Character.toLowerCase(tutor.getName().charAt(0)) == Character.toLowerCase(targetAlphabet)) {
                System.out.println("Name: " + tutor.getName());
                System.out.println("Email: " + tutor.getEmail());
                System.out.println("Faculty: " + tutor.getSfaculty());
                System.out.println("Profession: " + tutor.getProfession());
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tutors found with names starting with '" + targetAlphabet + "'.");
        }
    }

    public static String deleteTutor(Tutor tutor) {
        System.out.println("Deleting Tutor: " + tutor.getName());
        System.out.println("Name: " + tutor.getName());
        System.out.println("Email: " + tutor.getEmail());
        System.out.println("Faculty: " + tutor.getSfaculty());
        System.out.println("Profession: " + tutor.getProfession());
        return null;
    }

}
