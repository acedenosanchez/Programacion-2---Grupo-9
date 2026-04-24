package queueModule;


import java.util.NoSuchElementException;

public class SimpleLinkedQueue<E> implements SimpleQueue<E> {
    private class LinkedNode {
        E element;
        LinkedNode next;
        LinkedNode previous;

        //Constructor
        LinkedNode(E element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }
    }

        private LinkedNode first;
        private LinkedNode last;
        private int size;

    public SimpleLinkedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void enqueue(E element) {
        LinkedNode newLinkedNode = new LinkedNode(element);

        if (isEmpty()) {
            first = newLinkedNode;
            last = newLinkedNode;
        } else {
            newLinkedNode.previous = last;
            last.next = newLinkedNode;
            last = newLinkedNode;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

      E removedElement = first.element;
      //modificá el primer nodo
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
        return removedElement;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.element;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }
}
