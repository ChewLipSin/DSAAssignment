package client;

import adt.ArrList;
import adt.ArrListInterface;
import dao.ProgramDAO;
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
public class deleteProgram {
    private ArrListInterface<Program> programList = new ArrList<>();
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;

    public deleteProgram(ArrListInterface<Program> programList) {
        this.programList = programList;
        frame = new JFrame("Program Details");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel barPanel = new JPanel(new BorderLayout());
        backButton BackButton = new backButton("Back","program");
        barPanel.add(BackButton);
        
        if (programList != null && !programList.isEmpty()) {
            Object[][] data = new Object[programList.size()][6];
            String[] columnNames = {"Program Code", "Program Level", "Program Name", "Faculty", "Description", "Action"};

            Iterator<Program> iterator = programList.getIterator();
            int row = 0;
            while (iterator.hasNext()) {
            Program program = iterator.next();
                data[row][0] = program.getCode();
                data[row][1] = program.getLevel();
                data[row][2] = program.getName();
                data[row][3] = program.getFaculty();
                data[row][4] = program.getDescription();
                data[row][5] = "Delete";
                row++;
            }

            table = new JTable(data, columnNames);
            table.setShowGrid(false);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow selecting single row
            table.getTableHeader().setFont(new Font("Helvetica", Font.BOLD, 14)); // Header font

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
            });

            // renders the delete button
            table.getColumn("Action").setCellRenderer(new ButtonRenderer());
            // handles button clicks and deletion of the corresponding row from the programList.
            table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));

            scrollPane = new JScrollPane(table);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
        } else {
            mainPanel.add(new JLabel("No program details to display."), BorderLayout.CENTER);
        }

        frame.setContentPane(mainPanel);
        frame.add(barPanel,BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ArrListInterface<Program> programList = new ArrList<>(); 
        programList.add(new Program("RSD", "Bachelor Degree", "Bachelor of Information Technology (Honours) in Software Systems Development", "FOCS - Faculty of Computing and Information Technology", "This programme produces and equips graduates with in-depth knowledge and skills that are essential to work as professionals in the software systems development and computer networking sectors."));
        programList.add(new Program("RAC", "Bachelor Degree", "Bachelor of Accounting (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides a wide spectrum of knowledge and skills required for a career in the accountancy and finance profession."));

        deleteProgram display = new deleteProgram(programList);
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        //setText((value == null) ? "" : value.toString()) sets the text of the button. If the value is null, it sets an empty string as the button's text. 
        //Otherwise, it converts the value to a string and sets it as the button's text.
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Custom cell editor for handling button clicks in the "Action" column
    private class ButtonEditor extends DefaultCellEditor {

        protected JButton button;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        programList.remove(row+1);
                        deleteProgram display = new deleteProgram(programList);
                    }
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText((value == null) ? "" : value.toString()); // If the value is null, set an empty string as the button's text, otherwise convert the value to a string
            return button;
        }
    }

}
