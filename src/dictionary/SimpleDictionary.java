package dictionary;


public interface SimpleDictionary <K,V> {
    public V put (K key, V value);
    boolean remove (K key);
    public boolean containsKey (K key);
    public V get(K Key);
    public K[] keys ();
    public V[] values();
    public boolean isEmpty();
    public int size ();
    public void clear();

}
