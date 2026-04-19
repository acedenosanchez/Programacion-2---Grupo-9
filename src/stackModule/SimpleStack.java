package stackModule;

public interface SimpleStack <E> {
    public void push(E element);
    public E pop();
    public E peek();
    public boolean isEmpty();
    public void clear();
    public int size();
}
