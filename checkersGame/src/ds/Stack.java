package ds;

public class Stack<E> implements Structure<E>{
    private LinkedListImpl list;

    public Stack() {
        this.list = new LinkedListImpl<>();
    }

    @Override
    public void push(E item) {
        list.add(item);
    }

    @Override
    public E pop() {
        E current;
        if (list.isEmpty()){
            throw new IllegalArgumentException("it's empty");
        }else {
            current = (E) list.get(0);
            list.remove(0);
        }
        return current;
    }

    @Override
    public E top() {
        E current;
        if (list.isEmpty()){
            throw new IllegalArgumentException("it's empty");
        }else {
            current = (E) list.get(0);
        }
        return current;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public int find(E element) {
        return list.indexOf(element);
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
