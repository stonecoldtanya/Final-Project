package com.example.checkers.ds;

/**
 * Implementation of the Stack data structure using a LinkedList.
 *
 * @param <E> the type parameter.
 */
public class Stack<E> implements Structure<E>{
    private LinkedListImpl list;

    /**
     * Instantiates a new Stack.
     */
    public Stack() {
        this.list = new LinkedListImpl<>();
    }

    /**
     * Push method that adds elements to the start of the stack.
     *
     * @param element of type E, the element being added to the stack.
     */
    @Override
    public void push(E element) {
        list.add(element);
    }

    /**
     * Method that gets(removes) the last(element at position 0 since it's the last one to be added) element in the stack and returns it. Throws an exception if there are no elements in the stack.
     */
    @Override
    public E pop() {
        E current;
        if (list.isEmpty()){
            throw new IllegalArgumentException("it's empty");
        }else {
            current = (E) list.get(0);
            list.remove(0);
        }
        return current;
    }

    /**
     * Method that returns the last added element in the stack without removing it.
     */
    @Override
    public E top() {
        E current;
        if (list.isEmpty()){
            throw new IllegalArgumentException("it's empty");
        }else {
            current = (E) list.get(0);
        }
        return current;
    }

    /**
     * Method that checks whether the stack is empty and returns true, if that's the case - false, if there are elements present.
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the size of the stack. The amount of elements present in it.
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Method that returns the position of an element in the stack.
     */
    @Override
    public int find(E element) {
        return list.indexOf(element);
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
