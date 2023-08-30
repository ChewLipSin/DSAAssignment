package dao;

import adt.*;
import entity.Program;
import java.io.*;
import java.io.File;
/**
 * @author Lim Yi Leong
 */
public class ProgramDAO {
  private static String fileName = "program.dat"; // For security and maintainability, should not have filename hardcoded here.
  private ListInterface<Program> pList = new ArrList<>();
  
public void saveToFile(ListInterface<Program> programList) throws IOException {
        this.pList = programList;
        File file = new File(fileName);
//        if (file.createNewFile()) {
//            System.out.println(fileName + " File Created");
//        } else {
//            System.out.println("File " + fileName + " already exists");
//        }
        try {
            try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file))) {
                ooStream.writeObject(programList);
                System.out.println("Data saved to file.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
            ex.printStackTrace();
        }
}

    public ListInterface<Program> retrieveFromFile() {
        File file = new File(fileName);
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            pList = (ArrList<Program>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return pList;
        }
    }
}
