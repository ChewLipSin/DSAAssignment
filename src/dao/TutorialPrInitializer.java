package dao;

import adt.*;
import entity.TutorialProgram;
import java.io.IOException;
/**
 * @author Lim Yi Leong
 */
public class TutorialPrInitializer {

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

    public static void main(String[] args) {
        TutorialPrInitializer tp = new TutorialPrInitializer();
        ListInterface<TutorialProgram> tpList = tp.TutorialPrInitializer();

        // Save the program data to the file using ProgramDAO
        tDAO DAO = new tDAO();
        try {
            DAO.saveToFile(tpList,"tutorialProgram.dat");
        } catch (IOException ex) {
            System.out.println("Error saving data to file: " + ex.getMessage());
        }

        System.out.println("\nProgram: \n" + tpList);
    }

}
