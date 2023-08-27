/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseProgram implements Serializable {

    private String courseCode;
    private String programCode;
    private boolean isElective;

    public CourseProgram(String courseCode, String programCode, boolean isElective) {
        this.courseCode = courseCode;
        this.programCode = programCode;
        this.isElective = isElective;
    }

    public CourseProgram() {
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public boolean isIsElective() {
        return isElective;
    }

    public void setIsElective(boolean isElective) {
        this.isElective = isElective;
    }

}
