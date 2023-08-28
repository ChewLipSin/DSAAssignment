package entity;

import java.io.Serializable;
/**
 * @author zyleo
 */
public class TutorialProgram implements Serializable{
    String code;
    String programname;
    String groupname;
    int numStudent;
    String classRap;
    int intakeYear, intakeMonth;

    public TutorialProgram(String code, String programname, String groupname, int numStudent, String classRap, int intakeYear, int intakeMonth) {
        this.code = code;
        this.programname = programname;
        this.groupname = groupname;
        this.numStudent = numStudent;
        this.classRap = classRap;
        this.intakeYear = intakeYear;
        this.intakeMonth = intakeMonth;
    }

    
    public String getCode() {
        return code;
    }

    public String getProgramname() {
        return programname;
    }

    public String getGroupname() {
        return groupname;
    }

    public int getNumStudent() {
        return numStudent;
    }

    public String getClassRap() {
        return classRap;
    }

    public int getIntakeYear() {
        return intakeYear;
    }

    public int getIntakeMonth() {
        return intakeMonth;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setProgramname(String programname) {
        this.programname = programname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public void setNumStudent(int numStudent) {
        this.numStudent = numStudent;
    }

    public void setClassRap(String classRap) {
        this.classRap = classRap;
    }

    public void setIntakeYear(int intakeYear) {
        this.intakeYear = intakeYear;
    }

    public void setIntakeMonth(int intakeMonth) {
        this.intakeMonth = intakeMonth;
    }
    
    
}
