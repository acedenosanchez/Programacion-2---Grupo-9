package tp06PriorityQueue.priorityQueue;

public interface PriorityQueue <E> {
    public void enqueue (E elem , int priority);
    public E dequeue ();
    public int getHighestPriority ();
    public boolean isEmpty();
    public int size();
    public E peek();
    public void clear();

}
