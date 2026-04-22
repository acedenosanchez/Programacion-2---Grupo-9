package stackModule;

public class SimpleArrayStack <E> implements SimpleStack<E> {

    private int size = 0;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 4;

    @SuppressWarnings("unchecked")
    public SimpleArrayStack() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void push(E element) {
        if (size == elements.length) {
            resize();
        }
        elements[size] = element;
        size++;
    }

    @Override
    public E pop() {
        E removed = elements[size - 1];
        elements[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public E peek() {
        return elements[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        E[] newArray = (E[]) new Object[elements.length * 2];

        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }

        elements = newArray;
    }
}