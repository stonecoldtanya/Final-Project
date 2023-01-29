package ds;

import java.util.function.Consumer;

public class LinkedListImpl<E> {
    private Node<E> head;
    private int size = 0;
    private Node<E> tail;
    public LinkedListImpl() {
        head = null;
        tail = null;
    }

    void add(E element) {
        Node<E> newNode = new Node<E>(element);
        if (!isEmpty()) {
            newNode.next = head;
        }
        this.head = newNode;
        size++;
    }

    public void insert(E element) {
        Node<E> newNode = new Node<E>(element);
        if(!isEmpty()){
            getFinal().next = newNode;
        } else {
            head = newNode;
        }
        size++;
    }

    public Node<E> getFinal(){
        Node<E> node = head;
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    boolean isEmpty() {
        // Checking if head node points to null
        if (head == null) {
            return true;
        }
        return false;
    }

    E get(int index) {
        checkIndex(index);
        E result = null;
        int currentIndex = 0;
        Node<E> currentNode = this.head;

        while (currentNode != null) {
            if (currentIndex == index) {
                result = currentNode.info;
                break;
            }
            currentIndex++;
            currentNode = currentNode.next;
        }

        return result;
    }

    void add(int position, E element) {
        if (position > size()) {
            return;
        }
        if (position == 0) {
            Node<E> current = head;
            head = new Node<E>(element);
            head.next = current;
            size++;
            return;
        }

        Node<E> current = head;
        Node<E> prev = new Node<E>(null);
        while (position > 0) {
            prev = current;
            current = current.next;
            position--;
        }
        prev.next = new Node<E>(element);
        prev.next.next = current;
        size++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("The index " + index + " is out of bounds");
        }
    }

    int indexOf(E element) {
        int index = 0;
        if (element == null) {
            for (Node<E> current = head; current != null; current = current.next) {
                if (current.info == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> current = head; current != null; current = current.next) {
                if (element.equals(current.info))
                    return index;
                index++;
            }
        }
        return -1;

    }

    void removeEl(E info) {
        if (head == null) return;
        if (head.info == info) {
            head = head.next;
            size--;
            return;
        }
        Node<E> current = head;
        while (current.next != null) {
            if (current.next.info == info) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }

    void remove(int position) {
        removeEl(get(position));
    }

    public int size() {
        if (head == null) return 0;
        int counter = 0;
        for (Node<E> current = head; current != null; current = current.next)
            counter++;
        return counter;
    }

    void forEach(Consumer<E> consumer) {
        Node<E> currentNode = this.head;
        while (currentNode != null) {
            consumer.accept(currentNode.info);
            currentNode = currentNode.next;
        }
    }

    void dump() {
        for (Node<E> node = head; node != null; node = node.next)
            System.out.print(node.info + " ");
    }

    public LinkedListImpl<E> subList(int start, int end)
            throws IndexOutOfBoundsException {
        if(start < 0 || start > size() || end < start || end > size()){
            throw new IndexOutOfBoundsException();
        }

        LinkedListImpl<E> list = new LinkedListImpl<E>();
        Node<E> startNode = head;
        int counter=0;
        while(startNode != null){
            if(counter >= start && counter <= end){
                list.add(startNode.getInfo());
            }
            startNode = startNode.next;
            counter++;
        }
        return list;
    }

    public void reverse() {
        if (!isEmpty()){
            throw new NullPointerException("it's empty");
        }

        if (size() == 1) {
            return;
        }

        else if (size() == 2) {
            tail.setNext(head);
            head = tail;
            tail = head.getNext();
            tail.setNext(null);
        }

        else {
            Node<E> current = head;
            Node<E> currentNext = head.getNext();
            Node<E> currentNextNext = currentNext.getNext();
            tail = head;

            while (currentNext != null) {
                currentNext.setNext(current);
                current = currentNext;
                currentNext = currentNextNext;
                if (currentNextNext != null) {
                    currentNextNext = currentNextNext.getNext();
                }
            }
            tail.setNext(null);
            head = current;
        }
    }

    public String toString() {
        String str = "";
        Node<E> current = head;
        if (current == null)
            return str+ "";
        while (current.next != null) {
            str += current.info + " ";
            current = current.next;
        }
       str += String.valueOf(current.info);
        return str + " ";
    }

    public int getSize() {
        return size;
    }
}
