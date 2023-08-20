/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import adt.ListInterface;
import entity.Course;

/**
 *
 * @author Chew Lip Sin
 */
public class Sort {

    public static void quickSort(ListInterface<Course> arr, int low, int high, String val) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high, val);

            quickSort(arr, low, partitionIndex - 1, val);
            quickSort(arr, partitionIndex + 1, high, val);
        }
    }

    public static int partition(ListInterface<Course> arr, int low, int high, String val) {
        Course pivot = arr.getEntry(high);
        int i = low - 1;
        if (val == "courseCode") {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getCourseCode().compareTo(pivot.getCourseCode()) < 0) {
                    i++;
                    swap(arr, i, j);
                }
            }
        } else if (val == "title") {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getTitle().compareTo(pivot.getTitle()) < 0) {
                    i++;
                    swap(arr, i, j);
                }
            }
        } else if (val == "creditHours") {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getCreditHours() < pivot.getCreditHours()) {
                    i++;
                    swap(arr, i, j);
                }
            }
        } else if (val == "timeCreated") {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getCreateTime().compareTo(pivot.getCreateTime()) < 0) {
                    i++;
                    swap(arr, i, j);
                }
            }
        } else if (val == "updateCreated") {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getUpdateTime().compareTo(pivot.getUpdateTime()) < 0) {
                    i++;
                    swap(arr, i, j);
                }
            }
        } else {
            swap(arr, i + 1, high);
        }
        return i + 1;
    }

    public static void swap(ListInterface<Course> arr, int i, int j) {
        Course temp = arr.getEntry(i);
        arr.replace(i, arr.getEntry(j));
        arr.replace(j, temp);
    }
}
