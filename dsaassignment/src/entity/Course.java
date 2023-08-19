package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Chew Lip Sin
 */
public class Course implements Serializable {

    private String courseCode;
    private String title;
    private int creditHours;
    private LocalDateTime updateTime;

    public Course(String courseCode, String title, int creditHours) {
        this.courseCode = courseCode;
        this.title = title;
        this.creditHours = creditHours;
        this.updateTime = LocalDateTime.now();
    }

    public Course() {
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.courseCode);
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + this.creditHours;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (this.creditHours != other.creditHours) {
            return false;
        }
        if (!Objects.equals(this.courseCode, other.courseCode)) {
            return false;
        }
        return Objects.equals(this.title, other.title);
    }

    @Override
    public String toString() {
        return "Course" + "courseCode=" + courseCode + ", title=" + title + ", creditHours=" + creditHours + ", updateTime=" + updateTime;
    }
}
