package client;

import adt.ArrList;
import adt.ArrListInterface;
import dao.ProgramDAO;
import entity.TutorialProgram;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.TableCellRenderer;
import programme.backButton;
/**
 *
 * @author Lim Yi Leong
 */
public class deleteTutorialInProgram {
    private ArrListInterface<TutorialProgram> tutorialGroupList = new ArrList<>();
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;

    public deleteTutorialInProgram(ArrListInterface<TutorialProgram> tutorialGroupList) {
        this.tutorialGroupList = tutorialGroupList;
        frame = new JFrame("Tutorial Details In Program");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel barPanel = new JPanel(new BorderLayout());
        backButton BackButton = new backButton("Back","course");
        barPanel.add(BackButton);
        
        if (tutorialGroupList != null && !tutorialGroupList.isEmpty()) {
            Object[][] data = new Object[tutorialGroupList.size()][6];
            String[] columnNames = {"Program Code", "Program Name", "Tutorial Group", "Number Of Students", "Class Rap Name", "Intake", "Action"};
            for (int i = 1; i < tutorialGroupList.size()+1; i++) {
                TutorialProgram tp = tutorialGroupList.getEntry(i);
                data[i-1][0] = tp.getCode();
                data[i-1][1] = tp.getProgramname();
                data[i-1][2] = tp.getGroupname();
                data[i-1][3] = tp.getNumStudent();
                data[i-1][4] = tp.getClassRap();
                data[i-1][5] = tp.getIntakeYear() + tp.getIntakeMonth();
                data[i-1][6] = "Delete";
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
                    if (row >= 0 && col == 1) {
                        String fullSentence = tutorialGroupList.getEntry(row).getProgramname();
                        JOptionPane.showMessageDialog(frame, fullSentence, "Program Name", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (row >= 0 && col == 2) {
                        String fullSentence = tutorialGroupList.getEntry(row).getGroupname();
                        JOptionPane.showMessageDialog(frame, fullSentence, "Tutorial Group", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (row >= 0 && col == 4) {
                        String fullSentence = tutorialGroupList.getEntry(row).getClassRap();
                        JOptionPane.showMessageDialog(frame, fullSentence, "Class Rap Name", JOptionPane.INFORMATION_MESSAGE);
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

        mainPanel.add(barPanel,BorderLayout.SOUTH);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ArrListInterface<TutorialProgram> tutorialGroupList = new ArrList<>(); 
        //tutorialGroupList.add(new Program("RSD", "Bachelor Degree", "Bachelor of Information Technology (Honours) in Software Systems Development", "FOCS - Faculty of Computing and Information Technology", "This programme produces and equips graduates with in-depth knowledge and skills that are essential to work as professionals in the software systems development and computer networking sectors."));
        //tutorialGroupList.add(new Program("RAC", "Bachelor Degree", "Bachelor of Accounting (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides a wide spectrum of knowledge and skills required for a career in the accountancy and finance profession."));

        deleteTutorialInProgram display = new deleteTutorialInProgram(tutorialGroupList);
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        //setText((value == null) ? "" : value.toString()) sets the text of the button. If the value is null, it sets an empty string as the button's text. 
        //Otherwise, it converts the value to a string and sets it as the button's text.
        @Override
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
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        tutorialGroupList.remove(row+1);
                        refreshTable();
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

    private void refreshTable() {
        table.revalidate();
        table.repaint();
    }
}
