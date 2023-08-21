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

    public void insertionSort(ListInterface<Course> arr, String val) {
        int n = arr.size();
        for (int i = 1; i < n; i++) {
            Course key = arr.getEntry(i + 1);

            int j = i - 1;
            if (val == "courseCode") {
                while (j >= 0 && arr.getEntry(j + 1).getCourseCode().compareTo(key.getCourseCode()) > 0) {
                    arr.replace(j + 2, arr.getEntry(j + 1));
                    j--;
                }
            }
            if (val == "title") {
                while (j >= 0 && arr.getEntry(j + 1).getTitle().compareTo(key.getTitle()) > 0) {
                    arr.replace(j + 2, arr.getEntry(j + 1));
                    j--;
                }
            }
            if (val == "semester") {
                while (j >= 0 && arr.getEntry(j + 1).getSemester().getString(arr.getEntry(j + 1).getSemester()).compareTo(key.getSemester().getString(key.getSemester())) > 0) {
                    arr.replace(j + 2, arr.getEntry(j + 1));
                    j--;
                }
            }
            if (val == "creditHours") {
                while (j >= 0 && arr.getEntry(j + 1).getCreditHours() > key.getCreditHours()) {
                    arr.replace(j + 2, arr.getEntry(j + 1));
                    j--;
                }
            }
            if (val == "updatedAt") {
                while (j >= 0 && arr.getEntry(j + 1).getUpdatedAt().compareTo(key.getUpdatedAt()) > 0) {
                    arr.replace(j + 2, arr.getEntry(j + 1));
                    j--;
                }
            }
            if (val == "createdAt") {
                while (j >= 0 && arr.getEntry(j + 1).getCreatedAt().compareTo(key.getCreatedAt()) > 0) {
                    arr.replace(j + 2, arr.getEntry(j + 1));
                    j--;
                }
            }
            arr.replace(j + 2, key);

        }
    }

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
        }
        if (val == "title") {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getTitle().compareTo(pivot.getTitle()) < 0) {
                    i++;
                    swap(arr, i, j);
                }
            }
        }
        if (val == "creditHours") {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getCreditHours() < pivot.getCreditHours()) {
                    i++;
                    swap(arr, i, j);
                }
            }
        }
        if (val == "semester") {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getSemester().getString(arr.getEntry(j).getSemester()).compareTo(pivot.getSemester().getString(pivot.getSemester())) < 0)  {
                    i++;
                    swap(arr, i, j);
                }
            }
        }
        if (val == "createdAt") {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getCreatedAt().compareTo(pivot.getCreatedAt()) < 0) {
                    i++;
                    swap(arr, i, j);
                }
            }
        }
        if (val == "updatedAt") {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getUpdatedAt().compareTo(pivot.getUpdatedAt()) < 0) {
                    i++;
                    swap(arr, i, j);
                }
            }
        }
        swap(arr, i + 1, high);

        return i + 1;
    }

    public static void swap(ListInterface<Course> arr, int i, int j) {
        Course temp = arr.getEntry(i);
        arr.replace(i, arr.getEntry(j));
        arr.replace(j, temp);
    }
}
