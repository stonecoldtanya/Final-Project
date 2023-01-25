package ds;

public class Node<E>{
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
}
