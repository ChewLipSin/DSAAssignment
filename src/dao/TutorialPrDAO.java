package dao;

import adt.*;
import entity.TutorialProgram;
import java.io.*;
/**
 *
 * @author Lim Yi Leong
 */
public class TutorialPrDAO {
  private String fileName = "tutorialProgram.dat"; // For security and maintainability, should not have filename hardcoded here.
  
  public void saveToFile(ArrListInterface<TutorialProgram> tutorialGroupList) {
    File file = new File(fileName);
    try {
      ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
      ooStream.writeObject(tutorialGroupList);
      ooStream.close();
    } catch (FileNotFoundException ex) {
      System.out.println("\nFile not found");
    } catch (IOException ex) {
      System.out.println("\nCannot save to file");
    }
  }

  public ArrListInterface<TutorialProgram> retrieveFromFile() {
    File file = new File(fileName);
    ArrListInterface<TutorialProgram> tutorialGroupList = new ArrList<>();
    try {
      ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
      tutorialGroupList = (ArrList<TutorialProgram>) (oiStream.readObject());
      oiStream.close();
    } catch (FileNotFoundException ex) {
      System.out.println("\nNo such file.");
    } catch (IOException ex) {
      System.out.println("\nCannot read from file.");
    } catch (ClassNotFoundException ex) {
      System.out.println("\nClass not found.");
    } finally {
      return tutorialGroupList;
    }
  }
  private void createFileIfNotExists() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File created at: " + file.getAbsolutePath());
            } catch (IOException ex) {
                System.out.println("\nCannot create file.");
            }
        }
    }
}
