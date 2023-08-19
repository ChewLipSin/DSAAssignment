/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programme;

import entity.Program;

/**
 *
 * @author zyleo
 * @param <P>
 */
public class Programme<P extends Program> implements ProgrammeInterface<P> {
    
    private P[] programArray;
    private int numberOfElements;
    private static final int DEFAULT_CAPACITY = 5;

    public Programme() {
        this(DEFAULT_CAPACITY);
    }

    public Programme(int initialCapacity) {
        numberOfElements = 0;
        programArray = (P[]) new Object[initialCapacity];
    }

    @Override
    public boolean addProgram(P newprgm) {
        if (newprgm == null) {
            return false;
        }
                
        for (int i = 0; i < numberOfElements; i++) {
            if (newprgm.equals(programArray[i])) {
                return false;
            }
        }
        if (isArrayFull()) {
            doubleArray();
        }
        programArray[numberOfElements] = newprgm;
        numberOfElements++;
        return true;
    }

    @Override
    public boolean removeProgram(P exstprgm) {
        for (int i = 0; i < numberOfElements; i++) {
            if (exstprgm.equals(programArray[i])) {
                removeGap(i);
                numberOfElements--;
                return true;
            }
        }

        return false;
    }

    private void removeGap(int index) {
        for (int i = index; i < numberOfElements; i++) {
            programArray[i] = programArray[i + 1];
        }
        programArray[numberOfElements - 1] = null; // Clear the last element
    }

    
    @Override
    public void findProgram(P exstprgm) {
        for (int i = 0; i < numberOfElements; i++) {
            if (exstprgm.equals(programArray[i])) {
                displayResult(programArray[i]);
            }
        }
        System.out.println("Program not found!!");
    }

    private void displayResult(P exstprgm) {
        System.out.println("-------------------------------------------------------");
        System.out.printf("| %-15s | %-30s | %-20s | %-40s | %-20s |%n",
                "Program Code", "Program Name", "Level of Program", "Program Description", "Update Time");
        System.out.println("-------------------------------------------------------");

        System.out.printf("| %-15s | %-30s | %-20s | %-40s | %-20s |%n",
                exstprgm.getCode(), exstprgm.getName(), exstprgm.getLevel(),
                exstprgm.getDescription(), exstprgm.getUpdateTime());

        System.out.println("-------------------------------------------------------");
    }


    @Override
    public void modifyProgram(P exstprgm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listAllProgram() {
        for (int i = 0; i < numberOfElements; i++) {
            displayResult(programArray[i]);
        }
    }

    @Override
    public boolean addTutorialToProgram(P exstgrp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeTutorialFromProgram(P exstgrp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listAllGroup(P exstprgm) {
        for (int i = 0; i < numberOfElements; i++) {
            if (exstprgm.equals(programArray[i])) {
                displayResult(programArray[i]);
            }
        }
        System.out.println("Program not found!!");    }

    @Override
    public void generateReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean isArrayFull() {
        return programArray.length == numberOfElements;
    }
    
    private void doubleArray() {
        P[] oldArray = programArray;
        programArray = (P[]) new Object[2 * oldArray.length];
        System.arraycopy(oldArray, 0, programArray, 0, numberOfElements);
    }
    
    
}
