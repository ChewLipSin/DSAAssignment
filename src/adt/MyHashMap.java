
package adt;

import adt.ArrList;

/**
 *
 * @author fungc
 */

public class MyHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private Object[] buckets;

    public MyHashMap() {
        buckets = new Object[INITIAL_CAPACITY];
    }

    public void put(K key, V value) {
        int hashCode = key.hashCode();
        int index = Math.abs(hashCode) % buckets.length;

        if (buckets[index] == null) {
            buckets[index] = new MyEntry<>(key, value);
        } else {
            MyEntry<K, V> entry = (MyEntry<K, V>) buckets[index];
            while (entry.next != null) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
                entry = entry.next;
            }
            entry.next = new MyEntry<>(key, value);
        }
    }

    public V get(K key) {
        int hashCode = key.hashCode();
        int index = Math.abs(hashCode) % buckets.length;

        if (buckets[index] == null) {
            return null;
        }

        MyEntry<K, V> entry = (MyEntry<K, V>) buckets[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    // Other methods (keys(), remove(), etc.) can be added here

    private static class MyEntry<K, V> {
        K key;
        V value;
        MyEntry<K, V> next;

        MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public ArrList<K> keys() {
        ArrList<K> keyList = new ArrList<>();
        for (Object bucket : buckets) {
            if (bucket != null) {
                MyEntry<K, V> entry = (MyEntry<K, V>) bucket;
                while (entry != null) {
                    keyList.add(entry.key);
                    entry = entry.next;
                }
            }
        }
        return keyList;
    }
}



