/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author Chew Lip Sin
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
