package utility;

import adt.ListInterface;
import dao.ProgramDAO;
import dao.TutorialPrDAO;
import entity.Program;
import entity.TutorialProgram;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class TutorialValidator {
    private static TutorialPrDAO tpDAO;
    public static boolean isTutorialGroupValid(String groupName) {
         tpDAO = new TutorialPrDAO();
        ListInterface<TutorialProgram> tpList = tpDAO.retrieveFromFile();
        Iterator<TutorialProgram> iterator = tpList.getIterator();
        while (iterator.hasNext()) {
            TutorialProgram tp = iterator.next();
            if (tp.getCode().equalsIgnoreCase(groupName)) {
                return true; 
            }
        }
        return false;
    }
}
