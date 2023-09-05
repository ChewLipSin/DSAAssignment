
package adt;

/**
 *
 * @author Chew Lip Sin
 * @param <T> The type of elements stored in the linked list.
 */
public interface FirstOrDefaultClause<T> {

    /**
     * Task: Match first or default record
     *
     * @param element
     * @return
     */
    boolean match(T element);
}
