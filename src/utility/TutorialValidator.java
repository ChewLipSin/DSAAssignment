package utility;

import adt.ArrList;
import adt.ListInterface;
import dao.tDAO;
import entity.TutorialProgram;
import java.util.Iterator;
import static utility.MessageUI.printFormattedText;
/**
 *
 * @author Lim Yi Leong
 */
public class TutorialValidator {
    private final tDAO DAO = new tDAO();
    private ListInterface<TutorialProgram> tpList = new ArrList<>();
    
    public boolean isTutorialGroupValid(String groupName) {
        tpList = DAO.retrieveFromFile("tutorialProgram.dat");
        Iterator<TutorialProgram> iterator = tpList.getIterator();
        while (iterator.hasNext()) {
            TutorialProgram tp = iterator.next();
            if (tp.getGroupname().equalsIgnoreCase(groupName)) {
                return true; 
            }
        }
        return false;
    }
        public String getTutorialGroupName(String code){
        String name = null;
        tpList = DAO.retrieveFromFile("tutorialProgram.dat");
        Iterator<TutorialProgram> iterator = tpList.getIterator();
        while (iterator.hasNext()){
            TutorialProgram tp = iterator.next();
            if (tp.getCode().equalsIgnoreCase(code)) {
                name = tp.getGroupname(); 
                System.out.println(name);
            }
        }
        if (name==null){
            printFormattedText("\n---No tutorial group exist---\n",ConsoleColor.MAGENTA);
        }
        return name;
    }
}
