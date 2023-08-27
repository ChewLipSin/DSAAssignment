package adt;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author Chew Lip Sin
 * @author Lim Yi Leong
 * @param <T>
 */
public class ArrList<T> implements ListInterface<T>, Serializable {

    private T[] arr;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 20;

    public ArrList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrList(int initialCapacity) {
        numberOfEntries = 0;
        arr = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        if (isArrayFull()) {
            //double the arry list if array list is full
            doubleArray();
        }
        arr[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            if (isArrayFull()) {
                //double the arry list if array list is full
                doubleArray();
            }
            makeRoom(newPosition);
            arr[newPosition - 1] = newEntry;
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public boolean addAll(T... newElements) {
        if (newElements != null) {
            if (isElementsValid(newElements)) {
                for (T element : newElements) {
                    add(element);
                }
                return true;
            }
        }
        return false;
    }

    //Clear all the array elements
    @Override
    public void clear() {
        numberOfEntries = 0;
    }

    @Override
    //check the array list whether contain the entry or not
    public boolean contains(T anEntry) {
        boolean found = false;
        //!found = true and index should smaller than the length of array list
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (anEntry.equals(arr[index])) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = arr[givenPosition - 1];
        }
        return result;
    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;

        //the number enter by user must between 1 and the length of the array list
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = arr[givenPosition - 1];

            //shift the existing entries if the entry removed is not located at the last entry
            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }

            //length should minus 1 after removing a entry
            numberOfEntries--;
        }
        return result;
    }

    @Override
    public boolean removeAll(T... elements) {
        if (isEmpty() || !isElementsValid(elements)) {
            return false;
        } else {
            for (T element : elements) {
                remove(element);
            }
            return true;
        }
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            //-1 because the givenPosition will only start with 1, does not like index of array list that start with 0. 
            arr[givenPosition - 1] = newEntry;
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean remove(T anEntry) {
        boolean isSuccessful = false;

        //if the array list is not empty
        if (!isEmpty()) {
            for (int i = 0; i < numberOfEntries; i++) {
                if (arr[i].equals(anEntry)) { //compare the given entry and every entry in the array list,
                    // if true then go in and remove the given entry                       
                    removeGap(i+1);
                    isSuccessful = true;
                    numberOfEntries--;
                }
            }
        }

        return isSuccessful;
    }

    private boolean isArrayFull() {
        return arr.length == numberOfEntries;

    }

    private void doubleArray() {
        T[] oldArray = arr;
        arr = (T[]) new Object[2 * oldArray.length];
        System.arraycopy(oldArray, 0, arr, 0, numberOfEntries);
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += arr[index] + "\n";
        }

        return outputStr;
    }

    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        // move each entry to next higher index, starting at end of
        // array and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--) {
            arr[index + 1] = arr[index];
        }
    }

    private void removeGap(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            arr[index] = arr[index + 1];
        }
    }

    private boolean isElementsValid(T... newElements) {
        boolean valid = true;
        for (int i = 0; i < newElements.length && valid; i++) {
            if (newElements[i] == null) {
                valid = false;
            }
        }
        return valid;
    }

    @Override
    public Iterator<T> getIterator() {
        return new getIterator<T>();
    }

    public class getIterator<T> implements Iterator<T> {

        private int index;

        public getIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < numberOfEntries;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T nextEntry = (T) arr[index];
            index++; // advance iterator
            return nextEntry;
        }
    }
}
