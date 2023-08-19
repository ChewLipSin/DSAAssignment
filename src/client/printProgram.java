package client;

import entity.Program;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/**
 *
 * @author Lim Yi Leong
 */
public class printProgram {
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private ArrayList<Program> programList;

    public printProgram(ArrayList<Program> programList) {
        this.programList = programList;
        frame = new JFrame("Program Details");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        if (programList != null && !programList.isEmpty()) {
            Object[][] data = new Object[programList.size()][5];
            String[] columnNames = {"Program Code", "Program Level", "Program Name", "Faculty", "Description"};

            for (int i = 0; i < programList.size(); i++) {
                Program program = programList.get(i);
                data[i][0] = program.getCode();
                data[i][1] = program.getLevel();
                data[i][2] = program.getName();
                data[i][3] = program.getFaculty();
                data[i][4] = program.getDescription();
            }

            table = new JTable(data, columnNames);
            table.setShowGrid(false); 
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow selecting single row
            table.getTableHeader().setFont(new Font("Helvetica", Font.BOLD, 14)); // Header font

            // mouse listener to show popup when a cell is clicked
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int row = table.getSelectedRow();
                    int col = table.getSelectedColumn();
                    if (row >= 0 && col == 2) {
                        String fullSentence = programList.get(row).getName();
                        JOptionPane.showMessageDialog(frame, fullSentence, "Program Name", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (row >= 0 && col == 3) {
                        String fullSentence = programList.get(row).getFaculty();
                        JOptionPane.showMessageDialog(frame, fullSentence, "Faculty", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (row >= 0 && col == 4) {
                        String fullSentence = programList.get(row).getDescription();
                        JOptionPane.showMessageDialog(frame, fullSentence, "Description", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            );

            scrollPane = new JScrollPane(table);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
        } else {
            mainPanel.add(new JLabel("No program details to display."), BorderLayout.CENTER);
        }

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Assuming you have a list of programs from the previous class
        ArrayList<Program> programList = new ArrayList<>();
        // Add some programs to the list for testing
        programList.add(new Program("RSD", "Bachelor Degree", "Bachelor of Information Technology (Honours) in Software Systems Development",  "FOCS - Faculty of Computing and Information Technology", "This programme produces and equips graduates with in-depth knowledge and skills that are essential to work as professionals in the software systems development and computer networking sectors."));
        programList.add(new Program("RAC", "Bachelor Degree", "Bachelor of Accounting (Honours)",  "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides a wide spectrum of knowledge and skills required for a career in the accountancy and finance profession."));

        printProgram display = new printProgram(programList);
    }
}
