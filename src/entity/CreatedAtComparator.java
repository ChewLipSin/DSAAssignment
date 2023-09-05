
package entity;

import java.util.Comparator;

/**
 *
 * @author chewr
 */
public class CreatedAtComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Course c1 = (Course) o1;
        Course c2 = (Course) o2;
        return c1.getCreatedAt().compareTo(c2.getCreatedAt());
    }

}
