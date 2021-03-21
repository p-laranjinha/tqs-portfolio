import java.util.LinkedList;

public class TqsListStack<T> implements ITqsStack<T> {

    private LinkedList<T> elementsCollection = new LinkedList<>();

    @Override
    public boolean isEmpty() {
        return elementsCollection.isEmpty();
    }

    @Override
    public int size() {
        return elementsCollection.size();
    }

    @Override
    public void push(T x) {
        elementsCollection.addFirst(x);
    }

    @Override
    public T pop() {
        return elementsCollection.removeFirst();
    }

    @Override
    public T peek() {
        return elementsCollection.getFirst();
    }

}
