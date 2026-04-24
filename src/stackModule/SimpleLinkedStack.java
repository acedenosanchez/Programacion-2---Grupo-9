package stackModule;
import java.util.NoSuchElementException;

public class SimpleLinkedStack<E>  implements SimpleStack<E>{

    private LinkedNode top;
    private int size;

    private class LinkedNode {
        E element;
        LinkedNode previous;

        LinkedNode(E element) {
            this.element = element;
            this.previous = null;
        }
    }
    @Override
    public void push(E element) {
        LinkedNode newNode = new LinkedNode(element);
        newNode.previous = top;
        top = newNode;
        size ++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack vacía.");
        }

        E value = top.element;
        top = top.previous;
        size --;

        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack vacía.");
        }
        return top.element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }
}
