package control;

import adt.*;
import boundary.TutorManagementUI;
import dao.*;
import entity.*;
import utility.*;

public class TutorManagement {

    public static ListInterface<Tutor> tutorList = new ArrList<>();
    public static DAO<Tutor> tutorDAO = new DAO();
    public static TutorManagementUI tutorUI = new TutorManagementUI();

    public TutorManagement() {
        tutorList = tutorDAO.retrieveFromFile("tutors.dat");
    }

    public void runTutorManagement() {
//        Initializer tt = new Initializer();
//        TutorManagement.tutorList = tt.initializeTutors();
        TutorManagement tutorManagement = new TutorManagement();

        int choice = 0;
        do {
            choice = tutorUI.getMenuChoice();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    addNewTutor();
                    tutorUI.listAllTutors(getAllTutors());
                    break;
                case 2:
                    TutorManagementUI.searchTutor();
                    break;
                case 3:
                    TutorManagementUI.removeTutor();
                    break;
                case 4:
                    TutorManagementUI.editExistTutor();
                    break;
                case 5:
                    tutorUI.listAllTutors(getAllTutors());
                    break;
                case 6:
                    TutorManagementUI.filterTutor();
                    break;
                case 7:
                    TutorManagementUI.generateFacultyReport();
                    break;
                default:
                    MessageUI.displayInvalidMessage();
            }
        } while (choice != 0);
    }

    public void addNewTutor() {
        Tutor newTutor = tutorUI.inputTutorDetails();
        tutorList.add(newTutor);
        tutorDAO.saveToFile(tutorList, "tutors.dat");
    }

    public static int searchTutor(ListInterface<Tutor> list, String target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.getEntry(i + 1).getName().equals(target)) { // Adding 1 to convert to 1-based index
                return i; // Tutor found at index i
            }
        }
        return -1; // Tutor not found
    }

    public String getAllTutors() {
        String outputStr = "";
        for (int i = 1; i <= tutorList.size(); i++) {
            outputStr += tutorList.getEntry(i) + "\n";
        }
        return outputStr;
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

//    public static void main(String[] args) {
//        TutorManagement tutorManagement = new TutorManagement();
//        Initializer tt = new Initializer();
//        TutorManagement.tutorList = tt.initializeTutors();
//        tutorManagement.runTutorManagement();
//        InputValue.closeInput();
//    }
}
