package dao;

import adt.*;
import entity.Program;
import java.io.*;
/**
 *
 * @author Lim Yi Leong
 */
public class ProgramDAO {
  private String fileName = "program.dat"; // For security and maintainability, should not have filename hardcoded here.
  
  public void saveToFile(ArrListInterface<Program> programList) {
    
    File file = new File(fileName);
    try {
      ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
      ooStream.writeObject(programList);
      ooStream.close();
    } catch (FileNotFoundException ex) {
      System.out.println("\nFile not found");
    } catch (IOException ex) {
      System.out.println("\nCannot save to file");
    }
  }

  public ArrListInterface<Program> retrieveFromFile() {
    File file = new File(fileName);
    ArrListInterface<Program> programList = new ArrList<>();
    try {
      ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
      programList = (ArrList<Program>) (oiStream.readObject());
      oiStream.close();
    } catch (FileNotFoundException ex) {
      System.out.println("\nNo such file.");
    } catch (IOException ex) {
      System.out.println("\nCannot read from file.");
    } catch (ClassNotFoundException ex) {
      System.out.println("\nClass not found.");
    } finally {
      return programList;
    }
  }
    private void createFileIfNotExists() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                if(file.createNewFile()){
                System.out.println("File created at: " + file.getAbsolutePath());
                }else
                    System.out.println("Fail");
            } catch (IOException ex) {
                System.out.println("\nCannot create file.");
            }
        }
    }
}
