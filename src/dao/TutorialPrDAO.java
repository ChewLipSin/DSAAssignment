package dao;

import adt.*;
import entity.TutorialProgram;
import java.io.*;
import java.io.File;
/**
 * @author Lim Yi Leong
 */
public class TutorialPrDAO {
  private String fileName = "tutorialProgram.dat"; // For security and maintainability, should not have filename hardcoded here.
  private ListInterface<TutorialProgram> tpList = new ArrList<>();
  
  public void saveToFile(ListInterface<TutorialProgram> tutorialGroupList) throws IOException {
    this.tpList = tutorialGroupList;
      File file = new File(fileName);
//        if (file.createNewFile()) {
//            System.out.println(fileName + " File Created");
//        } else {
//            System.out.println("File " + fileName + " already exists");
//        }
        try {
            try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file))) {
                ooStream.writeObject(tutorialGroupList);
                System.out.println("Data saved to file.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
            ex.printStackTrace();
        }
  }

  public ListInterface<TutorialProgram> retrieveFromFile() {
    File file = new File(fileName);
    try {
      ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
      tpList = (ArrList<TutorialProgram>) (oiStream.readObject());
      oiStream.close();
    } catch (FileNotFoundException ex) {
      System.out.println("\nNo such file.");
    } catch (IOException ex) {
      System.out.println("\nCannot read from file.");
    } catch (ClassNotFoundException ex) {
      System.out.println("\nClass not found.");
    } finally {
      return tpList;
    }
  }
}
