package entity;

import adt.ArrList;
import adt.ListInterface;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Lim Yi Leong
 */
public class Program implements Serializable {

    private String code;
    private String level;
    private String name;
    private String faculty;
    private String description;
    private LocalDateTime updateTime;
    private ListInterface<Course> courses = new ArrList();
    public Program() {
    }

    public Program(String code, String name) {
        this.code = code;
        this.name = name;
        this.updateTime = LocalDateTime.now();
    }

    public Program(String code, String level, String name, String faculty, String description) {
        this.code = code;
        this.level = level;
        this.name = name;
        this.faculty = faculty;
        this.description = description;
        this.updateTime = LocalDateTime.now();
    }

    public String getCode() {
        return code;
    }

    public String getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public ListInterface<Course> getCourses() {
        return courses;
    }
    
    public void addCourse(Course course){
        courses.add(course);
        course.getPrograms().add(this);
    }
    
    public void removeCourse(Course course){
        courses.remove(course);
        course.getPrograms().remove(this);
    }
}
