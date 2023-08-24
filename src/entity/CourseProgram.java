/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrList;
import adt.ListInterface;

/**
 *
 * @author chewr
 */
public class CourseProgram {

    private ListInterface<Course> courses = new ArrList();
    private ListInterface<Program> programs = new ArrList();

    public void addCourseProgram(Course course, Program program) {
        if (!programs.contains(program)) {
            programs.add(program);
        }

        if (!courses.contains(course)) {
            courses.add(course);
        }
        
        course.addProgram(program);
        program.addCourse(course);
    }

    public ListInterface<Course> getCoursesForProgram(Program program) {
        return program.getCourses();
    }

    public ListInterface<Program> getProgramsForCourse(Course course) {
        return course.getPrograms();
    }
}
