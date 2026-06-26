package tp07Dictionary.dictionary;

import tp02.tp03.listModule.SimpleList;

public interface SimpleDictionary <K,V> {
    public V put (K key, V value);
    boolean remove (K key);
    public boolean containsKey (K key);
    public V get(K Key);
    public SimpleList<K> keys ();
    public SimpleList<V> values();
    public boolean isEmpty();
    public int size ();
    public void clear();

}
