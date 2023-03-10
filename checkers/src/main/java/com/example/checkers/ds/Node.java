package com.example.checkers.ds;

/**
 * Node class used to implement a LinkedList data structure.
 *
 * @param <E> the type parameter
 */
public class Node<E> implements Comparable<E>{
    /**
     * The data that node will be used to work with.
     */
    E info;

    Node next;
    Node prev;

    /**
     * Instantiates a new Node.
     *
     * @param info the data collected in the Node.
     */
    Node(E info) {
        this.info = info;
        this.next = null;
        this.prev = null;
    }

    /**
     * @return the info
     */
    public E getInfo() {
        return info;
    }

    /**
     * @return the next node
     */
    public Node getNext() {
        return next;
    }

    /**
     @return the previous node
     */
    public Node getPrev() {
        return prev;
    }

    /**
     * Sets next node.
     */
    public void setNext(Node next) {
        this.next = next;
    }


    @Override
    public int compareTo(E o) {
        return 0;
    }

    @Override
    public String toString() {
        return info.toString();
    }
}
