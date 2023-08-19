package client;

import adt.ArrList;
import adt.ArrListInterface;
import entity.Program;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import programme.backButton;

/**
 *
 * @author Lim Yi Leong
 */
public class findProgram {

    private ArrListInterface<Program> programList = new ArrList<>();
    private JFrame frame;
    private JTextField searchField;
    private JButton searchButton, clearButton;
    private JTable table;
    private JScrollPane scrollPane;
    
    JPanel mainPanel = new JPanel(new BorderLayout());

    public findProgram(ArrListInterface<Program> programList) {
        this.programList = programList;
        frame = new JFrame("Search Program");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel barPanel = new JPanel(new BorderLayout());
        backButton BackButton = new backButton("back", "program");
        barPanel.add(BackButton);
        
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
                ArrListInterface<Program> searchResults = new ArrList<>();

                for (int i = 1; i < programList.size() + 1; i++) {
                    Program program = programList.getEntry(i);
                    if (programList.contains(program)&&
                            program.getCode().toLowerCase().contains(searchText)
                            || program.getLevel().toLowerCase().contains(searchText)
                            || program.getName().toLowerCase().contains(searchText)
                            || program.getFaculty().toLowerCase().contains(searchText)) {
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
                displaySearchResults(programList);
            }
        });
        frame.setContentPane(mainPanel);
        frame.add(barPanel,BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void displaySearchResults(ArrListInterface<Program> searchResults) {
        if (scrollPane != null) {
        frame.remove(scrollPane);
    }
    if (searchResults != null && !searchResults.isEmpty()) {
        Object[][] data = new Object[searchResults.size()][5];
        String[] columnNames = {"Program Code", "Program Level", "Program Name", "Faculty", "Description"};

        Iterator<Program> iterator = searchResults.getIterator(); // Use the searchResults iterator
        int row = 0; 
        while (iterator.hasNext()) {
            Program program = iterator.next();
            data[row][0] = program.getCode();
            data[row][1] = program.getLevel();
            data[row][2] = program.getName();
            data[row][3] = program.getFaculty();
            data[row][4] = program.getDescription();
            row++;
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
                        String fullSentence = programList.getEntry(row+1).getName();
                        JOptionPane.showMessageDialog(frame, fullSentence, "Program Name", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (row >= 0 && col == 3) {
                        String fullSentence = programList.getEntry(row+1).getFaculty();
                        JOptionPane.showMessageDialog(frame, fullSentence, "Faculty", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (row >= 0 && col == 4) {
                        String fullSentence = programList.getEntry(row+1).getDescription();
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
        ArrListInterface<Program> programList = new ArrList<>();
        programList.add(new Program("RSD", "Bachelor Degree", "Bachelor of Information Technology (Honours) in Software Systems Development", "FOCS - Faculty of Computing and Information Technology", "This programme produces and equips graduates with in-depth knowledge and skills that are essential to work as professionals in the software systems development and computer networking sectors."));
        programList.add(new Program("RAC", "Bachelor Degree", "Bachelor of Accounting (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides a wide spectrum of knowledge and skills required for a career in the accountancy and finance profession."));

        findProgram search = new findProgram(programList);
    }
}
