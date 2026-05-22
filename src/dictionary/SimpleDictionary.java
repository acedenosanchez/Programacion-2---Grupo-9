package dictionary;


public interface SimpleDictionary <K,V> {
    public V put (K key, V value);
    public V get(K Key);
}
