/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
//import java.util.Objects;
/**
 *
 * @author Eugene Teoh
 */
public class Tutor implements Serializable, Comparable<Tutor> {

  private String name;
  private String email;
  private int faculty;
  private String profession;
  private final String[] facultyList = {"FACULTY OF COMPUTING AND INFORMATION TECHNOLOGY", "FACULTY OF ACCOUNTANCY, FINANCE AND BUSINESS", 
  "FACULTY OF APPLIED SCIENCES", "FACULTY OF BUILT ENVIRONMENT", "FACULTY OF ENGINEERING AND TECHNOLOGY",
  "FACULTY OF COMMUNICATION & CREATIVE INDUSTRIES","FACULTY OF SOCIAL SCIENCES AND HUMANITIES"};

  public Tutor(){
  }
  
  public Tutor(String name, String email, int faculty, String profession){
      this.name = name;
      this.email = email;
      this.faculty = faculty;
      this.profession = profession;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public int getFaculty() {
    return faculty;
  }

  public void setFaculty(int faculty) {
    this.faculty = faculty;
  }
  
  public String getSfaculty() {
    return facultyList[faculty - 1];
  }
  
  public String[] getFacultyList() {
    return facultyList;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }
  
  @Override
  public int compareTo(Tutor other) {
    return Integer.compare(this.faculty, other.faculty);
  }
    
  @Override
  public String toString() {
      String str = String.format("%-20s %-20s %-50s %-40s", name, email, facultyList[faculty - 1], profession);
    return str;
  }
}
