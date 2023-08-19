/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programme;

/**
 *
 * @author zyleo
 */
public interface ProgrammeInterface<P> {
/*
Add a new programme ; newprgm = new programme
Description: Adds a new academic program to the system.
Pre-condition: The new program is not exist in the system.
Post-condition: The new program is added to the system.
Return: true if successfully add else false
*/
    boolean addProgram(P newprgm);
    
/*
Remove a programme ; exstprgm = existed programme
Description: Removes an existing academic program from the system.
Pre-condition: The program to be removed must exist in the system.
Post-condition: The specified program is removed from the system.
Return: true if successfully add else false
*/
    boolean removeProgram(P exstprgm);
 
/*
Find programme(GET) ; exstprgm = existed programme
Description: Finds and displays details of an existing academic program.
Pre-condition: The program to be found must exist in the system.
Post-condition: None
Return: Program details
*/
    void findProgram(P exstprgm);
    
/*
Amend programme details(SET) ; exstprgm = existed programme
Description: Allows users to update details of an existing academic program.
Pre-condition: The program to be amended must exist in the system.
Post-condition: The program details are updated with the new information.
Return: The updated program details
*/
    void modifyProgram(P exstprgm);
    
/*
List all programmes(GET)
Description: Lists details of all academic programs in the system.
Pre-condition: None
Post-condition: None
Return: List of program details
*/
    void listAllProgram();

/*
Add a tutorial group to a programme ; extgrp = existed tutorial group
Description: Adds a new tutorial group to an existing academic program.
Pre-condition: The program to which the tutorial group is added must exist in the system.
Post-condition: The new tutorial group is added to the specified program.
Return: true if successfully add else false
*/
    boolean addTutorialToProgram(P exstgrp);
    
/*
Remove a tutorial group from a programme ; extgrp = existed tutorial group
Description: Removes a tutorial group from an existing academic program.
Pre-condition: The program from which the tutorial group is removed must exist in the system, and the tutorial group must be part of that program.
Post-condition: The specified tutorial group is removed from the program.
Return: true if successfully add else false
*/
    boolean removeTutorialFromProgram(P exstgrp);
    
/*
List all tutorial groups for a programme ; exstprgm = existed programme
Description: Lists all tutorial groups associated with a specific academic program.
Pre-condition: The program for which tutorial groups are listed must exist in the system.
Post-condition: None
Return: List of tutorial group details
*/
    void listAllGroup(P exstprgm);
    
/*
Generate relevant reports.
Description: Generates relevant reports based on the academic program data.
Pre-condition: None
Post-condition: None
Return: Generated reports
*/
    void generateReport();

}
