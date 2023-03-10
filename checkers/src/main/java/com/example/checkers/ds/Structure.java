package com.example.checkers.ds;

/**
 * Data Structure Interface.
 *
 * @param <E> the type parameter
 */
public interface Structure<E> {
    /**
     * Pushes/adds and element of type E.
     */
    void push(E element);

    /**
     * @return and removes from the ds the last/first added element
     */
    E pop();

    /**
     * @return the last/first added element
     */
    E top();

    /**
     *Checks if the ds is empty. Returns true if there are 0 elements.
     */
    boolean isEmpty();

    /**
     * @return the number of elements in the ds
     */
    int size();

    /**
     * Finds the position of an element in the ds.
     */
    abstract int find(E element);
}
