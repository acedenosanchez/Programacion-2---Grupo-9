package queueModule;

import listModule.SimpleLinkedList;


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
      E removedElement = first.element;
      first = first.next;
       if(first == null) {
           last = null;
       }
        size--;
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void clear() {

    }
}
