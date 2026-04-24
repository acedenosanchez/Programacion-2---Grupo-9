package queueModule;

import listModule.SimpleLinkedList;

import java.util.Queue;

public class SimpleLinkedQueue<E> implements SimpleQueue<E> {
    private class LinkedNode {
        E element;
        SimpleLinkedQueue.LinkedNode next;
        SimpleLinkedQueue.LinkedNode previous;

        LinkedNode(E element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }
    }

    private SimpleLinkedQueue.LinkedNode first;
    private SimpleLinkedQueue.LinkedNode last;
    private int size;

    @Override
    public void enqueue(E element) {

    }

    @Override
    public E dequeue() {
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
