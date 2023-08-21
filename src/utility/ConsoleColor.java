/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author Chew Lip Sin
 */
public enum ConsoleColor {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BRIGHTBLUE("\u001B[34;1m"),
    CYAN("\u001b[36m");


    private final String code;

    ConsoleColor(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
