/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.InputValue;
import utility.MessageUI;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseProgramMaintenanceUI {
    private InputValue iv = new InputValue();
    public int getMenuChoices() {
        int choice = 0;
        System.out.println("================================================");
        System.out.println("||Course and Program Management Subsystem Menu||");
        System.out.println("================================================");
        System.out.println("1. Add new program to the course");
        System.out.println("2. Remove program from the course");
        System.out.println("0. Exit");
        do {
            System.out.print("Enter choice: ");
            choice = iv.readInteger();
            if (choice > 2 || choice < 0) {
                MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice > 2 || choice < 0);

        return choice;
    }
    
}
