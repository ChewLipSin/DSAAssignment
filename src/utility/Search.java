/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import adt.ArrList;
import adt.ListInterface;
import entity.Course;
import entity.CourseCodeComparator;

/**
 *
 * @author Chew Lip Sin
 */
public class Search<T> {
    private final CourseCodeComparator cCodeC = new CourseCodeComparator();

    // Returns index of x if it is present in courseList,
    // else return -1
    public int binarySearch(ListInterface<Course> courseList, String x) {
        ListInterface<Course> courseList2 = courseList;
        ArrList.insertionSort(courseList, cCodeC,"asc");
        int l = 0, r = courseList2.size() - 1;

        // Loop to implement Binary Search
        while (l <= r) {

            // Calculatiing mid
            int m = l + (r - l) / 2;
            Course midValue = courseList2.getEntry(m + 1);
            int res = x.compareTo(midValue.getCourseCode());

            // Check if x is present at mid
            if (res == 0) {
                return m;
            }

            // If x greater, ignore left half
            if (res > 0) {
                l = m + 1;
            } // If x is smaller, ignore right half
            else {
                r = m - 1;
            }
        }

        return -1;
    }

}
