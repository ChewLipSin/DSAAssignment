/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author Chew Lip Sin
 */
public class Map<K, V> implements MapInterface<K, ArrList<V>> {

    private Object[] keys;
    private Object[] values;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Map() {
        this(DEFAULT_CAPACITY);
    }
    public Map(int initialCapacity) {
        keys = new Object[initialCapacity];
        values = new Object[initialCapacity];
        size = 0;
    }

    @Override
    public void put(K key, ArrList<V> value) {
        int index = indexOf(key);
        if (index == -1) {
            if (size == keys.length) {
                resizeArrays();
            }
            keys[size] = key;
            values[size] = value;
            size++;
        } else {
            values[index] = value;
        }
    }

    @Override
    public void delete(K key) {
        int index = indexOf(key);
        if (index != -1) {
            // Move the last element to the removed element's position
            keys[index] = keys[size - 1];
            values[index] = values[size - 1];
            // Clear the last element's position
            keys[size - 1] = null;
            values[size - 1] = null;
            size--;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return indexOf(key) != -1;
    }

    @Override
    public ArrList<V> get(K key) {
        int index = indexOf(key);
        return index != -1 ? (ArrList<V>) values[index] : null;
    }

    @Override
    public int size() {
        return size;
    }

    private int indexOf(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private void resizeArrays() {
        int newCapacity = keys.length * 2;
        Object[] newKeys = new Object[newCapacity];
        Object[] newValues = new Object[newCapacity];

        System.arraycopy(keys, 0, newKeys, 0, size);
        System.arraycopy(values, 0, newValues, 0, size);

        keys = newKeys;
        values = newValues;
    }

    public void modifyKey(K oldKey, K newKey) {
        int index = indexOf(oldKey);
        if (index != -1) {
            // Retrieve the value associated with the old key
            ArrList<V> value = (ArrList<V>) values[index];

            // Remove the old key-value pair
            delete(oldKey);

            // Re-add the value with the new key
            put(newKey, value);
        }
    }
}
