package com.example.checkers.ds;

import java.util.Comparator;

/**
 * PriorityQueue implementation using LinkedList. Extends the Queue class.
 *
 * @param <E> the type parameter
 */
public class PriorityQueue<E> extends Queue<E> implements Structure<E> {
    private final Comparator<? super E> comparator;

    /**
     * Instantiates a new Priority queue without a comparator.
     */
    public PriorityQueue() {
        super();
        comparator = null;
    }

    /**
     * Instantiates a new Priority queue with a comparator that will define the priority.
     *
     * @param comparator the comparator
     */
    public PriorityQueue(Comparator<? super E> comparator) {
        super();
        this.comparator = comparator;
    }

    /**
     * Push method that adds elements to the queue based on their priority.
     *
     * @param element of type E, the element being added to the queue.
     */
    @Override
    public void push(E element) {
        int position = findPosition(super.list, element);
        list.add(position, element);
    }
    /**
     * Method that finds a position for an element in the priorityQueue based on their priority.
     * The element is being compared with the already added elements in the queue and based on that it returns a suitable position.
     */
    private int findPosition(LinkedListImpl<E> list, E element) {
        if (list.size() == 0) {
            return 0;
        }

        if (list.size() == 1) {
            if (compare(list.get(0), element) > 0) {
                return 0;
            }
            return 1;
        }

        int size = list.size();
        int half = size / 2;
        E mid = list.get(half - 1);

        if (compare(mid, element) > 0) {
            return findPosition(list.subList(0, half - 1), element);
        }
        return half + findPosition(list.subList(half, size), element);
    }

    private int compare(E element, E other) {
        if (this.comparator == null) {
            Comparable<E> comparable = (Comparable<E>) element;
            return comparable.compareTo(other);
        }

        return this.comparator.compare(element, other);
    }

    /**
     * Returns the size of the priorityQueue. The amount of elements present in it.
     */
    @Override
    public int size(){
        return list.size();
    }

    @Override
    public E pop() {
        return super.pop();
    }

    @Override
    public E top() {
        return super.top();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public int find(E element) {
        return super.find(element);
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}