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
public class MainMaintenanceUI {

    InputValue iv = new InputValue();

    public int getMenuChoices() {
        int choice = 0;
        System.out.println("====================================");
        System.out.println("||           Main Menu            ||");
        System.out.println("====================================");
        System.out.println("1. Program Management Subsystem");
        System.out.println("2. Tutor Management Subsystem Menu");
        System.out.println("3. Tutorial Group Management Subsystem Menu");
        System.out.println("4. Course Management Subsystem Menu");
        System.out.println("0. Exit");
        do {
            System.out.print("Enter choice: ");
            choice = iv.readInteger();
            if (choice > 4 || choice < 0) {
                MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice > 4 || choice < 0);

        return choice;
    }

}
