package ds;

public interface Structure<E> {
    void push(E item);

    E pop();

    E top();

    boolean isEmpty();

    int size();

    abstract int find(E element);
}
