package dao;

import adt.*;
import entity.Program;
import java.io.IOException;
/**
 * @author Lim Yi Leong
 */
public class ProgramInitializer {

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

    public static void main(String[] args) {
        ProgramInitializer p = new ProgramInitializer();
        ListInterface<Program> programList = p.ProgramInitializer();

        // Save the program data to the file using ProgramDAO
        tDAO DAO = new tDAO();
        try {
            DAO.saveToFile(programList,"program.dat");
        } catch (IOException ex) {
            System.out.println("Error saving data to file: " + ex.getMessage());
        }

        System.out.println("\nProgram: \n" + programList);
    }

}
