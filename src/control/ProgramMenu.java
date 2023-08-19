package control;

import adt.ArrList;
import adt.ArrListInterface;
import client.addProgram;
import client.deleteProgram;
import client.findProgram;
import client.modifyProgram;
import dao.ProgramDAO;
import dao.TutorialPrDAO;
import entity.Program;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramMenu {
    private ArrListInterface<Program> programList = new ArrList<>();
    private ProgramDAO programDAO = new ProgramDAO();
    private TutorialPrDAO tpDAO = new TutorialPrDAO();
    private JFrame frame;
    private JTextField chInput;

    public ProgramMenu() {
        frame = new JFrame("Programme Menu");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        Font menuFont = new Font("Arial", Font.PLAIN, 16);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel();
        JPanel menuPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 30)); // Adds some padding

        JLabel titleLabel = new JLabel("<html><h1>Programme Menu</h1><hr></html>");
        titlePanel.add(titleLabel);
        JLabel menuLabel = new JLabel("<html>"
                + "<p>1. Add New Program</p>"
                + "<p>2. Find Program Details</p>"
                + "<p>3. Modify Program Details</p>"
                + "<p>4. Delete Program</p>"
                + "<p>5. Course Service</p></html>");
        menuLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // Padding for menu labels

        JLabel labelC = new JLabel("Enter your choice: *");
        chInput = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)); // Aligns components at center
        inputPanel.add(labelC);
        inputPanel.add(chInput);
        inputPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMenuChoice();
            }
        });
        menuLabel.setFont(menuFont);
        titleLabel.setFont(titleFont);
        submitButton.setFont(menuFont);

        menuPanel.add(menuLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    private void handleMenuChoice() {
        programList = programDAO.retrieveFromFile();
        String choiceText = chInput.getText();
        int choice = Integer.parseInt(choiceText);

        switch (choice) {
            case 1:
                new addProgram();
                frame.dispose();
                break;
            case 2:
                new findProgram(programList);
                frame.dispose();
                break;
            case 3:
                new modifyProgram(programList);
                frame.dispose();
                break;
            case 4:
                new deleteProgram(programList);
                frame.dispose();
                break;
            case 5:
                new TutorialPrMenu();
                frame.dispose();
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Invalid choice. Please enter a valid option.");
        }
    }

}
