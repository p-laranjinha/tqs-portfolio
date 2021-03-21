import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TqsStackStackTest {

    private ITqsStack<String> emptyStack;
    private ITqsStack<String> threeStack;

    @BeforeEach
    void setUp() {
        //emptyStack = new TqsStackStack<>();
        emptyStack = new TqsListStack<>();
        threeStack = new TqsListStack<>();
        threeStack.push("A");
        threeStack.push("B");
        threeStack.push("C");
    }

    @AfterEach
    void tearDown() {
    }

    //@DisplayName("A stack is empty after construction")
    @Test
    void isEmpty() {
        assertTrue(emptyStack.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, emptyStack.size()); //, "empty stack reports non-zero size");
    }

    @Test
    void push3Items() {
        emptyStack.push("A");
        emptyStack.push("B");
        emptyStack.push("C");

        assertAll(
                () -> assertEquals(3, emptyStack.size()),
                () -> assertFalse(emptyStack.isEmpty())
        );
    }

    @Test
    void pushAndPop() {
        threeStack.push("D");

        assertEquals("D", threeStack.pop());
    }

    @Test
    void peek() {
        threeStack.push("D");

        assertAll(
                () -> assertEquals("D", threeStack.peek()),
                () -> assertEquals(4, threeStack.size())
        );
    }

    @Test
    void popAll() {
        int size = threeStack.size();
        for (int i = 0; i < size; i++) {
            threeStack.pop();
        }

        assertAll(
                () -> assertEquals(0, emptyStack.size()),
                () -> assertTrue(emptyStack.isEmpty())
        );
    }

    @Test
    void popOnEmpty() {
        Exception e = assertThrows(
                NoSuchElementException.class, () -> emptyStack.pop()
        );
    }

    @Test
    void peekOnEmpty() {
        Exception e = assertThrows(
                NoSuchElementException.class, () -> emptyStack.peek()
        );
    }

//    @Test
//    void pop() {
//        assertEquals("C", threeStack.pop());
//        assertEquals(2, threeStack.size());
//    }
}