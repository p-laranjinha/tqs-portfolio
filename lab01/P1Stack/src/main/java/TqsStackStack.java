import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;

public class TqsStackStack<T> implements ITqsStack<T> {

    Stack<T> subStack = new Stack<T>();

    public boolean isEmpty() {
        return subStack.isEmpty();
    }

    public int size() {
        return subStack.size();
    }

    public void push(T x) {
        subStack.push(x);
    }

    public T pop() {
        try {
            return subStack.pop();
        } catch (EmptyStackException e) {
            throw new NoSuchElementException();
        }
    }

    public T peek() {
        return subStack.peek();
    }
}
