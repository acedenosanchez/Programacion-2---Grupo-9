package listModule;

public class SimpleLinkedList<E> implements SimpleList<E> {

    //Creamos la clase que da la estructura al nodo
    private class LinkedNode {
        E element;
        LinkedNode next;
        LinkedNode previous;

        LinkedNode(E element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }
    }

    private LinkedNode first;
    private LinkedNode last;
    private int size;

    public SimpleLinkedList() {
        first = null;
        last = null;
        size = 0;
    }


    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }
    }

    private LinkedNode getNode(int index) {
        validateIndex(index);

        LinkedNode current;

        if (index < size / 2) {
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }

        return current;
    }

    @Override
    public boolean add(E element) {

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
        return true;
    }

    @Override
    public void add(int index, E element) {
        validateIndexForAdd(index);

        LinkedNode newLinkedNode = new LinkedNode(element);

        if (index == size) {
            add(element);
            return;
        }

        if (index == 0) {
            newLinkedNode.next = first;
            first.previous = newLinkedNode;
            first = newLinkedNode;
            size++;
            return;
        }

        LinkedNode current = getNode(index);
        LinkedNode previousLinkedNode = current.previous;

        newLinkedNode.previous = previousLinkedNode;
        newLinkedNode.next = current;

        previousLinkedNode.next = newLinkedNode;
        current.previous = newLinkedNode;

        size++;
    }


    @Override
    public E remove(int index) {
        validateIndex(index);

        LinkedNode linkedNodeToRemove = getNode(index);
        E removedElement = linkedNodeToRemove.element;

        if (size == 1) {
            first = null;
            last = null;
        } else if (linkedNodeToRemove == first) {
            first = first.next;
            first.previous = null;
        } else if (linkedNodeToRemove == last) {
            last = last.previous;
            last.next = null;
        } else {
            linkedNodeToRemove.previous.next = linkedNodeToRemove.next;
            linkedNodeToRemove.next.previous = linkedNodeToRemove.previous;
        }

        size--;
        return removedElement;
    }

    @Override
    public boolean remove(Object object) {
        LinkedNode current = first;

        while (current != null) {
            if ((object == null && current.element == null) ||
                    (object != null && object.equals(current.element))) {

                if (size == 1) {
                    first = null;
                    last = null;
                } else if (current == first) {
                    first = first.next;
                    first.previous = null;
                } else if (current == last) {
                    last = last.previous;
                    last.next = null;
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }

                size--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;

    }

    @Override
    public boolean contains(Object object) {
        LinkedNode current = first;

        while (current != null) {
            if ((object == null && current.element == null) ||
                    (object != null && object.equals(current.element))) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    @Override
    public E set(int index, E element) { LinkedNode linkedNode = getNode(index);
        E oldElement = linkedNode.element;
        linkedNode.element = element;
        return oldElement;
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
