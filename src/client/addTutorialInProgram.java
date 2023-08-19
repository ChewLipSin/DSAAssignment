package client;

import adt.ArrListInterface;
import adt.ArrList;
import dao.ProgramDAO;
import dao.TutorialPrDAO;
import entity.Program;
import entity.TutorialProgram;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import programme.backButton;
/**
 *
 * @author Lim Yi Leong
 */
public class addTutorialInProgram {
    private ArrListInterface<Program> programList = new ArrList<>();
    private ArrListInterface<TutorialProgram> tutorialGroupList = new ArrList<>();
    private TutorialPrDAO tpDAO = new TutorialPrDAO();
    //tutorial array
    private JComboBox<String> codeInput, groupInput;
    private JTextField programNameInput, numStudInput, nameRapInput;
    private JSpinner yearIntake, monthIntake;

    public addTutorialInProgram() {
        JFrame frame = new JFrame("Add Tutorial Group");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //main>input>intakePanel+buttonPanel
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(50, 70, 50, 70));
        
        JLabel labelC = new JLabel("Select the program code : *");
        codeInput = new JComboBox<>();
        for (int i = 0; i < programList.size(); i++) {
            Program program = programList.getEntry(i);
            codeInput.addItem(program.getCode());
        }
        inputPanel.add(labelC);
        inputPanel.add(codeInput);
        
        JLabel labelG = new JLabel("Select the group name : *");
        groupInput = new JComboBox<>();
        //tutorial class
        for (int i = 0; i < programList.size(); i++) {
            Program program = programList.getEntry(i);
            codeInput.addItem(program.getCode());
        }
        inputPanel.add(labelG);
        inputPanel.add(groupInput);
        
        JLabel labelS = new JLabel("Enter number of students : ");
        NumberFormatter formatter = new NumberFormatter();
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(30);
        numStudInput = new JFormattedTextField(formatter);

        JLabel labelR = new JLabel("Enter name of the class rap : *");
        nameRapInput = new JTextField();
        inputPanel.add(labelR);
        inputPanel.add(nameRapInput);
        
        //intake
        JPanel inputYMPanel = new JPanel(new GridLayout(1,3));
        JLabel labelT = new JLabel("Enter the intake session (year/month) : *");
        SpinnerNumberModel year = new SpinnerNumberModel(2023, 2020, 2025, 1);
        yearIntake = new JSpinner(year);
        inputPanel.add(labelT);
        inputYMPanel.add(yearIntake);
        
        SpinnerNumberModel month = new SpinnerNumberModel(1, 1, 12, 1);  // Create the month input using JSpinner
        monthIntake = new JSpinner(month);
        inputYMPanel.add(monthIntake);
        inputPanel.add(inputYMPanel);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        JButton addButton = new JButton("Add");
        inputPanel.add(new JLabel());
        buttonPanel.add(addButton);
        backButton BackButton = new backButton("Back","course");
        buttonPanel.add(BackButton);
        inputPanel.add(buttonPanel);
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String programCode = (String) codeInput.getSelectedItem();
                String programName = null;
                for (int i = 0; i < programList.size(); i++) {
                    Program program = programList.getEntry(i);
                    if (program.getCode().equals(programCode)) {
                        programName = program.getCode();
                    }
                }
                String groupName = (String)groupInput.getSelectedItem();
                int numStudent = Integer.parseInt(numStudInput.getText());
                String classRapName = nameRapInput.getText();
                int intakeYear = (int) yearIntake.getValue();
                int intakeMonth = (int) monthIntake.getValue();
                
                if (!groupName.isEmpty() && !programCode.isEmpty() && !classRapName.isEmpty()) {
                    if (isGroupNameUnique(groupName) && isProgramCodeValid(programCode)) {
                        TutorialProgram tutorialGroup = new TutorialProgram(programCode, programName, groupName, numStudent, classRapName, intakeYear, intakeMonth);
                        tutorialGroupList.add(tutorialGroup);
                        tpDAO.saveToFile(tutorialGroupList);
                        
                        numStudInput.setText("");
                        nameRapInput.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Group name already exists or program code is invalid.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please fill in all required fields.");
                }
            }
        });
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
    
    private boolean isGroupNameUnique(String groupName) {
        for (int i = 0; i < tutorialGroupList.size(); i++) {
            TutorialProgram tutorialGroup = tutorialGroupList.getEntry(i);
            if (tutorialGroup.getGroupname().equals(groupName)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isProgramCodeValid(String programCode) {
        for (int i = 0; i < tutorialGroupList.size(); i++) {
            TutorialProgram tutorialGroup = tutorialGroupList.getEntry(i);
            if (tutorialGroup.getCode().equals(programCode)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        addTutorialInProgram addTutorialGroup = new addTutorialInProgram();
    }
}
