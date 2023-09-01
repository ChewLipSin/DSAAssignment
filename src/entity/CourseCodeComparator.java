/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Comparator;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseCodeComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Course c1 = (Course) o1;
        Course c2 = (Course) o2;

        return c1.getCourseCode().compareTo(c2.getCourseCode());
    }

    
}
