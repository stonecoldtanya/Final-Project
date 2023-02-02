package com.example.checkers.ds;

/**
 * Implementation of the Queue data structure using a LinkedList.
 *
 * @param <E> the type parameter.
 */
public class Queue<E> implements Structure<E>{

    final LinkedListImpl<E> list;

    /**
     * Instantiates a new Queue by creating a new list.
     */
    public Queue() {
        this.list = new LinkedListImpl<>();
    }

    /**
     * Push method that adds elements to the end of the queue.
     *
     * @param element of type E, the element being added to the queue.
     */
    @Override
    public void push(E element) {
        int len = list.size();
        if (isEmpty()){
            list.add(element);
        }
        list.add(len, element);
    }

    /**
     * Method that gets(removes) the first element in the queue and returns it. Throws an exception if there are no elements in the queue.
     */
    @Override
    public E pop() {
        if (!isEmpty()) {
            E current = list.get(0);
            list.remove(0);

            return current;
        }

        throw new IllegalArgumentException("it's empty");
    }

    /**
     * Method that returns the first element in the queue without removing it.
     */
    @Override
    public E top() {
        if (isEmpty()){
            throw new IllegalArgumentException("it's empty");
        }
        return list.get(0);
    }

    /**
     * Method that checks whether the queue is empty and returns true, if that's the case - false, if there are elements present.
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the size of the queue. The amount of elements present in it.
     */
    @Override
    public int size(){
        return list.size();
    }

    /**
     * Method that returns the position of an element in the Queue.
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
