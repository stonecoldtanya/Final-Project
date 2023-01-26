package ds;

import java.util.function.Consumer;

public class LinkedListImpl<E> {
    private Node<E> head;
    private int size = 0;

    public LinkedListImpl() {
        head = null;
    }

    void add(E element) {
        Node<E> newNode = new Node<E>(element);
        if (!isEmpty()) {
            newNode.next = head;
        }
        this.head = newNode;
        size++;
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

    void add(int pos, E element) {
        if (pos > size) {
            return;
        }
        if (pos == 0) {
            Node<E> newTemp = head;
            head = new Node<E>(element);
            head.next = newTemp;
            return;
        }
        // node for traversal
        Node<E> newTemp = head;
        // Dummy node
        Node<E> prev = new Node<E>(null);
        while (pos > 0) {
            prev = newTemp;
            newTemp = newTemp.next;
            pos--;
        }
        prev.next = new Node<E>(element);
        prev.next.next = newTemp;
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
            for (Node<E> currentNode = head; currentNode != null; currentNode = currentNode.next) {
                if (currentNode.info == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> currentNode = head; currentNode != null; currentNode = currentNode.next) {
                if (element.equals(currentNode.info))
                    return index;
                index++;
            }
        }
        return -1;

    }

    void removeEl(E info) {
        if (head == null) return;
        size--;
        if (head.info == info) {
            head = head.next;
            size--;
            return;
        }
        Node<E> current = head;
        while (current.next != null) {
            if (current.next.info == info) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    void remove(int position) {
        removeEl(get(position));
        //fix
    }

    public int size() {
        if (head == null) return 0;
        int counter = 0;
        for (Node<E> curr = head; curr != null; curr = curr.next)
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
        for (Node<E> n = head; n != null; n = n.next)
            System.out.print(n.info + " ");
    }

    public LinkedListImpl<E> subList(int fromIndex, int toIndex)
            throws IndexOutOfBoundsException {
        if(fromIndex<0 || fromIndex>size() || toIndex<fromIndex || toIndex>size()){
            throw new IndexOutOfBoundsException();
        }

        LinkedListImpl<E> n = new LinkedListImpl<E>();
        Node<E> startNode = head;
        int counter=0;
        while(startNode!=null){
            if(counter>fromIndex && counter<toIndex){
                n.add((E) startNode);
            }
            startNode=startNode.next;
            counter++;
        }
        return n;
    }

    public String toString() {
        String S = "";
        Node<E> X = head;
        if (X == null)
            return S + "";
        while (X.next != null) {
            S += X.info + " ";
            X = X.next;
        }
        S += String.valueOf(X.info);
        return S + " ";
    }
}
