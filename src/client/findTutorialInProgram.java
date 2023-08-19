package client;

import adt.ArrListInterface;
import adt.ArrList;
import entity.Program;
import entity.TutorialProgram;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import programme.backButton;
/**
 *
 * @author Lim Yi Leong
 */
public class findTutorialInProgram {
    private ArrListInterface<TutorialProgram> tutorialGroupList = new ArrList<>();
    private JFrame frame;
    private JTextField searchField;
    private JButton searchButton, clearButton;
    private JTable table;
    private JScrollPane scrollPane;

    public findTutorialInProgram(ArrListInterface<TutorialProgram> tutorialGroupList) {
        this.tutorialGroupList = tutorialGroupList;
        frame = new JFrame("Search Tutorial");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel barPanel = new JPanel(new BorderLayout());
        backButton BackButton = new backButton("Back","course");
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
                
                // Create an empty list to hold the search results; cannot use loop
                ArrListInterface<TutorialProgram> searchResults = new ArrList<>();

                for (int i = 1; i < tutorialGroupList.size(); i++) {
                    TutorialProgram TP = tutorialGroupList.getEntry(i);
                    if (tutorialGroupList.contains(TP) && TP.getCode().toLowerCase().contains(searchText) ||
                            TP.getGroupname().toLowerCase().contains(searchText) ||
                            TP.getClassRap().toLowerCase().contains(searchText) ||
                            TP.getProgramname().toLowerCase().contains(searchText)) {
                        searchResults.add(TP);
                    }
                }

                displaySearchResults(searchResults);
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setText("");
                displaySearchResults(new ArrList<>());
            }
        });
        mainPanel.add(barPanel,BorderLayout.SOUTH);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    private void displaySearchResults(ArrListInterface<TutorialProgram> searchResults) {
        if (scrollPane != null) {
            frame.remove(scrollPane);
        }

        Object[][] data = new Object[searchResults.size()][5];
        String[] columnNames = {"Program Code", "Program Name", "Tutorial Group", "Number Of Students", "Class Rap Name", "Intake"};

        Iterator<TutorialProgram> iterator = searchResults.getIterator(); // Use the searchResults iterator
        int row = 0; 
        while (iterator.hasNext()) {
            TutorialProgram tp = iterator.next();
            data[row][0] = tp.getCode();
            data[row][1] = tp.getProgramname();
            data[row][2] = tp.getGroupname();
            data[row][3] = tp.getNumStudent();
            data[row][4] = tp.getClassRap();
            data[row][5] = tp.getIntakeYear()+tp.getIntakeMonth();
            row++;
        }

        table = new JTable(data, columnNames);
        table.setShowGrid(false);
        table.getTableHeader().setFont(new Font("Helvetica", Font.BOLD, 14));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                if (row >= 0 && col == 1) {
                    String fullSentence = tutorialGroupList.getEntry(row).getProgramname();
                    JOptionPane.showMessageDialog(frame, fullSentence, "Program Name", JOptionPane.INFORMATION_MESSAGE);
                }
                if (row >= 0 && col == 2) {
                    String fullSentence = tutorialGroupList.getEntry(row).getGroupname();
                    JOptionPane.showMessageDialog(frame, fullSentence, "Tutorial Group", JOptionPane.INFORMATION_MESSAGE);
                }
                //click show student list
                if (row >= 0 && col == 4) {
                    String fullSentence = tutorialGroupList.getEntry(row).getClassRap();
                    JOptionPane.showMessageDialog(frame, fullSentence, "Class Rap Name", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.revalidate();
    }
        
    public static void main(String[] args) {
        ArrListInterface<Program> programList = new ArrList<>(); 
        programList.add(new Program("RSD", "Bachelor Degree", "Bachelor of Information Technology (Honours) in Software Systems Development", "FOCS - Faculty of Computing and Information Technology", "This programme produces and equips graduates with in-depth knowledge and skills that are essential to work as professionals in the software systems development and computer networking sectors."));
        programList.add(new Program("RAC", "Bachelor Degree", "Bachelor of Accounting (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides a wide spectrum of knowledge and skills required for a career in the accountancy and finance profession."));

        //tutorialProgram
        findProgram search = new findProgram(programList);
    }
    
}
