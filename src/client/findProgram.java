package client;

import adt.ArrList;
import adt.ListInterface;
import entity.Program;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Lim Yi Leong
 */
public class findProgram {
    private ListInterface<Program> programList = new ArrList<>();
    private JFrame frame;
    private JTextField searchField;
    private JButton searchButton, clearButton;
    private JTable table;
    private JScrollPane scrollPane;
    
    JPanel mainPanel = new JPanel(new BorderLayout());
    public findProgram(ListInterface<Program> programList) {
        this.programList = programList;
        frame = new JFrame("Search Program");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        clearButton = new JButton("Clear");
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);

        mainPanel.add(searchPanel, BorderLayout.NORTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().trim().toLowerCase();
                List<Program> searchResults = new ArrayList<>();

                for (int i = 0; i <= programList.size(); i++) {
                    Program program = programList.getEntry(i);
                    if (program.getCode().toLowerCase().contains(searchText) ||
                            program.getLevel().toLowerCase().contains(searchText) ||
                            program.getName().toLowerCase().contains(searchText) ||
                            program.getFaculty().toLowerCase().contains(searchText)) {
                        searchResults.add(program);
                    }
                }

                displaySearchResults(searchResults);
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setText("");
                displaySearchResults(new ArrayList<>());
            }
        });
        
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    private void displaySearchResults(List<Program> searchResults) {
        if (scrollPane != null) {
            frame.remove(scrollPane);
        }

        if (programList != null && !programList.isEmpty()) {
        Object[][] data = new Object[searchResults.size()][5];
        String[] columnNames = {"Program Code", "Program Level", "Program Name", "Faculty", "Description"};

        for (int i = 0; i < searchResults.size(); i++) {
            Program program = searchResults.get(i);
            data[i][0] = program.getCode();
            data[i][1] = program.getLevel();
            data[i][2] = program.getName();
            data[i][3] = program.getFaculty();
            data[i][4] = program.getDescription();
        }

        table = new JTable(data, columnNames);
        table.setShowGrid(false);
        table.getTableHeader().setFont(new Font("Helvetica", Font.BOLD, 14));

        // mouse listener to show popup when a cell is clicked
        table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int row = table.getSelectedRow();
                    int col = table.getSelectedColumn();
                    if (row >= 0 && col == 2) {
                        String fullSentence = programList.getEntry(row).getName();
                        JOptionPane.showMessageDialog(frame, fullSentence, "Program Name", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (row >= 0 && col == 3) {
                        String fullSentence = programList.getEntry(row).getFaculty();
                        JOptionPane.showMessageDialog(frame, fullSentence, "Faculty", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (row >= 0 && col == 4) {
                        String fullSentence = programList.getEntry(row).getDescription();
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
        
        scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.revalidate();
    }

    public static void main(String[] args) {
        ListInterface<Program> programList = new ArrList<>(); 
        programList.add(new Program("RSD", "Bachelor Degree", "Bachelor of Information Technology (Honours) in Software Systems Development", "FOCS - Faculty of Computing and Information Technology", "This programme produces and equips graduates with in-depth knowledge and skills that are essential to work as professionals in the software systems development and computer networking sectors."));
        programList.add(new Program("RAC", "Bachelor Degree", "Bachelor of Accounting (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides a wide spectrum of knowledge and skills required for a career in the accountancy and finance profession."));

        findProgram search = new findProgram(programList);
    }
}
