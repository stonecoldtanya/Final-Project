package ds;

import java.util.Comparator;

public class PriorityQueue<E> extends Queue<E> implements Structure<E> {
    private final Comparator<? super E> comparator;

    public PriorityQueue() {
        super();
        comparator = null;
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        super();
        this.comparator = comparator;
    }

    @Override
    public void push(E element) {
        int position = findPosition(super.list, element);
        list.add(position, element);
    }

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

    @Override
    public String toString() {
        return this.list.toString();
    }
}