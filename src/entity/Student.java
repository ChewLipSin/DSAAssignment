package entity;

import adt.MyObjects;
import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String id;
    private String email;

    public Student(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name != null ? name : "";
    }

    public String getId() {
        return id != null ? id : "";
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student student = (Student) obj;
        return id.equals(student.id) && name.equals(student.name);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + MyObjects.hashCode(this.name);
        hash = 71 * hash + MyObjects.hashCode(this.id);
        hash = 71 * hash + MyObjects.hashCode(this.email);
        return hash;
    }

    @Override
    public String toString() {
        return getName() + " (" + getId() + ") " + getEmail();
    }

    
}
