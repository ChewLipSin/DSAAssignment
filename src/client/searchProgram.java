package client;

import adt.ArrList;
import adt.ListInterface;
import boundary.ProgramUI;
import control.ProgramMaintenance;
import entity.Program;
import java.util.Iterator;
import java.util.Scanner;
import utility.ConsoleColor;
import utility.InputValue;
import static utility.MessageUI.printFormattedText;
import entity.programCodeComparator;

/**
 *
 * @author Lim Yi Leong
 */
public class searchProgram {

    private ListInterface<Program> pList = new ArrList<Program>();
    private final ProgramMaintenance pM = new ProgramMaintenance();
    private final programCodeComparator pCompare = new programCodeComparator();
    private final ProgramUI pU = new ProgramUI();
    private final InputValue iv = new InputValue();

    public searchProgram(ListInterface<Program> pList) {
        Scanner scanner = new Scanner(System.in);

        boolean continueSearching = true;

        while (continueSearching) {
            System.out.print("\n\nEnter a code : ");
            String searchTerm = scanner.nextLine().toLowerCase();

            String outputStr = "";
            Iterator<Program> iterator = pList.getIterator();

            String header = String.format("%-15s | %-30s | %-80s | %-60s | %s%n",
                    "Program Code", "Program Level", "Program Name", "Faculty", "Description");

            while (iterator.hasNext()) {
                Program program = iterator.next();
                if (program.getCode().toLowerCase().equals(searchTerm)) {
                    String row = String.format("%-15s | %-30s | %-80s | %-60s | %s%n",
                            program.getCode(),
                            program.getLevel(),
                            program.getName(),
                            program.getFaculty(),
                            program.getDescription());
                    outputStr += row;
                }
            }

            if (!outputStr.isEmpty()) {
                System.out.println("\nSearch Results :\n");
                System.out.print(header);
                System.out.println(outputStr);
            } else {
                System.out.println("\nNo matching programs found.");
            }

            System.out.print("\nDo you want to continue searching? (y=yes): ");
            String choice = scanner.nextLine().toLowerCase();
            if (!choice.equals("y")) {
                continueSearching = false;
            }
        }
    }

    private boolean matchesSearchTerm(ListInterface<Program> pList, String searchTerm) {
        for (int index = 1; index < pList.size(); index++) {
            Program program = pList.getEntry(index);
            if (program.getCode().equals(searchTerm)) {
                return true;
            }
            if (program.getName().equals(searchTerm)) {
                return true;
            }
            if (program.getLevel().equals(searchTerm)) {
                return true;
            }
            if (program.getFaculty().equals(searchTerm)) {
                return true;
            }
            if (program.getDescription().equals(searchTerm)) {
                return true;
            }
        }
        return false;
    }
}
