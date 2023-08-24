/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.CourseMaintenanceUI;
import dao.CourseDAO;
import entity.Course;
import entity.Course.Sem;
import java.util.Iterator;
import utility.*;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseMaintenance {

//    private ListInterface<Course> courseList = new ArrList<>();
    private CourseDAO courseDAO = new CourseDAO();
    private CourseMaintenanceUI courseUI = new CourseMaintenanceUI();
    private Sort s = new Sort();

    public CourseMaintenance(ListInterface<Course> courseList) {
        courseList = courseDAO.retrieveFromFile();
    }

    public CourseMaintenance() {
    }

    public void runCourseMaintenance(ListInterface<Course> courseList) {
        int choice;
        do {
            choice = courseUI.getMenuChoices();
            switch (choice) {

                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    addNewCourse(courseList);
                    courseUI.listAllCourses(getAllCourses(courseList));
                    Command.pressEnterToContinue();
                    break;
                case 2:
                    removeCourse(courseList);
                    courseUI.listAllCourses(getAllCourses(courseList));
                    break;
                case 3:
                    searchCourse(courseList, "search");
                    break;
                case 4:
                    searchCourse(courseList, "ammend");
                    break;
                case 5:
                    courseUI.listAllCourses(getAllCourses(courseList));
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();

            }
        } while (choice != 0);

    }

    private void addNewCourse(ListInterface<Course> courseList) {
        boolean loop = false;
        do {
            boolean newCourseFull, confirm = true;
            Course newCourse = courseUI.inputCourseDetails(courseList);
            newCourseFull = (!newCourse.equals(new Course(null, null, 0, null)));
            if (newCourseFull == true) {
                confirm = askConfirmation(newCourse, "New", "add");
                if (confirm == true) {
                    courseList.add(newCourse);
                    courseDAO.saveToFile(courseList);
                    MessageUI.displaySuccessConfirmationMessage("Added");
                    loop = courseUI.getConfirmationChoice("add", 0);
                }
            }
        } while (loop);
    }

    public String getAllCourses(ListInterface<Course> courseList) {
//        int currentIndex = 0;
//        for (int i = 1; i <= courseList.size(); i++) {
//            outputStr += courseList.getEntry(i) + "\n";
//        }
        String outputStr = "";
        Iterator it = courseList.getIterator();

        while (it.hasNext()) {
//            System.out.println(it.next());
            outputStr += it.next() + "\n";
        }
        return outputStr;
    }

    public boolean askConfirmation(Course newCourse, String val, String val2) {
        courseUI.displayCourse(newCourse, val);
        return courseUI.getConfirmationChoice(val2, 1);
    }

    private void removeCourse(ListInterface<Course> courseList) {
        boolean loop = false;
        do {
            boolean deleteCourse, confirm;
            int courseSelected = courseUI.inputRemoveCode(courseList);
            deleteCourse = (courseSelected > -1);
            if (deleteCourse == true) {
                confirm = askConfirmation(courseList.getEntry(courseSelected), "Remove", "remove");
                if (confirm == true) {
                    courseList.remove(courseSelected);
                    courseDAO.saveToFile(courseList);
                    MessageUI.displaySuccessConfirmationMessage("Removed");
                    Command.pressEnterToContinue();
                    loop = courseUI.getConfirmationChoice("remove", 0);
                }
            }
        } while (loop);
    }

    private void searchCourse(ListInterface<Course> courseList, String val) {
        boolean loop = true;
        do {
            int choice = courseUI.getSearchMenuChoices();
            switch (choice) {
                case 0:
                    loop = false;
                    break;
                case 1:
                    searchCourseCode(courseList, val);
                    break;
                case 2:
                    searchCourseTitle(courseList, val);
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();

            }
        } while (loop);
    }

    public void searchCourseCode(ListInterface<Course> courseList, String val) {
        boolean loop;
        do {
            int found = courseUI.getSearchCourseCode(courseList);
            if (found == -1) {
                MessageUI.displayNotFoundMessage();
            } else {
                courseUI.displayCourseDetailsFounded(courseList, found);
                if (val == "ammend") {
                    ammendCourse(courseList, found);
                }
            }
            loop = courseUI.getConfirmationChoice(val, 0);
        } while (loop == true);

    }

    private void searchCourseTitle(ListInterface<Course> courseList, String val) {
        boolean loop = true;
        ListInterface<Course> courseList2 = new ArrList();
        do {
            int found = courseUI.getSearchCourseTitle(courseList, courseList2);
            if (found != 0) {
                if (found == -1) {
                    MessageUI.displayNotFoundMessage();
                } else if (found == 1) {
                    if (val == "ammend") {
                        int index = courseUI.getCourseAmmend(courseList, courseList2);
                        ammendCourse(courseList, index);
                    } else {
                        courseUI.displayCourseFounded(courseList2);
                    }
                }
                courseList2.clear();
                loop = courseUI.getConfirmationChoice(val, 0);
            }else if(found == 0){
                loop = false;
            }
        } while (loop);
    }

    private void ammendCourse(ListInterface<Course> courseList, int found) {
        int choice = 0;
        Course tempCourse = courseList.getEntry(found + 1);
        String newCourseCode = tempCourse.getCourseCode();
        String newTitle = tempCourse.getTitle();
        Sem newSem = tempCourse.getSemester();
        int newCreditHours = tempCourse.getCreditHours();
//        do {
        do {
            choice = courseUI.getAmmendMenuChoices();

            switch (choice) {
                case 0:
                    break;
                case 1:
                    newCourseCode = courseUI.inputCourseCode(courseList);
                    if (newCourseCode.equals("0")) {
                        newCourseCode = tempCourse.getCourseCode();
                    }
                    break;
                case 2:
                    newTitle = courseUI.inputTitle();
                    if (newTitle.equals("0")) {
                        newTitle = tempCourse.getTitle();
                    }
                    break;
                case 3:
                    newCreditHours = courseUI.inputCreditHour();
                    if (newCreditHours == 0) {
                        newCreditHours = tempCourse.getCreditHours();
                    }
                    break;
                case 4:
                    newSem = courseUI.inputSemester();
                    if (newSem == null) {
                        newSem = tempCourse.getSemester();
                    }
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
        tempCourse = new Course(newCourseCode, newTitle, newCreditHours, newSem);
        boolean match = courseList.contains(tempCourse);
        if (!match) {
            boolean confirm = askConfirmation(tempCourse, "Ammend", "ammend");
            if (confirm == true) {
                courseList.replace(found + 1, tempCourse);
                courseDAO.saveToFile(courseList);
                MessageUI.displaySuccessConfirmationMessage("Ammend");
                System.out.println(courseList);
                Command.pressEnterToContinue();
//                    loop = courseUI.getConfirmationChoice("ammend", 0);
            }
        } else {
            MessageUI.printFormattedText("No changing\n", ConsoleColor.YELLOW);
        }
//        } while (loop);

    }

}
