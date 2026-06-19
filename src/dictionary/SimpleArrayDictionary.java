package dictionary;

import listModule.SimpleArrayList;
import listModule.SimpleList;

import java.util.ArrayList;
import java.util.List;

public class SimpleArrayDictionary<K, V> implements SimpleDictionary<K, V> {

    private static final int DEFAULT_CAPACITY = 4;

    private K[] keys;
    private V[] values;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public SimpleArrayDictionary() {
        keys = (K[]) new Object[DEFAULT_CAPACITY];
        values = (V[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public SimpleArrayDictionary(int initialCapacity) {
        validateCapacity(initialCapacity);
        keys = (K[]) new Object[initialCapacity];
        values = (V[]) new Object[initialCapacity];
    }

    @Override
    public V put(K key, V value) {
        validateKey(key);
        validateValue(value);

        int index = searchKey(key);

        if (index != -1) {
            V oldValue = values[index];
            values[index] = value;
            return oldValue;
        }

        validateSize(size + 1);

        keys[size] = key;
        values[size] = value;
        size++;

        return null;
    }

    @Override
    public boolean remove(K key) {
        validateKey(key);

        int index = searchKey(key);

        if (index == -1) {
            return false;
        }

        for (int i = index; i < size - 1; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }

        keys[size - 1] = null;
        values[size - 1] = null;
        size--;

        return true;
    }

    @Override
    public boolean containsKey(K key) {
        validateKey(key);
        return searchKey(key) != -1;
    }

    @Override
    public V get(K key) {
        validateKey(key);

        int index = searchKey(key);

        if (index == -1) {
            return null;
        }

        return values[index];
    }

    @Override
    public SimpleList<K> keys() {
        SimpleList<K> result = new SimpleArrayList<K>(size);

        for (int i = 0; i < size; i++) {
            result.add(keys[i]);
        }

        return result;
    }

    @Override
    public SimpleList<V> values() {
        SimpleList<V> result = new SimpleArrayList<V>(size);

        for (int i = 0; i < size; i++) {
            result.add(values[i]);
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
        for (int i = 0; i < size; i++) {
            keys[i] = null;
            values[i] = null;
        }
        size = 0;
    }


    // Métodos de servicio
    private void validateSize(int newSize) {
        if (newSize > keys.length) {
            resize();
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        K[] oldKeys = keys;
        V[] oldValues = values;

        keys = (K[]) new Object[keys.length * 2];
        values = (V[]) new Object[values.length * 2];

        for (int i = 0; i < size; i++) {
            keys[i] = oldKeys[i];
            values[i] = oldValues[i];
        }
    }

    private int searchKey(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
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

    private void validateCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("La capacidad inicial debe ser mayor a cero.");
        }
    }
}
