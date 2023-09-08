
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
        pList.add(new Program("RSD", "BACHELOR DEGREE", 
                "BACHELOR OF INFORMATION TECHNOLOGY (HONOURS) IN SOFTWARE SYSTEMS DEVELOPMENT", 
                "FOCS - FACULTY OF COMPUTING AND INFORMATION TECHNOLOGY", 
                "THIS PROGRAMME PRODUCES AND EQUIPS GRADUATES WITH IN-DEPTH KNOWLEDGE AND S"
                        + "KILLS THAT ARE ESSENTIAL TO WORK AS PROFESSIONALS IN THE SOFTWARE "
                        + "SYSTEMS DEVELOPMENT AND COMPUTER NETWORKING SECTORS."));
        pList.add(new Program("RBA", "BACHELOR DEGREE", 
                "BACHELOR OF BUSINESS ADMINISTRATION (HONOURS)", 
                "FAFB - FACULTY OF ACCOUNTANCY, FINANCE & BUSINESS", 
                "THIS PROGRAMME PROVIDES STUDENTS WITH A WELL-ROUNDED BUSINESS EDUCATION "
                        + "THROUGH A CORE CURRICULUM OF MANAGEMENT, MARKETING, FINANCE, "
                        + "ACCOUNTING, ECONOMICS AND LAW COURSES."));
        pList.add(new Program("RAC", "BACHELOR DEGREE", 
                "BACHELOR OF ACCOUNTING (HONOURS)", 
                "FAFB - FACULTY OF ACCOUNTANCY, FINANCE & BUSINESS", 
                "THIS PROGRAMME PROVIDES A WIDE SPECTRUM OF KNOWLEDGE AND SKILLS REQUIRED "
                        + "FOR A CAREER IN THE ACCOUNTANCY AND FINANCE PROFESSION."));
        pList.add(new Program("RBF", "BACHELOR DEGREE", 
                "BACHELOR OF BANKING AND FINANCE (HONOURS)", 
                "FAFB - FACULTY OF ACCOUNTANCY, FINANCE & BUSINESS", 
                "THIS PROGRAMME AIMS TO PRODUCE GRADUATES WHO ARE EQUIPPED WITH THE KNOWLEDGE "
                        + "AND CORE COMPETENCIES AND DISPOSITIONS NECESSARY FOR PURSUING A "
                        + "DEMANDING CAREER IN THE HIGHLY COMPETITIVE DOMESTIC AND "
                        + "INTERNATIONAL BANKING AND FINANCE INDUSTRY."));
        pList.add(new Program("RME", "BACHELOR DEGREE", 
                "BACHELOR OF MECHANICAL ENGINEERING WITH HONOURS", 
                "FOET - FACULTY OF ENGINEERING AND TECHNOLOGY", 
                "MECHANICAL ENGINEERING IS THE BROADEST AMONG ALL ENGINEERING DISCIPLINES. "
                        + "THUS, MOST OF THE MODERN-DAY INVENTIONS ARE DUE TO KNOWLEDGE AND "
                        + "APPLICATION OF MECHANICAL ENGINEERING."));
        pList.add(new Program("DAC", "DIPLOMA", 
                "DIPLOMA IN ACCOUNTING", 
                "FAFB - FACULTY OF ACCOUNTANCY, FINANCE & BUSINESS", 
                "THIS PROGRAMME PROVIDES STUDENTS WITH BROAD BASE KNOWLEDGE IN FINANCIAL "
                        + "ACCOUNTING & REPORTING, MANAGEMENT ACCOUNTING, FINANCE, ECONOMICS, "
                        + "MANAGEMENT, TAXATION, AUDITING, LAW AND INFORMATION "
                        + "TECHNOLOGY & SYSTEMS."));
        pList.add(new Program("DMC", "DIPLOMA", 
                "DIPLOMA IN MASS COMMUNICATION (ADVERTISING)", 
                "FCCI - FACULTY OF COMMUNICATION & CREATIVE INDUSTRIES", 
                "THIS PROGRAMME ENCOMPASSES THE BASIC KNOWLEDGE AND PRACTICAL SKILLS "
                        + "OF ADVERTISING, MEDIA AND COMMUNICATION."));
        pList.add(new Program("DCS", "DIPLOMA", 
                "DIPLOMA IN COMPUTER SCIENCE", 
                "FOCS - FACULTY OF COMPUTING AND INFORMATION TECHNOLOGY", 
                "STUDENTS ARE TRAINED IN BOTH THEORETICAL KNOWLEDGE AND PRACTICAL SKILLS FOR "
                        + "SOFTWARE DEVELOPMENT, SYSTEM DESIGN AND RELATED "
                        + "MATHEMATICAL TECHNIQUES."));
        pList.add(new Program("DIT", "DIPLOMA", 
                "DIPLOMA IN INFORMATION TECHNOLOGY", 
                "FOCS - FACULTY OF COMPUTING AND INFORMATION TECHNOLOGY", 
                "THIS PROGRAMME PROVIDES STUDENTS WITH A BASIC UNDERSTANDING OF COMPUTING "
                        + "TECHNIQUES AND AIMS TO DEVELOP THE COMPUTING AND INFORMATION "
                        + "TECHNOLOGY-BASED KNOWLEDGE AND SKILLS REQUIRED IN MODERN INDUSTRIAL, "
                        + "COMMERCIAL AND SERVICE ORGANISATIONS."));
        pList.add(new Program("DIS", "DIPLOMA", 
                "DIPLOMA IN INFORMATION SYSTEM", 
                "FOCS - FACULTY OF COMPUTING AND INFORMATION TECHNOLOGY", 
                "THIS PROGRAMME MAJORS IN BUSINESS INFORMATION SYSTEMS. IT AIMS TO PRODUCE "
                        + "GRADUATES WITH FUNDAMENTAL KNOWLEDGE IN INFORMATION TECHNOLOGY AND "
                        + "ITS BUSINESS RELATED APPLICATIONS."));
        pList.add(new Program("RCB", "BACHELOR DEGREE", 
                "BACHELOR OF COMMUNICATION (HONOURS) IN BROADCASTING", 
                "FCCI - FACULTY OF COMMUNICATION & CREATIVE INDUSTRIES", 
                "THIS PROGRAMME ENTAILS KNOWLEDGE AND SKILLS FOR THE TECHNICAL UNDERSTANDING, "
                        + "WRITING, SCRIPTING, PRESENTING AND ORGANISING OF TRADITIONAL AND NEW "
                        + "BROADCAST MEDIA."));
        pList.add(new Program("RFS", "BACHELOR DEGREE", 
                "BACHELOR OF SCIENCE (HONS) IN FOOD SCIENCE", 
                "FOAS - FACULTY OF APPLIED SCIENCES", 
                "THIS PROGRAMME APPLIES THE PURE SCIENCE SUBJECTS, SUCH AS CHEMISTRY, "
                        + "BIOCHEMISTRY, NUTRITION, BIOLOGY AND MICROBIOLOGY TO THE "
                        + "STUDY OF THE NATURE, PROPERTIES AND COMPOSITION OF FOODS."));
        pList.add(new Program("RCA", "BACHELOR DEGREE", 
                "BACHELOR OF CORPORATE ADMINISTRATION (HONOURS)", 
                "FAFB - FACULTY OF ACCOUNTANCY, FINANCE & BUSINESS", 
                "THIS PROGRAMME PROVIDES STUDENTS WITH BROAD BASE KNOWLEDGE IN MANAGEMENT, "
                        + "LAW, ACCOUNTING, TAXATION, FINANCE AND CORPORATE GOVERNANCE."));
        pList.add(new Program("RSW", "BACHELOR DEGREE", 
                "BACHELOR OF SOFTWARE ENGINEERING (HONOURS)", 
                "FOCS - FACULTY OF COMPUTING AND INFORMATION TECHNOLOGY", 
                "GRADUATES OF THIS PROGRAMME WILL BE ABLE TO DEVELOP, MANAGE AND MAINTAIN "
                        + "HIGH-QUALITY SOFTWARE IN A SYSTEMATIC, CONTROLLED AND "
                        + "EFFICIENT MANNER. "));
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
        tpList.add(new TutorialProgram("RSD", 
                "BACHELOR OF INFORMATION TECHNOLOGY (HONOURS) IN SOFTWARE SYSTEMS DEVELOPMENT", 
                "RSDG1", 24, "Gini Thai May", 2023, 6));
        tpList.add(new TutorialProgram("RBA", 
                "BACHELOR OF BUSINESS ADMINISTRATION (HONOURS)", "RBAG1", 25, 
                "Modi", 2023, 6));
        tpList.add(new TutorialProgram("RAC", 
                "BACHELOR OF ACCOUNTING (HONOURS)", "RACG1", 23, 
                "Abdullah", 2023, 1));
        tpList.add(new TutorialProgram("RBF", 
                "BACHELOR OF BANKING AND FINANCE (HONOURS)", "RBFG1", 24, 
                "Chou Tzuyu", 2023, 6));
        tpList.add(new TutorialProgram("RME", 
                "BACHELOR OF MECHANICAL ENGINEERING WITH HONOURS", "RMEG1", 23, 
                "Zhao LuSi", 2023, 6));

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
