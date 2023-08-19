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
import javax.swing.table.TableCellRenderer;
import programme.backButton;

/**
 *
 * @author Lim Yi Leong
 */
public class modifyProgram {

    private ArrListInterface<Program> programList;
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;

    public modifyProgram(ArrListInterface<Program> programList) {
        this.programList = programList;
        frame = new JFrame("Program Details");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel barPanel = new JPanel(new BorderLayout());
        backButton BackButton = new backButton("Back", "program");
        barPanel.add(BackButton);

        if (programList != null && !programList.isEmpty()) {
            Object[][] data = new Object[programList.size()][6];
            String[] columnNames = {"Program Code", "Program Level", "Program Name", "Faculty", "Description", "Action"};

            Iterator<Program> iterator = programList.getIterator(); // Use the searchResults iterator
            int Irrow = 0;
            while (iterator.hasNext()) {
                Program program = iterator.next();
                data[Irrow][0] = program.getCode();
                data[Irrow][1] = program.getLevel();
                data[Irrow][2] = program.getName();
                data[Irrow][3] = program.getFaculty();
                data[Irrow][4] = program.getDescription();
                data[Irrow][5] = "Modify";
                Irrow++;
            }

            table = new JTable(data, columnNames);
            table.setShowGrid(false);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow selecting single row
            table.getTableHeader().setFont(new Font("Helvetica", Font.BOLD, 14)); // Header font

            table.addMouseListener(new MouseAdapter() {
                @Override
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
                    if (row >= 0 && col == 5) {
                        openModifyWindow(programList.getEntry(row+1), row+1); // Open modify window with the selected program
                    }
                }
            });
            table.getColumn("Action").setCellRenderer(new ButtonRenderer());
            table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));

            scrollPane = new JScrollPane(table);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
        } else {
            mainPanel.add(new JLabel("No program details to display."), BorderLayout.CENTER);
        }

        frame.setContentPane(mainPanel);
        frame.add(barPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.repaint();
    }

    private void openModifyWindow(Program program, int row) {
        ModifyWindow modifyWindow = new ModifyWindow(program, row);
    }

    public static void main(String[] args) {
        ArrListInterface<Program> programList = new ArrList<>();
        programList.add(new Program("RSD", "Bachelor Degree", "Bachelor of Information Technology (Honours) in Software Systems Development", "FOCS - Faculty of Computing and Information Technology", "This programme produces and equips graduates with in-depth knowledge and skills that are essential to work as professionals in the software systems development and computer networking sectors."));
        programList.add(new Program("RAC", "Bachelor Degree", "Bachelor of Accounting (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides a wide spectrum of knowledge and skills required for a career in the accountancy and finance profession."));

        modifyProgram display = new modifyProgram(programList);
    }

    private class ModifyWindow {

        private JFrame modifyFrame;
        private Program program;
        private JTextField codeInput, nameInput;
        private JTextArea descriptionInput;
        private JComboBox levelInput, facultyInput;
        private JButton submitButton;

        public ModifyWindow(Program program, int row) {
            this.program = program;
            modifyFrame = new JFrame("Modify Program");
            modifyFrame.setSize(400, 300);
            modifyFrame.setLocationRelativeTo(null);
            modifyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel mainPanel = new JPanel(new BorderLayout());

            JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel labelC = new JLabel("Program Code : ");
            codeInput = new JTextField(program.getCode());
            inputPanel.add(labelC);
            inputPanel.add(codeInput);

            JLabel labelL = new JLabel("Program Level : ");
            String[] levels = {"Foundation", "Diploma", "Bachelor Degree", "Master", "Doctor of Philosophy"};
            levelInput = new JComboBox(levels);
            levelInput.setSelectedItem(program.getLevel());
            inputPanel.add(labelL);
            inputPanel.add(levelInput);

            JLabel labelN = new JLabel("Program Name : ");
            nameInput = new JTextField(program.getName());
            inputPanel.add(labelN);
            inputPanel.add(nameInput);

            JLabel labelF = new JLabel("Faculty:");
            String[] faculty = {
                "FOCS - Faculty of Computing and Information Technology",
                "FSSH - Faculty of Social Science and Humanities",
                "FOAS - Faculty of Applied Sciences",
                "FOET - Faculty of Engineering and Technology",
                "FAFB - Faculty of Accountancy, Finance & Business",
                "FOBE - Faculty of Build Environment",
                "FCCI - Faculty of Communication & Creative Industries"
            };
            facultyInput = new JComboBox(faculty);
            facultyInput.setSelectedItem(program.getFaculty());
            inputPanel.add(labelF);
            inputPanel.add(facultyInput);

            JLabel labelD = new JLabel("Description:");
            descriptionInput = new JTextArea(program.getDescription());
            descriptionInput.setLineWrap(true);
            inputPanel.add(labelD);
            inputPanel.add(descriptionInput);

            submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newCode = codeInput.getText();
                    String newName = nameInput.getText();
                    String newLevel = (String) levelInput.getSelectedItem();
                    String newFaculty = (String) facultyInput.getSelectedItem();
                    String newDescription = descriptionInput.getText();
                    program.setCode(newCode);
                    program.setName(newName);
                    program.setLevel(newLevel);
                    program.setFaculty(newFaculty);
                    program.setDescription(newDescription);

                    programList.replace(row, program);
                    modifyFrame.dispose();
                }
            });
            mainPanel.add(inputPanel, BorderLayout.CENTER);
            mainPanel.add(submitButton, BorderLayout.SOUTH);
            modifyFrame.setContentPane(mainPanel);
            modifyFrame.setVisible(true);
        }
        
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {

        protected JButton button;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        programList.remove(row);
                        //findProgram search = new findProgram(programList);
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText((value == null) ? "" : value.toString()); // If the value is null, set an empty string as the button's text, otherwise convert the value to a string
            return button;
        }
    }

}
