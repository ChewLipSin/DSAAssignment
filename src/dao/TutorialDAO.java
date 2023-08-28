package dao;

import adt.*;
import entity.Tutorial;
import entity.TutorialProgram;
import java.io.*;
/**
 * @author Lim Yi Leong
 */
public class TutorialDAO {
  private String fileName = "tutorial.dat"; // For security and maintainability, should not have filename hardcoded here.
  private ListInterface<Tutorial> tList = new ArrList<>();
  
  public void saveToFile(ListInterface<Tutorial> tutorialList) throws IOException {
    this.tList = tutorialList;
      File file = new File(fileName);
        if (file.createNewFile()) {
            System.out.println(fileName + " File Created");
        } else {
            System.out.println("File " + fileName + " already exists");
        }
        try {
            try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file))) {
                ooStream.writeObject(tList);
                System.out.println("Data saved to file.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
            ex.printStackTrace();
        }
  }

  public ListInterface<Tutorial> retrieveFromFile() {
    File file = new File(fileName);
    try {
      ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
      tList = (ArrList<Tutorial>) (oiStream.readObject());
      oiStream.close();
    } catch (FileNotFoundException ex) {
      System.out.println("\nNo such file.");
    } catch (IOException ex) {
      System.out.println("\nCannot read from file.");
    } catch (ClassNotFoundException ex) {
      System.out.println("\nClass not found.");
    } finally {
      return tList;
    }
  }
}
