/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author Chew Lip Sin
 */
public interface OrderClause<T> {
    
    /**
    * Task: Compare the record
    * 
    * return move to the front or move to the back
    */
    public static final int MOVE_FORWARD = -1;
    public static final int MOVE_BACKWARD = 1;
    
    int compare(T t1, T t2);
}