
package adt;

/**
 *
 * @author Chew Lip Sin
 * @param <T> type of elements stored in the stack.
 */
public interface WhereClause<T> {

    /**
     * Task: Match 1 or more element(s)
     *
     * @param element
     * @return
     */
    boolean match(T element);
}
