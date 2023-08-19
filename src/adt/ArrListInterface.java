/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author Chew Lip Sin
 * @author Lim Yi Leong
 *
 */
public interface ArrListInterface<T> {

    public Iterator<T> getIterator();

    public boolean add(T newEntry);

    public boolean add(int newPosition, T newEntry);

    public void clear();

    public boolean contains(T newEntry);

    public T getEntry(int givenPosition);

    public int size();

    public boolean isEmpty();

    public T remove(int givenPosition);

    public boolean remove(T anEntry);

    public boolean replace(int givenPosition, T newEntry);

    public boolean isFull();

}
