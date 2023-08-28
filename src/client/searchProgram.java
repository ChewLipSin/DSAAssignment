package client;

import adt.ArrList;
import adt.ListInterface;
import boundary.ProgramUI;
import control.ProgramMaintenance;
import dao.ProgramDAO;
import entity.Program;
import java.util.Iterator;
import java.util.Scanner;
import utility.ConsoleColor;
import utility.InputValue;
import static utility.MessageUI.printFormattedText;

/**
 * @author Lim Yi Leong
 */
public class searchProgram {

    private ListInterface<Program> pList = new ArrList<>();
    private ProgramMaintenance pM = new ProgramMaintenance();
    private static ProgramDAO pDAO = new ProgramDAO();
    private static ProgramUI pU = new ProgramUI();
    private InputValue iv = new InputValue();

    public searchProgram(ListInterface<Program> pList) {
        Scanner scanner = new Scanner(System.in);
        boolean continueSearching = true;

        while (continueSearching) {
            System.out.print("\n\n");
            System.out.print("Enter a search term: ");
            String searchTerm = iv.readString().toLowerCase();

            String outputStr = "";
            Iterator<Program> iterator = pList.getIterator();
            String header = String.format("%-15s | %-30s | %-80s | %-60s | %s%n",
                    "Program Code", "Program Level", "Program Name", "Faculty", "Description"); // Labels
            String row = "";
            while (iterator.hasNext()) {
                Program program = iterator.next();
                if (matchesSearchTerm(program,searchTerm)) {
                    row = String.format("%-15s | %-30s | %-80s | %-60s | %s%n",
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
                printFormattedText("\nNo matching programs found.", ConsoleColor.BRIGHTRED);
            }
            System.out.print("\n\n");
            printFormattedText("\nDo you want to continue searching? (y/n): ", ConsoleColor.BRIGHTBLUE);
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("y")) {
                continueSearching = false;
            }
        }
    }
    private boolean matchesSearchTerm(Program program, String searchTerm) {
        return program.getCode().toLowerCase().contains(searchTerm)
                || program.getLevel().toLowerCase().contains(searchTerm)
                || program.getName().toLowerCase().contains(searchTerm)
                || program.getFaculty().toLowerCase().contains(searchTerm)
                || program.getDescription().toLowerCase().contains(searchTerm);
    }
}
