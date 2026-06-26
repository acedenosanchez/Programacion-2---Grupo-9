package tp07Dictionary.dictionary;

import tp02.tp03.listModule.SimpleList;
import tp02.tp03.listModule.SimpleArrayList;

public class SimpleLinkedDictionary<K, V> implements SimpleDictionary<K, V> {

    private class LinkedNode {
        K key;
        V value;
        LinkedNode next;
        LinkedNode previous;

        LinkedNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.previous = null;
        }
    }

    private LinkedNode first;
    private LinkedNode last;
    private int size;

    @Override
    public V put(K key, V value) {
        validateKey(key);
        validateValue(value);

        LinkedNode current = searchNode(key);

        if (current != null) {
            V oldValue = current.value;
            current.value = value;
            return oldValue;
        }

        LinkedNode newNode = new LinkedNode(key, value);

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.previous = last;
            last = newNode;
        }

        size++;
        return null;
    }

    @Override
    public boolean remove(K key) {
        validateKey(key);

        if (isEmpty()) {
            return false;
        }

        LinkedNode current = searchNode(key);

        if (current == null) {
            return false;
        }

        if (current == first && current == last) {
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

        current.next = null;
        current.previous = null;
        size--;

        return true;
    }

    @Override
    public boolean containsKey(K key) {
        validateKey(key);
        return searchNode(key) != null;
    }

    @Override
    public V get(K key) {
        validateKey(key);

        LinkedNode node = searchNode(key);

        if (node == null) {
            return null;
        }

        return node.value;
    }

    @Override
    public SimpleList<K> keys() {
        SimpleList<K> result = new SimpleArrayList<K>(size);

        LinkedNode current = first;

        while (current != null) {
            result.add(current.key);
            current = current.next;
        }

        return result;
    }

    @Override
    public SimpleList<V> values() {
        SimpleList<V> result = new SimpleArrayList<V>(size);

        LinkedNode current = first;

        while (current != null) {
            result.add(current.value);
            current = current.next;
        }

        return result;
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
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }


    // Métodos de servicio
    private LinkedNode searchNode(K key) {
        LinkedNode current = first;

        while (current != null) {
            if (current.key.equals(key)) {
                return current;
            }
            current = current.next;
        }

        return null;
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser null.");
        }
    }

    private void validateValue(V value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser null.");
        }
    }

}
