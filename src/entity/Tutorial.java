/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author zyleo
 */
public class Tutorial implements Serializable{
    String tutGroup;

    public Tutorial(String tutGroup) {
        this.tutGroup = tutGroup;
    }

    public String getTutGroup() {
        return tutGroup;
    }

    public void setTutGroup(String tutGroup) {
        this.tutGroup = tutGroup;
    }
    
}
