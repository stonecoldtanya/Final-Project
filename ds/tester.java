package ds;

public class tester {
    public static void main(String[] args) {
        Queue<Integer> stack = new Queue<>();
        stack.push(43);
        stack.push(21);
        stack.push(1);
        System.out.println(stack.pop());
        System.out.println(stack.find(21));
    }
}
