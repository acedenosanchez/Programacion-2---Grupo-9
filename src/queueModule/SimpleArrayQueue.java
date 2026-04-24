package queueModule;

import java.util.NoSuchElementException;

public class SimpleArrayQueue<E> implements SimpleQueue<E> {

    private int size = 0;
    private E[] elements = null;
    private static final int DEFAULT_CAPACITY = 4;

    @SuppressWarnings("unchecked")
    public SimpleArrayQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public SimpleArrayQueue(int initialCapacity) {
        elements = (E[]) new Object[initialCapacity];
    }


    private void validateSize(int newSize) {
        if (newSize > elements.length) {
            resize();
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        E[] oldElements = elements;
        elements = (E[]) new Object[elements.length * 2];

        for (int i = 0; i < size; i++) {
            elements[i] = oldElements[i];
        }
    }

    @Override
    public void enqueue(E element) {
        if (size == 0) {
            elements[0] = element;
        }
        validateSize(size + 1);
        elements[size++] = element;
    }


    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E primerValor = elements[0];
        for (int i = 0; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return primerValor;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }
}

