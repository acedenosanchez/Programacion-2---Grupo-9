package tp05.setModule;

public class SimpleLinkedSet<E> implements SimpleSet<E> {

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
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        if (contains(element)) {
            return false;
        }

        LinkedNode newNode = new LinkedNode(element);
        newNode.next = first;
        first = newNode;
        size++;

        return true;
    }

    @Override
    public boolean remove(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        if (isEmpty()) {
            return false;
        }

        if (first.element.equals(element)) {
            first = first.next;
            size--;
            return true;
        }

        LinkedNode current = first;

        while (current.next != null) {
            if (current.next.element.equals(element)) {
                current.next = current.next.next;
                size--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public boolean contains(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        LinkedNode current = first;

        while (current != null) {
            if (current.element.equals(element)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public void clear() {
        first = null;
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

        LinkedNode current = first;
        int index = 0;

        while (current != null) {
            result[index] = current.element;
            index++;
            current = current.next;
        }

        return result;
    }

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        if (other == null) {
            throw new IllegalArgumentException("Other set cannot be null.");
        }

        SimpleSet<E> result = new SimpleLinkedSet<E>();

        LinkedNode current = first;

        while (current != null) {
            result.add(current.element);
            current = current.next;
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

        SimpleSet<E> result = new SimpleLinkedSet<E>();

        LinkedNode current = first;

        while (current != null) {
            if (other.contains(current.element)) {
                result.add(current.element);
            }

            current = current.next;
        }

        return result;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        if (other == null) {
            throw new IllegalArgumentException("Other set cannot be null.");
        }

        SimpleSet<E> result = new SimpleLinkedSet<E>();

        LinkedNode current = first;

        while (current != null) {
            if (!other.contains(current.element)) {
                result.add(current.element);
            }

            current = current.next;
        }

        return result;
    }
}