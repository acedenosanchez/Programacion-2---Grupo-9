package listModule;

public class SimpleArrayList<E> implements SimpleList<E> {

    private int size = 0;
    private E[] elements = null;
    private static final int DEFAULT_CAPACITY = 4;

    @SuppressWarnings("Unchecked")
    //Inicia por el capacity por default
    public SimpleArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("Unchecked")
    //Le indicamos el capacity como firma
    public SimpleArrayList(int capacity) {
        elements = (E[]) new Object[capacity];
    }



    private void validateIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index was less than 0, or greater than size" );
    }

    @Override
    public boolean add(Object element) {
        return false;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(Object object) {
        return false;
    }

    @Override
    public Object get(int index) {
        validateIndex(index);
        return elements[index];
    }

    @Override
    public Object set(int index, Object element) {
        E currentElement = get(index);
        elements[index]=element;
        return currentElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}

