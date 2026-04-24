package test;

import listModule.*;
import stackModule.*;
import queueModule.*;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DataStructuresTest {

    private SimpleList<String>[] lists() {
        return new SimpleList[]{
                new SimpleArrayList<String>(),
                new SimpleLinkedList<String>()
        };
    }

    private SimpleStack<String>[] stacks() {
        return new SimpleStack[]{
                new SimpleArrayStack<String>(),
                new SimpleLinkedStack<String>()
        };
    }

    private SimpleQueue<String>[] queues() {
        return new SimpleQueue[]{
                new SimpleArrayQueue<String>(),
                new SimpleLinkedQueue<String>()
        };
    }

    @Test
    void listStartsEmpty() {
        for (SimpleList<String> list : lists()) {
            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
        }
    }

    @Test
    void listAddAndGetWorks() {
        for (SimpleList<String> list : lists()) {
            assertTrue(list.add("A"));
            assertTrue(list.add("B"));
            assertTrue(list.add("C"));

            assertEquals(3, list.size());
            assertEquals("A", list.get(0));
            assertEquals("B", list.get(1));
            assertEquals("C", list.get(2));
        }
    }

    @Test
    void listAddByIndexWorksAtBeginningMiddleAndEnd() {
        for (SimpleList<String> list : lists()) {
            list.add("A");
            list.add("C");

            list.add(1, "B");
            list.add(0, "START");
            list.add(list.size(), "END");

            assertEquals("START", list.get(0));
            assertEquals("A", list.get(1));
            assertEquals("B", list.get(2));
            assertEquals("C", list.get(3));
            assertEquals("END", list.get(4));
            assertEquals(5, list.size());
        }
    }

    @Test
    void listRemoveByIndexWorks() {
        for (SimpleList<String> list : lists()) {
            list.add("A");
            list.add("B");
            list.add("C");

            assertEquals("B", list.remove(1));

            assertEquals(2, list.size());
            assertEquals("A", list.get(0));
            assertEquals("C", list.get(1));
        }
    }

    @Test
    void listRemoveByObjectWorks() {
        for (SimpleList<String> list : lists()) {
            list.add("A");
            list.add("B");
            list.add("C");

            assertTrue(list.remove("B"));
            assertFalse(list.remove("X"));

            assertEquals(2, list.size());
            assertEquals("A", list.get(0));
            assertEquals("C", list.get(1));
        }
    }

    @Test
    void listSetAndContainsWork() {
        for (SimpleList<String> list : lists()) {
            list.add("A");
            list.add("B");

            assertEquals("B", list.set(1, "Z"));

            assertTrue(list.contains("A"));
            assertTrue(list.contains("Z"));
            assertFalse(list.contains("B"));
            assertEquals("Z", list.get(1));
        }
    }

    @Test
    void listClearWorksAndCanBeReused() {
        for (SimpleList<String> list : lists()) {
            list.add("A");
            list.add("B");

            list.clear();

            assertTrue(list.isEmpty());
            assertEquals(0, list.size());

            list.add("C");

            assertEquals(1, list.size());
            assertEquals("C", list.get(0));
        }
    }

    @Test
    void listInvalidIndexesThrowException() {
        for (SimpleList<String> list : lists()) {
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
            assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "X"));

            list.add("A");

            assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
            assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "X"));
            assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, "X"));
        }
    }

    @Test
    void stackStartsEmpty() {
        for (SimpleStack<String> stack : stacks()) {
            assertTrue(stack.isEmpty());
            assertEquals(0, stack.size());
        }
    }

    @Test
    void stackPushPeekAndPopUseLifoOrder() {
        for (SimpleStack<String> stack : stacks()) {
            stack.push("A");
            stack.push("B");
            stack.push("C");

            assertEquals(3, stack.size());
            assertEquals("C", stack.peek());
            assertEquals(3, stack.size());

            assertEquals("C", stack.pop());
            assertEquals("B", stack.pop());
            assertEquals("A", stack.pop());

            assertTrue(stack.isEmpty());
            assertEquals(0, stack.size());
        }
    }

    @Test
    void stackClearWorksAndCanBeReused() {
        for (SimpleStack<String> stack : stacks()) {
            stack.push("A");
            stack.push("B");

            stack.clear();

            assertTrue(stack.isEmpty());
            assertEquals(0, stack.size());

            stack.push("C");

            assertEquals("C", stack.peek());
            assertEquals(1, stack.size());
        }
    }

    @Test
    void stackPopAndPeekOnEmptyThrowException() {
        for (SimpleStack<String> stack : stacks()) {
            assertThrows(NoSuchElementException.class, stack::pop);
            assertThrows(NoSuchElementException.class, stack::peek);
        }
    }

    @Test
    void queueStartsEmpty() {
        for (SimpleQueue<String> queue : queues()) {
            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
        }
    }

    @Test
    void queueEnqueuePeekAndDequeueUseFifoOrder() {
        for (SimpleQueue<String> queue : queues()) {
            queue.enqueue("A");
            queue.enqueue("B");
            queue.enqueue("C");

            assertEquals(3, queue.size());
            assertEquals("A", queue.peek());
            assertEquals(3, queue.size());

            assertEquals("A", queue.dequeue());
            assertEquals("B", queue.dequeue());
            assertEquals("C", queue.dequeue());

            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
        }
    }

    @Test
    void queueWorksWhenInternalArrayIsFull() {
        for (SimpleQueue<String> queue : queues()) {
            queue.enqueue("A");
            queue.enqueue("B");
            queue.enqueue("C");
            queue.enqueue("D");

            assertEquals("A", queue.dequeue());
            assertEquals("B", queue.dequeue());
            assertEquals("C", queue.dequeue());
            assertEquals("D", queue.dequeue());

            assertTrue(queue.isEmpty());
        }
    }

    @Test
    void queueClearWorksAndCanBeReused() {
        for (SimpleQueue<String> queue : queues()) {
            queue.enqueue("A");
            queue.enqueue("B");

            queue.clear();

            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());

            queue.enqueue("C");

            assertEquals("C", queue.peek());
            assertEquals(1, queue.size());
        }
    }

    @Test
    void queueDequeueAndPeekOnEmptyThrowException() {
        for (SimpleQueue<String> queue : queues()) {
            assertThrows(NoSuchElementException.class, queue::dequeue);
            assertThrows(NoSuchElementException.class, queue::peek);
        }
    }
}