package dao;

import adt.*;
import entity.Program;
/**
 *
 * @author Lim Yi Leong
 */
public class TutorialPrInitializer {

//  Method to return a collection of with hard-coded entity values
  public ArrListInterface<Program> ProgramInitializer() {
    ArrListInterface<Program> pList = new ArrList<>();
    pList.add(new Program("RSD", "Bachelor Degree", "Bachelor of Information Technology (Honours) in Software Systems Development", "FOCS - Faculty of Computing and Information Technology", "This programme produces and equips graduates with in-depth knowledge and skills that are essential to work as professionals in the software systems development and computer networking sectors."));
    pList.add(new Program("RBA", "Bachelor Degree", "Bachelor of Business Administration (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides students with a well-rounded business education through a core curriculum of management, marketing, finance, accounting, economics and law courses."));
    pList.add(new Program("RAC", "Bachelor Degree", "Bachelor of Accounting (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme provides a wide spectrum of knowledge and skills required for a career in the accountancy and finance profession."));
    pList.add(new Program("RBF", "Bachelor Degree", "Bachelor of Banking and Finance (Honours)", "FAFB - Faculty of Accountancy, Finance & Business", "This programme aims to produce graduates who are equipped with the knowledge and core competencies and dispositions necessary for pursuing a demanding career in the highly competitive domestic and international banking and finance industry."));
    pList.add(new Program("RME", "Bachelor Degree", "Bachelor of Mechanical Engineering with Honours", "FOET - Faculty of Engineering and Technology", "Mechanical engineering is the broadest among all engineering disciplines. Thus, most of the modern-day inventions are due to knowledge and application of mechanical engineering. "));

    return pList;
  }

  public static void main(String[] args) {
    // To illustrate how to use the initializePrograms() method
    TutorialPrInitializer p = new TutorialPrInitializer();
    ArrListInterface<Program> programList = p.ProgramInitializer();
    System.out.println("\nProgram: \n" + programList);
  }

}
