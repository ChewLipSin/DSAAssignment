package entity;

import adt.ArrList;
import adt.ListInterface;
import java.io.Serializable;

public class TutorialGroup implements Serializable{

    private String groupName;
    private ListInterface<Student> students; // Use the ListInterface provided by your ADT

    public TutorialGroup(String groupName) {
        this.groupName = groupName;
        this.students = new ArrList<>();
    }

    public boolean addStudent(Student student) {
        return students.add(student);
    }

    public boolean removeStudent(Student student) {
        return students.remove(student);
    }

    public boolean containsStudent(Student student) {
        return students.contains(student);
    }

    /*    public boolean replaceStudent(Student oldStudent, Student newStudent) {
    return students.replace(oldStudent, newStudent);
    }*/

    public String getGroupName() {
        return groupName != null ? groupName : "";
    }

    public ArrList<Student> listStudents() {
        return (ArrList<Student>) students; // Return the ArrayList directly
    }
}
