package entity;

import java.io.Serializable;
/**
 * @author Lim Yi Leong
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
