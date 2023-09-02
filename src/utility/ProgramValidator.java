package utility;

import adt.ArrList;
import adt.ListInterface;
import dao.tDAO;
import entity.Program;
import java.util.Iterator;
/**
 *
 * @author Lim Yi Leong
 */
public class ProgramValidator {
    private final tDAO DAO = new tDAO();
    private ListInterface<Program> pList = new ArrList<>();
    
    public boolean isProgramCodeValid(String code) {
        pList = DAO.retrieveFromFile("program.dat");
        Iterator<Program> iterator = pList.getIterator();
        while (iterator.hasNext()) {
            Program p = iterator.next();
            if (p.getCode().equalsIgnoreCase(code)) {
                return true; 
            }
        }
        return false;
    }
    
    public String getProgramName(String code){
        String name = null;
        pList=DAO.retrieveFromFile("program.dat");
        Iterator<Program> iterator = pList.getIterator();
        while (iterator.hasNext()){
            Program p = iterator.next();
            if (p.getCode().equalsIgnoreCase(code)) {
                name = p.getName(); 
                System.out.print("The tutorial programme name : "+ name);
            }
        }
        return name;
    }
}
