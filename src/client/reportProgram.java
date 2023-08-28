package client;

import adt.ArrList;
import adt.ListInterface;
import dao.TutorialPrDAO;
import entity.Program;
import entity.Tutorial;
import entity.TutorialProgram;
import java.util.Iterator;
import utility.ConsoleColor;
import static utility.MessageUI.printFormattedText;
import utility.Sort;

/**
 * @author Lim Yi Leong
 */
public class reportProgram {

    private ListInterface<TutorialProgram> tpList = new ArrList<>();
    private ListInterface<Program> pList = new ArrList<>();
    private TutorialPrDAO tpDAO = new TutorialPrDAO();
    private Sort sorter = new Sort();

    public reportProgram() {
        tpList = tpDAO.retrieveFromFile();
        listTutorialsByProgramCode();
        sumNStudents(tpList);
        listProgramsAndGroups();

    }
    String hyphenLine = "--------------------------------------------------------------------------";
    String starLine = hyphenLine.replace('-', '*');

    //List tutorial every program
    private void listTutorialsByProgramCode() {
        printFormattedText("\n" + starLine + "\n", ConsoleColor.BRIGHTRED);
        printFormattedText("+-----------------------------------------+\n", ConsoleColor.BRIGHTBLUE);
        printFormattedText("+   List of tutorial group in Programme   +\n", ConsoleColor.CYAN);
        printFormattedText("+-----------------------------------------+\n", ConsoleColor.BRIGHTBLUE);

        try {
            Sort.quickSort(tpList, 1, tpList.size() - 1, "code");
            String tpcode = "";
            Iterator<TutorialProgram> iterator = tpList.getIterator();
            while (iterator.hasNext()) {
                TutorialProgram program = iterator.next();

                if (!program.getCode().equals(tpcode)) {
                    if (!tpcode.isEmpty()) {
                        printTutorialsByProgramCode(tpcode);
                    }
                    tpcode = program.getCode();
                }
            }

            if (!tpcode.isEmpty()) {
                printTutorialsByProgramCode(tpcode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions here
        }
        
        printFormattedText("\n" + hyphenLine + "\n", ConsoleColor.BRIGHTRED);
    }
        //Sum each program's students
    private void sumNStudents(ListInterface<TutorialProgram> tpList) {
        printFormattedText("\n" + starLine + "\n", ConsoleColor.BRIGHTRED);
        printFormattedText("+-----------------------------+\n", ConsoleColor.BRIGHTYELLOW);
        printFormattedText("+ Total Students in Programme +\n", ConsoleColor.YELLOW);
        printFormattedText("+-----------------------------+\n", ConsoleColor.BRIGHTYELLOW);
        sorter.quickSort(tpList, 1, tpList.size() - 1, "code");

        int totalStudents = 0;
        String tpcode = "";
        Iterator<TutorialProgram> iterator = tpList.getIterator();
        while (iterator.hasNext()) {
            TutorialProgram tp = iterator.next();
            if (!tp.getCode().equals(tpcode)) {
                if (!tpcode.isEmpty()) {
                    System.out.println("Total students in " + tpcode + ": " + totalStudents);
                }
                tpcode = tp.getCode();
                totalStudents = tp.getNumStudent();
            } else {
                totalStudents += tp.getNumStudent();
            }
        }
        if (!tpcode.isEmpty()) {
            System.out.println("Total students in " + tpcode + ": " + totalStudents);
        }
        printFormattedText("\n" + hyphenLine + "\n", ConsoleColor.BRIGHTRED);
    }

    //List each programs tutorial groups students
    private void listProgramsAndGroups() {
        printFormattedText("\n" + starLine + "\n", ConsoleColor.BRIGHTRED);
        printFormattedText("+-----------------------------------------+\n", ConsoleColor.BRIGHTBLUE);
        printFormattedText("+ Total Students of Tutorial in Programme +\n", ConsoleColor.CYAN);
        printFormattedText("+-----------------------------------------+\n", ConsoleColor.BRIGHTBLUE);

        try {
            Sort.quickSort(tpList, 1, tpList.size() - 1, "code");

            // Iterate through the list and print each program, group, and number of students
            String tpcode = "";
            String currentGroup = "";
            int groupTotalStudents = 0;

            Iterator<TutorialProgram> iterator = tpList.getIterator();
            while (iterator.hasNext()) {
                TutorialProgram tp = iterator.next();

                if (!tp.getCode().equals(tpcode)) {
                    // Print the tp header
                    if (!tpcode.isEmpty()) {
                        printFormattedText(tpcode + " :\n", ConsoleColor.GREEN);
                        System.out.println("Total students in " + currentGroup + ": " + groupTotalStudents + "\n");
                    }
                    tpcode = tp.getCode();
                    currentGroup = tp.getGroupname();
                    groupTotalStudents = tp.getNumStudent();
                } else if (!tp.getGroupname().equals(currentGroup)) {
                    printFormattedText(tpcode + " :\n", ConsoleColor.GREEN);
                    System.out.println("Total students in " + currentGroup + ": " + groupTotalStudents + "\n");
                    currentGroup = tp.getGroupname();
                    groupTotalStudents = tp.getNumStudent();
                } else {
                    groupTotalStudents += tp.getNumStudent();
                }
            }
            printFormattedText(tpcode + " :\n", ConsoleColor.GREEN);
            System.out.println("Total students in " + currentGroup + ": " + groupTotalStudents + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        printFormattedText("\n" + hyphenLine + "\n", ConsoleColor.BRIGHTRED);
    }

    private void printTutorialsByProgramCode(String programCode) {
        System.out.println("\nTutorials for program " + programCode + ":");

        Iterator<TutorialProgram> iterator = tpList.getIterator();
        while (iterator.hasNext()) {
            TutorialProgram program = iterator.next();
            if (program.getCode().equals(programCode)) {
                System.out.println(program.getGroupname()); // Print the group name or other relevant information
            }
        }

    }
}
