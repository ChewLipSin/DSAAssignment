package dao;

import adt.*;
import java.io.*;
import java.io.File;
/**
 *
 * @author Lim Yi Leong
 */
public class tDAO<T> {

  
public void saveToFile(ListInterface<T> list, String fileName) throws IOException {
        File file = new File(fileName);
//        if (file.createNewFile()) {
//            System.out.println(fileName + " File Created");
//        } else {
//            System.out.println("File " + fileName + " already exists");
//        }
        try {
            try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file))) {
                ooStream.writeObject(list);
                ooStream.close();
                System.out.println("Data saved to file.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
}

    public ListInterface<T> retrieveFromFile(String fileName) {
        File file = new File(fileName);
        ListInterface<T> list = new ArrList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            list = (ArrList<T>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return list;
        }
    }
}
