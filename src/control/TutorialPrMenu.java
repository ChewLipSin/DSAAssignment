package control;

import adt.ArrList;
import adt.ArrListInterface;
import client.addTutorialInProgram;
import client.deleteTutorialInProgram;
import client.findTutorialInProgram;
import dao.TutorialPrDAO;
import entity.TutorialProgram;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author Lim Yi Leong
 */
public class TutorialPrMenu {
private ArrListInterface<TutorialProgram> tutorialGroupList = new ArrList<>();
    private TutorialPrDAO tpDAO = new TutorialPrDAO();
    private JFrame frame;
    private JTextField chInput;
    public TutorialPrMenu() {
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

        JLabel titleLabel = new JLabel("<html><h1>Tutorial in Programme Page</h1><hr></html>");
        titlePanel.add(titleLabel);
        JLabel menuLabel = new JLabel("<html>"
                + "<p>1. Add Tutorial Group in Programme</p>"
                + "<p>2. Find Tutorial Group in Programme</p>"
                + "<p>3. Modify Program Details</p>"
                + "<p>4. Delete Tutorial Group in Programme</p>"
                + "<p>5. Back to Programme Page</p></html>");
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
        tutorialGroupList = tpDAO.retrieveFromFile();
        String choiceText = chInput.getText();
        int choice = Integer.parseInt(choiceText);

        switch (choice) {
            case 1:
                new addTutorialInProgram();
                frame.dispose();
                break;
            case 2:
                new findTutorialInProgram(tutorialGroupList);
                frame.dispose();
                break;
            case 3:
                new deleteTutorialInProgram(tutorialGroupList);
                frame.dispose();
                break;
            case 4:
                //go to tutorial 
                //new deleteTutorialInProgram(tutorialGroupList);
                //frame.dispose();
                break;
            case 5:
                new ProgramMenu();
                frame.dispose();
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Invalid choice. Please enter a valid option.");
        }
    }
    }
    
