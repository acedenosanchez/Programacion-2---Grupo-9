package priorityQueue;

import java.security.PrivateKey;

public class SimpleLinkedPriorityQueue <E> implements PriorityQueue <E> {

    private class LinkedNode {
        E element;
        LinkedNode next;
        LinkedNode last;
        int priority;

        LinkedNode(E element) {
            super();
        }
    }

    @Override
    public void enqueue(Object elem, int priority) {

    }

    @Override
    public int getHighestPriority() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void clear() {

    }
}
