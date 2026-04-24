package stackModule;

import java.util.NoSuchElementException;

public class SimpleArrayStack <E> implements SimpleStack<E>{
    private int size = 0;
    private E[] elements = null;
    private static final int DEFAULT_CAPACITY = 4;


    @SuppressWarnings("unchecked")
    public SimpleArrayStack(){elements = (E[])new Object[DEFAULT_CAPACITY];}

    @SuppressWarnings("unchecked")
    public SimpleArrayStack(int initialCapacity){elements = (E[])new Object[initialCapacity];}


    private void validateSize(int newSize){
        if (newSize > elements.length) {
            resize();
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(){
        E[] oldElements = elements;
        elements = (E[]) new Object[elements.length * 2];

        for(int i = 0; i<size; i++){
            elements[i] = oldElements[i];
        }
    }

    @Override
    public void push(E element) {
        validateSize(size + 1);
        elements[size++] = element;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack vacía.");
        }
        E value = elements[size-1];
        elements[size-1] = null;
        size--;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack vacía.");
        }

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
}
