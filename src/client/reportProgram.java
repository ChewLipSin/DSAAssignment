package client;

import adt.ArrList;
import adt.ListInterface;
import dao.tDAO;
import entity.Program;
import entity.TutorialProgram;
import java.util.Iterator;
import utility.ConsoleColor;
import static utility.MessageUI.printFormattedText;
import utility.programCodeComparator;
/**
 *
 * @author Lim Yi Leong
 */
public class reportProgram {

    private ListInterface<TutorialProgram> tpList = new ArrList<TutorialProgram>();
    private ListInterface<Program> pList = new ArrList<Program>();
    private final tDAO DAO = new tDAO();
    private final programCodeComparator pCompare = new programCodeComparator(); 

    public reportProgram() {
        this.tpList = DAO.retrieveFromFile("tutorialProgram.dat");
        this.pList = DAO.retrieveFromFile("program.dat");
        listProgramByFaculty(pList);
        listTutorialsByProgramCode(pList);
        sumNStudents(tpList);
        listProgramsAndGroups(pList,tpList);

    }
    String hyphenLine = "--------------------------------------------------------------------------";
    String starLine = hyphenLine.replace('-', '*');

    //List tutorial every program
    private void listProgramByFaculty(ListInterface<Program> pList) {
        printFormattedText("\n" + starLine + "\n", ConsoleColor.BRIGHTRED);
        printFormattedText("+-----------------------------------------+\n", ConsoleColor.BRIGHTGREEN);
        printFormattedText("+   List of tutorial group in Programme   +\n", ConsoleColor.CYAN);
        printFormattedText("+-----------------------------------------+\n", ConsoleColor.BRIGHTGREEN);

        try {
            ArrList.insertionSort(pList, pCompare, "asc");
            String pFaculty = "";
            Iterator<Program> iterator = pList.getIterator();
            while (iterator.hasNext()) {
                Program program = iterator.next();

                if (!program.getFaculty().equals(pFaculty)) {
                    if (!pFaculty.isEmpty()) {
                        printProgramByFaculty(pFaculty);
                    }
                    pFaculty = program.getFaculty();
                }
            }
            if (!pFaculty.isEmpty()) {
                printProgramByFaculty(pFaculty);
            }
        } catch (Exception e) {
        }
        
        printFormattedText("\n" + hyphenLine + "\n", ConsoleColor.BRIGHTRED);
    }
    
    //List tutorial every program
    private void listTutorialsByProgramCode(ListInterface<Program> pList) {
        printFormattedText("\n" + starLine + "\n", ConsoleColor.BRIGHTRED);
        printFormattedText("+-----------------------------------------+\n", ConsoleColor.BRIGHTBLUE);
        printFormattedText("+   List of tutorial group in Programme   +\n", ConsoleColor.GREEN);
        printFormattedText("+-----------------------------------------+\n", ConsoleColor.BRIGHTBLUE);

        try {
            ArrList.insertionSort(pList, pCompare, "asc");
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
           
        }
        
        printFormattedText("\n" + hyphenLine + "\n", ConsoleColor.BRIGHTRED);
    }
        //Sum each program's students
    private void sumNStudents(ListInterface<TutorialProgram> tpList) {
        printFormattedText("\n" + starLine + "\n", ConsoleColor.BRIGHTRED);
        printFormattedText("+-----------------------------+\n", ConsoleColor.BRIGHTYELLOW);
        printFormattedText("+ Total Students in Programme +\n", ConsoleColor.YELLOW);
        printFormattedText("+-----------------------------+\n", ConsoleColor.BRIGHTYELLOW);
        ArrList.insertionSort(pList, pCompare, "asc");

        int totalStudents = 0;
        String tpcode = "";
        Iterator<TutorialProgram> iterator = tpList.getIterator();
        while (iterator.hasNext()) {
            TutorialProgram tp = iterator.next();
            if (!tp.getCode().equals(tpcode)) {
                if (!tpcode.isEmpty()) {
                    System.out.println("\nTotal students in " + tpcode + ": " + totalStudents);
                }
                tpcode = tp.getCode();
                totalStudents = tp.getNumStudent();
            } else {
                totalStudents += tp.getNumStudent();
            }
        }
        if (!tpcode.isEmpty()) {
            System.out.println("\nTotal students in " + tpcode + ": " + totalStudents);
        }
        printFormattedText("\n" + hyphenLine + "\n", ConsoleColor.BRIGHTRED);
    }

    //List each programs tutorial groups students
    private void listProgramsAndGroups(ListInterface<Program> pList, ListInterface<TutorialProgram> tpList) {
        printFormattedText("\n" + starLine + "\n", ConsoleColor.BRIGHTRED);
        printFormattedText("+-----------------------------------------+\n", ConsoleColor.BRIGHTBLUE);
        printFormattedText("+ Total Students of Tutorial in Programme +\n", ConsoleColor.CYAN);
        printFormattedText("+-----------------------------------------+\n", ConsoleColor.BRIGHTBLUE);

        try {
            ArrList.insertionSort(pList, pCompare, "asc");

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
                        System.out.println("\nTotal students in " + currentGroup + ": " + groupTotalStudents + "\n");
                    }
                    tpcode = tp.getCode();
                    currentGroup = tp.getGroupname();
                    groupTotalStudents = tp.getNumStudent();
                } else if (!tp.getGroupname().equals(currentGroup)) {
                    printFormattedText(tpcode + " :\n", ConsoleColor.GREEN);
                    System.out.println("\nTotal students in " + currentGroup + ": " + groupTotalStudents + "\n");
                    currentGroup = tp.getGroupname();
                    groupTotalStudents = tp.getNumStudent();
                } else {
                    groupTotalStudents += tp.getNumStudent();
                }
            }
            printFormattedText(tpcode + " :\n", ConsoleColor.GREEN);
            System.out.println("\nTotal students in " + currentGroup + ": " + groupTotalStudents + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        printFormattedText("\n" + hyphenLine + "\n", ConsoleColor.BRIGHTRED);
    }
     
    private void printProgramByFaculty(String faculty) {
        System.out.println("\nProgramme of " + faculty + " :");

        Iterator<Program> iterator = pList.getIterator();
        while (iterator.hasNext()) {
            Program program = iterator.next();
            if (program.getFaculty().equals(faculty)) {
                System.out.println(program.getCode());
            }
        }

    }
    
    private void printTutorialsByProgramCode(String programCode) {
        System.out.println("\nTutorials for program " + programCode + " :");

        Iterator<TutorialProgram> iterator = tpList.getIterator();
        while (iterator.hasNext()) {
            TutorialProgram program = iterator.next();
            if (program.getCode().equals(programCode)) {
                System.out.println(program.getGroupname());
            }
        }

    }
}
