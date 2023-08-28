package utility;

import adt.ListInterface;
import entity.TutorialProgram;
/**
 *
 * @author Lim Yi Leong
 */
public class Sort {

    public void insertionSort(ListInterface<TutorialProgram> arr, String val) {
        int n = arr.size();
        for (int i = 1; i < n; i++) {
            TutorialProgram key = arr.getEntry(i + 1);

            int j = i - 1;
            if ("code".equals(val)) {
                while (j >= 0 && arr.getEntry(j + 1).getCode().compareTo(key.getCode()) > 0) {
                    arr.replace(j + 2, arr.getEntry(j + 1));
                    j--;
                }
            }
            if ("programname".equals(val)) {
                while (j >= 0 && arr.getEntry(j + 1).getProgramname().compareTo(key.getProgramname()) > 0) {
                    arr.replace(j + 2, arr.getEntry(j + 1));
                    j--;
                }
            }
            if ("groupname".equals(val)) {
                while (j >= 0 && arr.getEntry(j + 1).getGroupname().compareTo(key.getGroupname()) > 0) {
                    arr.replace(j + 2, arr.getEntry(j + 1));
                    j--;
                }
            }
            if ("numStudent".equals(val)) {
                while (j >= 0 && arr.getEntry(j + 1).getNumStudent() > key.getNumStudent()) {
                    arr.replace(j + 2, arr.getEntry(j + 1));
                    j--;
                }
            }
            if ("classRap".equals(val)) {
                while (j >= 0 && arr.getEntry(j + 1).getClassRap().compareTo(key.getClassRap()) > 0) {
                    arr.replace(j + 2, arr.getEntry(j + 1));
                    j--;
                }
            }
            if (val.equals("intakeYear")) {
                while (j >= 0 && arr.getEntry(j + 1).getIntakeYear() > key.getIntakeYear()) {
                    arr.replace(j + 2, arr.getEntry(j + 1));
                    j--;
                }
            }
            
            if (val.equals("intakeMonth")) {
                while (j >= 0 && arr.getEntry(j + 1).getIntakeMonth() > key.getIntakeMonth()) {
                    arr.replace(j + 2, arr.getEntry(j + 1));
                    j--;
                }
            }
            arr.replace(j + 2, key);

        }
    }
/*takes the list arr, the indices low and high that define the range of the current subarray, 
    and the sorting field val.*/
    public static void quickSort(ListInterface<TutorialProgram> arr, int low, int high, String val) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high, val);

            quickSort(arr, low, partitionIndex - 1, val);
            quickSort(arr, partitionIndex + 1, high, val);
        }
    }

    public static int partition(ListInterface<TutorialProgram> arr, int low, int high, String val) {
        TutorialProgram pivot = arr.getEntry(high);
        int i = low - 1;
        if (val.equals("code")) {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getCode().compareTo(pivot.getCode()) < 0) {
                    i++;
                    swap(arr, i, j);
                }
            }
        }
        if ("programname".equals(val)) {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getProgramname().compareTo(pivot.getProgramname()) < 0) {
                    i++;
                    swap(arr, i, j);
                }
            }
        }
        if ("groupname".equals(val)) {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getGroupname().compareTo(pivot.getGroupname()) < 0) {
                    i++;
                    swap(arr, i, j);
                }
            }
        }
        if ("numStudent".equals(val)) {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getNumStudent() > pivot.getNumStudent()) {
                    i++;
                    swap(arr, i, j);
                }
            }
        }
        if ("classRap".equals(val)) {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getClassRap().compareTo(pivot.getClassRap()) < 0) {
                    i++;
                    swap(arr, i, j);
                }
            }
        }
        if ("intakeYear".equals(val)) {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getIntakeYear() > pivot.getIntakeYear()) {
                    i++;
                    swap(arr, i, j);
                }
            }
        }
        
        if ("intakeMonth".equals(val)) {
            for (int j = low; j < high; j++) {
                if (arr.getEntry(j).getIntakeMonth() > pivot.getIntakeMonth()) {
                    i++;
                    swap(arr, i, j);
                }
            }
        }
        swap(arr, i + 1, high);

        return i + 1;
    }
/*The swap method is used to swap two elements in the list. 
    It takes the list arr and the indices i and j.*/
    public static void swap(ListInterface<TutorialProgram> arr, int i, int j) {
        TutorialProgram temp = arr.getEntry(i);
        arr.replace(i, arr.getEntry(j));
        arr.replace(j, temp);
    }
}