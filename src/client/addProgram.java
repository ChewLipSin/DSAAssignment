package client;
import programme.backButton;
import adt.ArrList;
import adt.ArrListInterface;
import dao.ProgramDAO;
import entity.Program;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Lim Yi Leong
 */
public class addProgram{
    private ArrListInterface<Program> programList = new ArrList<>();
    private ProgramDAO programDAO = new ProgramDAO();
    private JTextField codeInput, nameInput, descriptionInput;
    private JComboBox<String> levelInput, facultyInput;

    public addProgram() {
        JFrame frame = new JFrame("Add Program");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 15, 15));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(50, 70, 50, 70)); 
        
        JLabel labelC = new JLabel("Enter the program code : *");
        codeInput = new JTextField();
        inputPanel.add(labelC);
        inputPanel.add(codeInput);

        JLabel labelN = new JLabel("Enter the program name : *");
        nameInput = new JTextField();
        inputPanel.add(labelN);
        inputPanel.add(nameInput);

        JLabel labelL = new JLabel("Select the program level : ");
        String[] levels = {"Foundation", "Diploma", "Bachelor Degree", "Master", "Doctor of Philosophy"};
        levelInput= new JComboBox(levels);
        inputPanel.add(labelL);
        inputPanel.add(levelInput);
        
        JLabel labelF = new JLabel("Select the faculty of program : ");
        String[] faculty = {
            "FOCS - Faculty of Computing and Information Technology", 
            "FSSH - Faculty of Social Science and Humanities", 
            "FOAS - Faculty of Applied Sciences",
            "FOET - Faculty of Engineering and Technology",
            "FAFB - Faculty of Accountancy, Finance & Business",
            "FOBE - Faculty of Build Environment",
            "FCCI - Faculty of Communication & Creative Industries"
        };
        facultyInput= new JComboBox(faculty);
        inputPanel.add(labelF);
        inputPanel.add(facultyInput);
        
        JLabel labelD = new JLabel("Enter the program description : *");
        descriptionInput = new JTextField();
        inputPanel.add(labelD);
        inputPanel.add(descriptionInput);

        JButton addButton = new JButton("Add");
        inputPanel.add(addButton);
        
        backButton BackButton = new backButton("Back","program");
        inputPanel.add(BackButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = codeInput.getText().toUpperCase();
                String name = nameInput.getText().toUpperCase();
                String level = (String)levelInput.getSelectedItem();
                String faculty = (String)facultyInput.getSelectedItem();
                String descpt = descriptionInput.getText().toLowerCase();
                
                if (!code.isEmpty() && !name.isEmpty() && !descpt.isEmpty()) {
                    if (isCodeUnique(code) && isNameUnique(name)) {
                        Program program = new Program(code, name, level, faculty, descpt);
                        programList.add(program);
                        programDAO.saveToFile(programList);
                        
                        codeInput.setText("");
                        nameInput.setText("");
                        descriptionInput.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Code and/or name already exist.");
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
    private boolean isCodeUnique(String code) {
        for (int i = 0; i < programList.size(); i++) {
            Program program = programList.getEntry(i);
            if (program.getCode().equals(code)) {
                return false;
            }
        }
        return true; // Code is unique
    }

    private boolean isNameUnique(String name) {
        for (int i = 0; i < programList.size(); i++) {
            Program program = programList.getEntry(i);
            if (program.getName().equals(name)) {
                return false;
            }
        }
        return true; // Name is unique
    }
    public static void main(String[] args) {
        addProgram addProg = new addProgram();
    }
}
