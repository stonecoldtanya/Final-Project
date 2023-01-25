package ds;

import java.util.Comparator;

public class PriorityQueue<E> extends Queue<E> implements Structure<E> {
    private final Comparator<? super E> comparator;

    public PriorityQueue() {
        super();
        comparator = null;
    }

    @Override
    public void push(E item) {

    }
}