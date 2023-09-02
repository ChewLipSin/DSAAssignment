/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templat1es/Classes/Class.java to edit this template
 */
package dao;

import adt.*;

import entity.Course;
import entity.CourseProgram;
import entity.Program;
import entity.Tutor;
import java.io.IOException;

/**
 *
 * @author Chew Lip Sin
 * @author Eugene Teoh
 */
public class Initializer {

    //  Method to return a collection of with hard-coded entity values
    public ListInterface<Course> initializeCourse() {
        ListInterface<Course> cList = new ArrList<>();
        cList.add(new Course("BAIT1013", "Introduction of Computer Networks", 4, Course.Sem.JAN));
        cList.add(new Course("BAMS1623", "Discrete Mathematics", 3, Course.Sem.ALL));
        cList.add(new Course("BAMS1054", "Probability and Statistics", 4, Course.Sem.JUL));
        cList.add(new Course("BJEL1713", "English for Tertiary Studies", 3, Course.Sem.JAN));
        cList.add(new Course("BJEL1723", "Academic English", 3, Course.Sem.JUL));
        cList.add(new Course("BACS1053", "Database Management", 4, Course.Sem.ALL));
        cList.add(new Course("BMCS2013", "Data Engineering", 4, Course.Sem.ALL));
        cList.add(new Course("AACS1304", "System Analysis and Design", 3, Course.Sem.JUL));
        cList.add(new Course("BAIT1043", "System Analysis and Design", 3, Course.Sem.JAN));
        cList.add(new Course("AMIT1713", "IT Fundamentals and Applications", 3, Course.Sem.ALL));
        cList.add(new Course("BMIT1723", "IT Fundamentals and Applications", 3, Course.Sem.ALL));
        cList.add(new Course("ABFA1173", "Principles of Accounting ", 3, Course.Sem.ALL));
        cList.add(new Course("BBFA1173", "Principles of Accounting ", 3, Course.Sem.ALL));
        cList.add(new Course("BBMF3203", "Research Method", 3, Course.Sem.ALL));
        cList.add(new Course("MPU-2312", "Civic Consciousness and Volunteerism", 2, Course.Sem.ALL));
        cList.add(new Course("MPU-2212", "Bahasa Kebangsaan A", 2, Course.Sem.ALL));

        return cList;
    }

    public ListInterface<Program> ProgramInitializer() {
        ListInterface<Program> pList = new ArrList<>();
        pList.add(new Program("RSD", "Bachelor Degree", "Bachelor of Information Technology (Honours) in Software Systems Development", "FOCS - Faculty of Computing and Information Technology", "This programme produces and equips graduates with in-depth knowledge and skills that are essential to work as professionals in the software systems development and computer networking sectors."));
        pList.add(new Program("RBA", "Bachelor Degree", "Bachelor of Business Administration (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides students with a well-rounded business education through a core curriculum of management, marketing, finance, accounting, economics and law courses."));
        pList.add(new Program("RAC", "Bachelor Degree", "Bachelor of Accounting (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides a wide spectrum of knowledge and skills required for a career in the accountancy and finance profession."));
        pList.add(new Program("RBF", "Bachelor Degree", "Bachelor of Banking and Finance (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme aims to produce graduates who are equipped with the knowledge and core competencies and dispositions necessary for pursuing a demanding career in the highly competitive domestic and international banking and finance industry."));
        pList.add(new Program("RME", "Bachelor Degree", "Bachelor of Mechanical Engineering with Honours", "FOET - Faculty of Engineering and Technology", "Mechanical engineering is the broadest among all engineering disciplines. Thus, most of the modern-day inventions are due to knowledge and application of mechanical engineering. "));
        return pList;
    }

    public LinkedListInterface<CourseProgram> CourseProgramInitializer() {
        LinkedListInterface<CourseProgram> cP = new DoublyLinkedList<>();
        cP.add(new CourseProgram("BMIT1713", "RSD", false));
        cP.add(new CourseProgram("BBMF3203", "RSD", false));
        cP.add(new CourseProgram("BBMF3203", "RME", false));
        cP.add(new CourseProgram("BAIT1013", "RSD", false));

        return cP;
    }

    public ListInterface<Tutor> initializeTutors() {

        ListInterface<Tutor> ttList = new ArrList<>();
        ttList.add(new Tutor("Ng Yen Phing", "ngyp@tarc.edu.my", 1, "Management Information Systems"));
        ttList.add(new Tutor("Ho Chuk Fong", "hocf@tarc.edu.my", 2, "Accounting & Finance"));
        ttList.add(new Tutor("Chin Wan Yoke", "chinwy@tarc.edu.my", 2, "Banking & Finance"));
        ttList.add(new Tutor("Ong Ting Hao", "ongth@tarc.edu.my", 5, "Exercise Physiology"));
        ttList.add(new Tutor("Goh Ching Pang", "gohcp@tarc.edu.my", 3, "Artificial Intelligence"));
        ttList.add(new Tutor("Lee Fong Yee", "leefy@tarc.edu.my", 4, "Bioscience"));
        return ttList;
    }

}
