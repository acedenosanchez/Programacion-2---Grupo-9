package setModule;

public class SimpleLinkedSet<E> implements SimpleSet<E>{

    private class LinkedNode {
        E element;
        LinkedNode next;

        LinkedNode(E element) {
            this.element = element;
            this.next = null;
        }
    }

    private LinkedNode first;
    private int size;

    public SimpleLinkedSet() {
        first = null;
        size = 0;
    }

    @Override
    public boolean add(E element) {

    }

    @Override
    public boolean remove(E element) {
        return false;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public void clear() {

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
    public E[] toArray() {
        return null;
    }

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        return null;
    }

    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {
        return null;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        return null;
    }
}
