package listModule;

public class SimpleLinkedList<E> implements SimpleList<E> {

    //Creamos la clase que da la estructura al nodo
    private class Node {
        E element;
        Node next;
        Node previous;

        Node(E element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public SimpleLinkedList() {
        first = null;
        last = null;
        size = 0;
    }


    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index fuera de rango");
        }
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index fuera de rango");
        }
    }

    private Node getNode(int index) {
        validateIndex(index);

        Node current;

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

        Node newNode = new Node(element);

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.previous = last;
            last.next = newNode;
            last = newNode;
        }

        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        validateIndexForAdd(index);

        Node newNode = new Node(element);

        if (index == size) {
            add(element);
            return;
        }

        if (index == 0) {
            newNode.next = first;
            first.previous = newNode;
            first = newNode;
            size++;
            return;
        }

        Node current = getNode(index);
        Node previousNode = current.previous;

        newNode.previous = previousNode;
        newNode.next = current;

        previousNode.next = newNode;
        current.previous = newNode;

        size++;
    }


    @Override
    public E remove(int index) {
        validateIndex(index);

        Node nodeToRemove = getNode(index);
        E removedElement = nodeToRemove.element;

        if (size == 1) {
            first = null;
            last = null;
        } else if (nodeToRemove == first) {
            first = first.next;
            first.previous = null;
        } else if (nodeToRemove == last) {
            last = last.previous;
            last.next = null;
        } else {
            nodeToRemove.previous.next = nodeToRemove.next;
            nodeToRemove.next.previous = nodeToRemove.previous;
        }

        size--;
        return removedElement;
    }

    @Override
    public boolean remove(Object object) {
        Node current = first;

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
        Node current = first;

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
    public E set(int index, E element) { Node node = getNode(index);
        E oldElement = node.element;
        node.element = element;
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
