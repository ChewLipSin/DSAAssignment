/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author fungc
 */
public class MyObjects {

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    public static boolean deepEquals(Object a, Object b) {
        // Implement your own deepEquals logic if needed
        // This is a simplified example
        return equals(a, b);
    }

    public static int hashCode(Object o) {
        return o != null ? o.hashCode() : 0;
    }

    // Other utility methods can be added here

}
