package programme;

import control.ProgramMenu;
import control.TutorialPrMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class backButton extends JButton {
    String type;
    public backButton(String label,String type) {
        super(label);
        this.type = type;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current GUI page
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(backButton.this);
                currentFrame.dispose();

                if(type.equals("program")){
                // Navigate back to the previous page
                new ProgramMenu(); // Assuming this creates the main menu page
                }else{
                    new TutorialPrMenu();
                }
            }
        });
    }
}
