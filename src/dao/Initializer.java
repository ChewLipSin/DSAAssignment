/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templat1es/Classes/Class.java to edit this template
 */
package dao;

import adt.*;

import entity.Course;
import entity.CourseProgram;
import entity.Program;
import entity.Student;
import entity.Tutor;
import entity.Tutorial;
import entity.TutorialGroup;
import entity.TutorialProgram;
import java.io.IOException;

/**
 *
 * @author Chew Lip Sin
 * @author Eugene Teoh
 * @author Fung Chun Xiang
 * @author Lim Yi Leong
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

    public static ListInterface<TutorialGroup> initializeTutorialGroups() {
        ListInterface<TutorialGroup> tutorialGroups = new ArrList<>();

        tutorialGroups.add(new TutorialGroup("RDS2S1G1"));
        tutorialGroups.add(new TutorialGroup("RAC2S1G1"));
        tutorialGroups.add(new TutorialGroup("RDS2S1G2"));
        tutorialGroups.add(new TutorialGroup("RDS2S1G3"));
        tutorialGroups.add(new TutorialGroup("RAC2S1G2"));
        tutorialGroups.add(new TutorialGroup("RSW2S1G1"));

        tutorialGroups.getEntry(1).addStudent(new Student("Fung Chun Xiang", "23WMR09149", "fungcx-pm21@student.tarc.edu.my"));
        tutorialGroups.getEntry(1).addStudent(new Student("Eugene Teoh Kai Siang", "23WMR09147", "eugenetks-pm21@student.tarc.edu.my"));
        tutorialGroups.getEntry(2).addStudent(new Student("Tee Wen Xin", "23WMR08480", "teewx-wm21@student.tarc.edu.my"));
        tutorialGroups.getEntry(3).addStudent(new Student("Khor Jinn Zhi", "23WMR09176", "khorjz-pm21@student.tarc.edu.my"));
        tutorialGroups.getEntry(3).addStudent(new Student("Lee Tze Siaen", "23WMR09179", "leets-pm21@student.tarc.edu.my"));

        return tutorialGroups;
    }

    //  Method to return a collection of with hard-coded entity values
    public ListInterface<Tutorial> TutorialInitializer() {
        ListInterface<Tutorial> tList = new ArrList<>();
        tList.add(new Tutorial("RSDG1"));
        tList.add(new Tutorial("RBAG1"));
        tList.add(new Tutorial("RACG1"));
        tList.add(new Tutorial("RBFG1"));
        tList.add(new Tutorial("RMEG1"));
        tList.add(new Tutorial("RSDG2"));
        tList.add(new Tutorial("RBAG2"));
        tList.add(new Tutorial("RACG2"));
        tList.add(new Tutorial("RBFG2"));
        tList.add(new Tutorial("RMEG2"));

        return tList;
    }

    public static void inputTutorial() {
        Initializer t = new Initializer();
        ListInterface<Tutorial> tList = t.TutorialInitializer();

        // Save the program data to the file using ProgramDAO
        DAO DAO = new DAO();
        DAO.saveToFile(tList, "tutorial.dat");

        System.out.println("\nProgram: \n" + tList);
    }

    //  Method to return a collection of with hard-coded entity values
    public ListInterface<Program> ProgramInitializer() {
        ListInterface<Program> pList = new ArrList<>();
        pList.add(new Program("RSD", "Bachelor Degree", "Bachelor of Information Technology (Honours) in Software Systems Development", "FOCS - Faculty of Computing and Information Technology", "This programme produces and equips graduates with in-depth knowledge and skills that are essential to work as professionals in the software systems development and computer networking sectors."));
        pList.add(new Program("RBA", "Bachelor Degree", "Bachelor of Business Administration (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides students with a well-rounded business education through a core curriculum of management, marketing, finance, accounting, economics and law courses."));
        pList.add(new Program("RAC", "Bachelor Degree", "Bachelor of Accounting (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides a wide spectrum of knowledge and skills required for a career in the accountancy and finance profession."));
        pList.add(new Program("RBF", "Bachelor Degree", "Bachelor of Banking and Finance (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme aims to produce graduates who are equipped with the knowledge and core competencies and dispositions necessary for pursuing a demanding career in the highly competitive domestic and international banking and finance industry."));
        pList.add(new Program("RME", "Bachelor Degree", "Bachelor of Mechanical Engineering with Honours", "FOET - Faculty of Engineering and Technology", "Mechanical engineering is the broadest among all engineering disciplines. Thus, most of the modern-day inventions are due to knowledge and application of mechanical engineering."));
        pList.add(new Program("DAC", "Diploma", "Diploma in Accounting", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides students with broad base knowledge in financial accounting & reporting, management accounting, finance, economics, management, taxation, auditing, law and information technology & systems."));
        pList.add(new Program("DMC", "Diploma", "Diploma in Mass Communication (Advertising)", "FCCI - Faculty of Communication & Creative Industries", "This programme encompasses the basic knowledge and practical skills of advertising, media and communication."));
        pList.add(new Program("DCS", "Diploma", "Diploma in Computer Science", "FOCS - Faculty of Computing and Information Technology", "Students are trained in both theoretical knowledge and practical skills for software development, system design and related mathematical techniques."));
        pList.add(new Program("DIT", "Diploma", "Diploma in Information Technology", "FOCS - Faculty of Computing and Information Technology", "This programme provides students with a basic understanding of computing techniques and aims to develop the computing and information technology-based knowledge and skills required in modern industrial, commercial and service organisations."));
        pList.add(new Program("DIS", "Diploma", "Diploma in Information System", "FOCS - Faculty of Computing and Information Technology", "This programme majors in business information systems. It aims to produce graduates with fundamental knowledge in information technology and its business related applications."));
        pList.add(new Program("RCB", "Bachelor Degree", "Bachelor of Communication (Honours) in Broadcasting", "FCCI - Faculty of Communication & Creative Industries", "This programme entails knowledge and skills for the technical understanding, writing, scripting, presenting and organising of traditional and new broadcast media."));
        pList.add(new Program("RFS", "Bachelor Degree", "Bachelor of Science (Hons) in Food Science", "FOAS - Faculty of Applied Sciences", "This programme applies the pure science subjects, such as chemistry, biochemistry, nutrition, biology and microbiology to the study of the nature, properties and composition of foods."));
        pList.add(new Program("RCA", "Bachelor Degree", "Bachelor of Corporate Administration (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides students with broad base knowledge in management, law, accounting, taxation, finance and corporate governance."));
        pList.add(new Program("RSW", "Bachelor Degree", "Bachelor of Software Engineering (Honours)", "FOCS - Faculty of Computing and Information Technology", "Graduates of this programme will be able to develop, manage and maintain high-quality software in a systematic, controlled and efficient manner. "));
        return pList;
    }

    public static void inputProgram() {
        Initializer p = new Initializer();
        ListInterface<Program> programList = p.ProgramInitializer();

        // Save the program data to the file using ProgramDAO
        DAO DAO = new DAO();
        DAO.saveToFile(programList, "program.dat");

        System.out.println("\nProgram: \n" + programList);
    }

    //  Method to return a collection of with hard-coded entity values
    public ListInterface<TutorialProgram> TutorialPrInitializer() {
        ListInterface<TutorialProgram> tpList = new ArrList<>();
        tpList.add(new TutorialProgram("RSD", "Bachelor of Information Technology (Honours) in Software Systems Development", "RSDG1", 24, "", 2023, 6));
        tpList.add(new TutorialProgram("RBA", "Bachelor of Business Administration (Honours)", "RBAG1", 25, "", 2023, 6));
        tpList.add(new TutorialProgram("RAC", "Bachelor of Accounting (Honours)", "RACG1", 23, " ", 2023, 1));
        tpList.add(new TutorialProgram("RBF", "Bachelor of Banking and Finance (Honours)", "RBFG1", 24, "Chou Tzuyu", 2023, 6));
        tpList.add(new TutorialProgram("RME", "Bachelor of Mechanical Engineering with Honours", "RMEG1", 23, "Zhao LuSi", 2023, 6));

        return tpList;
    }

    public static void inputTutorialProgram() {
        Initializer tp = new Initializer();
        ListInterface<TutorialProgram> tpList = tp.TutorialPrInitializer();

        // Save the program data to the file using ProgramDAO
        DAO DAO = new DAO();
        DAO.saveToFile(tpList, "tutorialProgram.dat");

        System.out.println("\nProgram: \n" + tpList);
    }

}
