package ds;

public class Queue<E> implements Structure<E>{
    final LinkedListImpl<E> list;

    public Queue() {
        this.list = new LinkedListImpl<>();
    }

    @Override
    public void push(E item) {
        int len = list.size();
        if (isEmpty()){
            list.add(item);
        }
        list.add(len, item);
    }

    @Override
    public E pop() {
        if (!isEmpty()) {
            E item = list.get(0);
            list.remove(0);

            return item;
        }

        throw new IllegalArgumentException("it's empty");
    }

    @Override
    public E top() {
        if (isEmpty()){
            throw new IllegalArgumentException("it's empty");
        }
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size(){
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
