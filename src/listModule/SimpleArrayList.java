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
            throw new IndexOutOfBoundsException("Index was less than 0, or greater than size");
    }

    @Override
    public boolean add(E element) {
        if (size == elements.length) {
            E[] newArray = (E[]) new Object[elements.length * 2];
            for (int i = 0; i < size; i++) {
                newArray[i] = elements[i];
            }
            elements = newArray;
        }

        elements[size] = e;
        size++;

        return true;
    }

    @Override
    public void add(int index, E element) {
        //Verificamos que el indice sea correcto
        if (index < 0 || index > elements.length) {
            throw new IndexOutOfBoundsException("Index was less than 0, or greater than size");
        } else if (size + 1 > elements.length) {
            throw new IndexOutOfBoundsException("Too many elements");
        }
        elements[index] = element;
        size++;
        System.out.println("The element " + element.toString() + " has been added");
    }

    @Override
    public E remove(int index) {
        validateIndex(index);

        E removedElement = elements[index];

        for (int i = index; i < size -1; i++) {
            elements[i] = elements[i+1];
        }

        elements[size - 1] = null;

        size--;

        return removedElement;
    }
    }

    @Override
    public boolean remove(Object object) {
        //podriamos ahorrarnos este if si contains devolviera el indice
        if (contains(object)) {
            int index = -1;
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(object)) {
                    index = i;
                }
            }
            if (index >= 0) {
                for (int i = index; i < size-1; i++) {
                    elements[i] = elements[i + 1];
                }
                elements[size-1] = null;
                size--;
            }
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        E[] elements = null;
        size = 0;
    }

    @Override

    //Comentarios para el profe: Mayor eficiencia si devolvemos directamente el indice donde se encuentra el valor y en caso de no encontrarse devolver -1 , así no se tiene que iterar nuevamente
    public boolean contains(Object object) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == object) {
                return true;
            }

        }
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}

