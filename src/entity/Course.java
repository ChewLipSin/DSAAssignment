package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Chew Lip Sin
 */
public class Course implements Serializable {

    private String courseCode;
    private String title;
    private int creditHours;
    private Sem semester;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    /**
     * Enumeration for different intake semesters.
     */
    public enum Sem {
        /**
         * Represents all semesters.
         */
        ALL,
        /**
         * Represents the January semester.
         */
        JAN,
        /**
         * Represents the July semester.
         */
        JUL;

        /**
         * Returns the string representation of a semester.
         *
         * @param sem The semester to convert to a string.
         * @return The string representation of the semester.
         */
        public String getString(Sem sem) {
            switch (sem) {
                case ALL:
                    return "ALL";
                case JAN:
                    return "JAN";
                case JUL:
                    return "JUL";
            }
            return "ALL";
        }

    };

    /**
     * Constructs a new Course instance with specified details.
     *
     * @param courseCode The code of the course.
     * @param title The title of the course.
     * @param creditHours The credit hours of the course.
     * @param semester The intake semester of the course.
     */
    public Course(String courseCode, String title, int creditHours, Sem semester) {
        this.courseCode = courseCode;
        this.title = title;
        this.creditHours = creditHours;
        this.semester = semester;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    /**
     * Default constructor for the Course class.
     */
    public Course() {
    }

    /**
     * Returns the course code.
     *
     * @return The course code.
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Returns the title of the course.
     *
     * @return The title of the course.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the credit hours of the course.
     *
     * @return The credit hours of the course.
     */
    public int getCreditHours() {
        return creditHours;
    }

    /**
     * Updates the updatedAt date of the course to the current date.
     */
    public void update() {
        this.updatedAt = LocalDate.now();
    }

    /**
     * Sets the course code of the course.
     *
     * @param courseCode The new course code to set.
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Gets the date when the course was created.
     *
     * @return The creation date of the course.
     */
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the date when the course was last updated.
     *
     * @return The last updated date of the course.
     */
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the date when the course was last updated.
     *
     * @param updatedAt The updated date to set.
     */
    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Sets the date when the course was created.
     *
     * @param createdAt The creation date to set.
     */
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Sets the title of the course.
     *
     * @param title The new title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the credit hours of the course.
     *
     * @param creditHours The new credit hours to set.
     */
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    /**
     * Computes and returns the hash code value for this course.
     *
     * @return The hash code value for this course.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.courseCode);
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + this.creditHours;
        return hash;
    }

    /**
     * Indicates whether some other object is "equal to" this course.
     *
     * @param obj The reference object with which to compare.
     * @return {@code true} if this course is the same as the obj argument;
     * {@code false} otherwise.
     */
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

    /**
     * Returns the semester of the course.
     *
     * @return The semester of the course.
     */
    public Sem getSemester() {
        return semester;
    }

    /**
     * Sets the semester of the course.
     *
     * @param semester The new semester to set.
     */
    public void setSemester(Sem semester) {
        this.semester = semester;
    }

    /**
     * Compares this course's semester with another semester.
     *
     * @param sem The semester to compare against.
     * @return A negative integer, zero, or a positive integer as this semester
     * is less than, equal to, or greater than the specified semester.
     */
    public int compareSem(Sem sem) {
        if (this.semester.compareTo(sem) < 0) {
            return -1;
        } else if (this.semester.compareTo(sem) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Returns a string representation of the course's semester.
     *
     * @param semester The semester to convert to a string.
     * @return The string representation of the semester.
     */
    public String semToString(Sem semester) {
        if (semester == Sem.JAN) {
            return "JAN";
        } else if (semester == Sem.JUL) {
            return "JUL";
        } else if (semester == Sem.ALL) {
            return "ALL";
        } else {
            return null;

        }
    }

    /**
     * Returns a string representation of the Course object.
     *
     * @return A formatted string representation of the Course object.
     */
    @Override
    public String toString() {
        String sems;
        if (semester == Sem.JAN) {
            sems = "JAN";
        } else if (semester == Sem.JUL) {
            sems = "JUL";
        } else {
            sems = "ALL";
        }
        return String.format("|%-12s|%-52s|  %-2d          |  %-8s|%-12s|%-12s", courseCode, title, creditHours, sems, createdAt, updatedAt);
    }

//    class SortbyCourseCode implements Comparator<Course> {
//
//        @Override
//        public int compare(Course c1, Course c2) {
//            int comparison = c1.courseCode.compareTo(c2.courseCode);
//            if (comparison == 0) {
//                return 0;
//            } else if (comparison > 1) {
//                return 1;
//            } else {
//                return -1;
//            }
//
//        }
//
//    }
}
