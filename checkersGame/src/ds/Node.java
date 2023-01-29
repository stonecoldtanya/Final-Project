package ds;

public class Node<E> implements Comparable<E>{
    E info;
    Node next;
    Node prev;

    Node(E info) {
        this.info = info;
        this.next = null;
        this.prev = null;
    }

    public E getInfo() {
        return info;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

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
