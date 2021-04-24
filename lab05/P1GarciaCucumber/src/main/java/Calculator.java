import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class Calculator {
    private final Deque<Number> stack = new LinkedList<>();
    private static final List<String> OPS = asList("-", "+", "*", "/");

    public void push(Object arg) {
        if (OPS.contains(arg)) {
            Number y = stack.removeLast();
            Number x = stack.isEmpty() ? 0 : stack.removeLast();
            Double val = null;
            switch (arg.toString()) {
                case "-":
                    val = x.doubleValue() - y.doubleValue();
                    break;
                case "+":
                    val = x.doubleValue() + y.doubleValue();
                    break;
                case "*":
                    val = x.doubleValue() * y.doubleValue();
                    break;
                case "/":
                    val = x.doubleValue() / y.doubleValue();
                    break;
            }
            push(val);
        } else {
            stack.add((Number) arg);
        }
    }

    public Number value() {
        return stack.getLast();
    }
}
