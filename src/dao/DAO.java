package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import adt.*;
import java.io.*;
import java.io.File;

/**
 *
 * @author Chew Lip Sin
 */
public class DAO<T> {

//course = course.dat
//program = program.dat
//courseProgram = courseProgram.dat
    public void saveToFile(ListInterface<T> list, String fileName) {
        File file = new File(fileName);
//        if (file.createNewFile()) {
//            System.out.println(fileName + " File Created");
//        } else {
//            System.out.println("File " + fileName + " already exists");
//        }
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(list);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public void saveToFile(LinkedListInterface<T> list, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName); ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(list);

//            System.out.println("DoublyLinkedList saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ListInterface<T> retrieveFromFile(String fileName){
        File file = new File(fileName);
        ListInterface<T> list = new ArrList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            list = (ArrList<T>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return list;
        }
    }

    public LinkedListInterface<T> dLLRetrieveFromFile(String fileName) {
        File file = new File(fileName);
        LinkedListInterface<T> list = new DoublyLinkedList<>();
        try (FileInputStream fileIn = new FileInputStream(fileName); ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            list = (DoublyLinkedList<T>) objectIn.readObject();

            // Now you can use the retrievedList object as your DoublyLinkedList
//            System.out.println("DoublyLinkedList retrieved from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
