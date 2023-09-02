package utility;

import entity.Program;
import java.util.Comparator;
/**
 *
 * @author Lim Yi Leong
 */
public class programCodeComparator implements Comparator{
    
    @Override
    public int compare(Object o1, Object o2) {
        Program p1 = (Program) o1;
        Program p2 = (Program) o2;
        
        return p1.getCode().compareTo(p2.getCode());
    }
}
