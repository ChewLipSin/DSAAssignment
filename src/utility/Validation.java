
package utility;

/**
 *
 * @author fungc
 */
import entity.TutorialGroup;
import entity.Student;
import adt.*;

public class Validation {
    
    public static boolean isValidStudentID(String studentID) {
        if (studentID.length() == 10) {
            String prefix = studentID.substring(0, 2);
            String middle = studentID.substring(2, 5);
            String suffix = studentID.substring(5);

            // Check if prefix and suffix are numeric, and middle is alphabetical
            if (prefix.matches("\\d{2}") && (middle.equals("WMR") || middle.equals("WMD")) && suffix.matches("\\d{5}")) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isValidEmail(String email) {
        // Regular expression pattern for the email format
        String pattern = "[a-zA-Z0-9\\-]+@student\\.tarc\\.edu\\.my";
        return email.matches(pattern);
    }
    
    public static boolean isStudentIDUsed(String studentID, ListInterface<TutorialGroup> tutorialGroups, ListInterface<Student> students) {

        for (int i = 1; i <= tutorialGroups.size(); i++) {
            TutorialGroup group = tutorialGroups.getEntry(i);
            for (int j = 1; j <= students.size(); j++) {
                Student student = students.getEntry(j);
                if (student.getId().equalsIgnoreCase(studentID)) {
                    return true;
                }
            }
        }
        return false;
    }
}

