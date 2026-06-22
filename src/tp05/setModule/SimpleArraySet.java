package tp05.setModule;

public class SimpleArraySet<E> implements SimpleSet<E>{

    private int size = 0;
    private E[] elements = null;
    private static final int DEFAULT_CAPACITY = 4;

    @SuppressWarnings("unchecked")
    public SimpleArraySet() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public SimpleArraySet(int initialCapacity) {
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
    public boolean add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        if (contains(element)) {
            return false;
        }

        validateSize(size + 1);
        elements[size] = element;
        size++;

        return true;
    }

    @Override
    public boolean remove(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                elements[i] = elements[size - 1];
                elements[size - 1] = null;
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean contains(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray() {
        E[] result = (E[]) new Object[size];

        for (int i = 0; i < size; i++) {
            result[i] = elements[i];
        }

        return result;
    }

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        if (other == null) {
            throw new IllegalArgumentException("Other set cannot be null.");
        }

        SimpleSet<E> result = new SimpleArraySet<E>();

        for (int i = 0; i < size; i++) {
            result.add(elements[i]);
        }

        E[] otherElements = other.toArray();

        for (int i = 0; i < otherElements.length; i++) {
            result.add(otherElements[i]);
        }

        return result;
    }

    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {
        if (other == null) {
            throw new IllegalArgumentException("Other set cannot be null.");
        }

        SimpleSet<E> result = new SimpleArraySet<E>();

        for (int i = 0; i < size; i++) {
            if (other.contains(elements[i])) {
                result.add(elements[i]);
            }
        }

        return result;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        if (other == null) {
            throw new IllegalArgumentException("Other set cannot be null.");
        }

        SimpleSet<E> result = new SimpleArraySet<E>();

        for (int i = 0; i < size; i++) {
            if (!other.contains(elements[i])) {
                result.add(elements[i]);
            }
        }

        return result;
    }
}
