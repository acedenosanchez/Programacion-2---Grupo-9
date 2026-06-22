package tp04.queueModule;

public interface SimpleQueue<E>{

    public void enqueue(E element);
    public E dequeue();
    public boolean isEmpty();
    public int size();
    public E peek();
    public void clear();
}
