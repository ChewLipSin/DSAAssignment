package entity;

import java.io.Serializable;
import adt.ListInterface;
import adt.ArrList;
import java.lang.Comparable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Chew Lip Sin
 */
public class Course<T> implements Serializable {

    private String courseCode;
    private String title;
    private int creditHours;
    private Sem semester;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    ListInterface<T> course = new ArrList();
    private ListInterface<Program> programs = new ArrList();

//    private static final String[] SEM = {"JAN", "JULY"};
    public enum Sem {
        ALL,
        JAN,
        JUL;

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

    public Course(String courseCode, String title, int creditHours, Sem semester) {
        this.courseCode = courseCode;
        this.title = title;
        this.creditHours = creditHours;
        this.semester = semester;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
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

    public Sem getSemester() {
        return semester;
    }

    public void setSemester(Sem semester) {
        this.semester = semester;
    }

    public int compareSem(Sem sem) {
        if (this.semester.compareTo(sem) < 0) {
            return -1;
        } else if (this.semester.compareTo(sem) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

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

    public ListInterface<Program> getPrograms() {
        return programs;
    }


    public void addProgram(Program program){
        programs.add(program);
        program.getCourses().add(this);
    }
    
    public void removeProgram(Program program){
        programs.remove(program);
        program.getCourses().remove(this);
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
