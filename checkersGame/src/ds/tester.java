package ds;

import java.util.LinkedList;
import java.util.List;

public class tester {
    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        q.push(2);
        q.push(23);
        System.out.println(q.top());
        System.out.println(q.size());
    }
}
