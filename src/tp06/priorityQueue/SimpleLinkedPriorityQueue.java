package tp06.priorityQueue;

import java.util.NoSuchElementException;

public class SimpleLinkedPriorityQueue<E> implements PriorityQueue<E> {

    private class LinkedNode {
        E element;
        int priority;
        LinkedNode next;
        LinkedNode previous;

        LinkedNode(E element, int priority) {
            this.element = element;
            this.priority = priority;
            this.next = null;
            this.previous = null;
        }
    }

    private LinkedNode first;
    private LinkedNode last;
    private int size;

    @Override
    public void enqueue(E elem, int priority) {
        validateElement(elem);
        validatePriority(priority);

        LinkedNode newNode = new LinkedNode(elem, priority);

        if (first == null || priority < first.priority) {
            newNode.next = first;
            first = newNode;
        } else {
            LinkedNode current = first;
            while (current.next != null && current.next.priority <= priority) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay elementos en la cola.");
        }

        E element = first.element;
        first = first.next;


        //Verificamos si la cola quedo vacia
        if(first == null) {
            last = null;
        }

        //elimino la conexión con el nodo anterior que salio de la cola
        else{
            first.previous = null;
        }

        size--;

        return element;


    }

    @Override
    public int getHighestPriority() {
        if (isEmpty()) {
            throw new IllegalStateException("No hay elementos en la cola.");
        }
        return first.priority;
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
        return first.element;
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
    }


    //Métodos de servicio
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
