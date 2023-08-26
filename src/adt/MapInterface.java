package adt;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Chew Lip Sin
 */
public interface MapInterface<K , V> {
    public void put(K key, V value);
    
    public void delete(K key);
    
    public boolean containsKey(K key);
    
    public V get(K key);
    
    public int size();
}
