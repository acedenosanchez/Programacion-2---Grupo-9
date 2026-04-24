package listModule;

public class SimpleArrayList<E> implements SimpleList<E> {

    private int size = 0;
    private E[] elements = null;
    private static final int DEFAULT_CAPACITY = 4;

    //Inicia por el capacity por default
    @SuppressWarnings("unchecked")
    public SimpleArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    //Le indicamos el capacity como firma
    public SimpleArrayList(int capacity) {
        elements = (E[]) new Object[capacity];
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index was less than 0, or greater than size.");
    }

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

    @SuppressWarnings("unchecked")
    @Override

    public boolean add(E element) {
        validateSize(size+1);
        elements[size++] = element;
        return true;
    }

    @Override
    public void add(int index, E element) {
        //Verificamos que el indice sea correcto
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index was less than 0, or greater than size.");
        }

        validateSize(size+1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        size++;

        System.out.println("The element " + element.toString() + " has been added.");
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

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override

    //Comentarios para el profe: Mayor eficiencia si devolvemos directamente el indice donde se encuentra el valor y en caso de no encontrarse devolver -1 , así no se tiene que iterar nuevamente
    public boolean contains(Object object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                return true;
            }

        }
        return false;
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index);
        E old = elements[index];
        elements[index] = element;
        return old;
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



