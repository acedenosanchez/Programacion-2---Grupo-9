package queueModule;

public interface SimpleQueue<E>{

    public void unqueue();
    public E dequeue();
    public boolean isEmpty();
    public int size();
    public E peek();
    public void clear();
}
