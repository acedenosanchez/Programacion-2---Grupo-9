package tp06PriorityQueue.priorityQueue;

public class SimpleArrayPriorityQueue<E> implements PriorityQueue<E> {

    private static final int DEFAULT_CAPACITY = 4;

    private E[] elements;
    private int[] priorities;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public SimpleArrayPriorityQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        priorities = new int[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public SimpleArrayPriorityQueue(int initialCapacity) {
        elements = (E[]) new Object[initialCapacity];
        priorities = new int[initialCapacity];
    }


    @Override
    public void enqueue(E elem, int priority) {
        validateElement(elem);
        validatePriority(priority);
        validateSize(size + 1);

        int pos = 0;
        while (pos < size && priorities[pos] <= priority) {
            pos++;
        }

        for (int i = size; i > pos; i--) {
            elements[i] = elements[i - 1];
            priorities[i] = priorities[i - 1];
        }

        elements[pos] = elem;
        priorities[pos] = priority;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("No hay elementos en la cola.");
        }

        E first = elements[0];

        for (int i = 0; i < size - 1; i++) {
            elements[i] = elements[i + 1];
            priorities[i] = priorities[i + 1];
        }

        elements[size - 1] = null;
        priorities[size - 1] = 0;
        size--;

        return first;
    }

    @Override
    public int getHighestPriority() {
        if (isEmpty()) {
            throw new IllegalStateException("No hay elementos en la cola.");
        }
        return priorities[0];
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
            throw new IllegalStateException("No hay elementos en la cola.");
        }
        return elements[0];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
            priorities[i] = 0;
        }
        size = 0;
    }


    //Métodos de servicio
    private void validateSize(int newSize) {
        if (newSize > elements.length) {
            resize();
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        E[] oldElements = elements;
        elements = (E[]) new Object[elements.length * 2];

        int[] oldPriorities = priorities;
        priorities = new int[priorities.length * 2];

        for (int i = 0; i < size; i++) {
            elements[i] = oldElements[i];
            priorities[i] = oldPriorities[i];
        }
    }

    private void validateElement(E elem) {
        if (elem == null) {
            throw new IllegalArgumentException("El elemento no puede ser null.");
        }
    }

    private void validatePriority(int priority) {
        if (priority < 0) {
            throw new IllegalArgumentException("La prioridad no puede ser negativa.");
        }
    }
}
