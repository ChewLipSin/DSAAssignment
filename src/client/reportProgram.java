/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.ArrList;
import adt.ArrListInterface;
import entity.Program;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 *
 * @author zyleo
 */
public class reportProgram {
        private ArrListInterface<Program> programList = new ArrList<>();
        
    public reportProgram() {
        
//        //Task 1: Sum each program's students
//        Map<String, Integer> programStudentCounts = new HashMap<>();
//        for (TutorialProgram program : programs) {
//            String programCode = program.getCode();
//            int numStudents = program.getNumStudent();
//            programStudentCounts.put(programCode, programStudentCounts.getOrDefault(programCode, 0) + numStudents);
//        }

//        // Task 2: List top 5 programs with more tutorial groups
//        List<Map.Entry<String, Integer>> topProgramsByGroups = new ArrayList<>(programStudentCounts.entrySet());
//        topProgramsByGroups.sort((a, b) -> b.getValue().compareTo(a.getValue())); // Sort in descending order
//        System.out.println("Top 5 programs by tutorial groups:");
//        for (int i = 0; i < Math.min(5, topProgramsByGroups.size()); i++) {
//            System.out.println(topProgramsByGroups.get(i).getKey() + ": " + topProgramsByGroups.get(i).getValue() + " groups");
//        }
//
//        // Task 3: List top 5 tutorials with more students
//        Map<String, Integer> tutorialStudentCounts = new HashMap<>();
//        for (TutorialProgram program : programs) {
//            String tutorial = program.getProgramname() + " - " + program.getGroupname();
//            int numStudents = program.getNumStudent();
//            tutorialStudentCounts.put(tutorial, tutorialStudentCounts.getOrDefault(tutorial, 0) + numStudents);
//        }
//
//        List<Map.Entry<String, Integer>> topTutorialsByStudents = new ArrayList<>(tutorialStudentCounts.entrySet());
//        topTutorialsByStudents.sort((a, b) -> b.getValue().compareTo(a.getValue())); // Sort in descending order
//        System.out.println("\nTop 5 tutorials by students:");
//        for (int i = 0; i < Math.min(5, topTutorialsByStudents.size()); i++) {
//            System.out.println(topTutorialsByStudents.get(i).getKey() + ": " + topTutorialsByStudents.get(i).getValue() + " students");
//        }
    }

}
