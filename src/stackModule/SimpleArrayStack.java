package stackModule;

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
    private void resize() {
        E[] oldElements = elements;
        elements = (E[]) new Object[elements.length * 2];

    }

    @Override
    public void push(E element) {
        if(elements[0] == null){
            elements[0] = element;
        }
        else{
            validateSize(size+1);
        }
        elements[size]=element;
        size++;
    }

    @Override
    public E pop() {
        E popElement = elements[size-1];
        elements[size-1] = null;
        size--;
        return popElement;
    }

    @Override
    public E peek() {
        return elements[size-1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        elements = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }
}
