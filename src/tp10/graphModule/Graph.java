package tp10.graphModule;

import tp02.tp03.listModule.SimpleList;

public interface Graph<T> {
    public SimpleList<T> vertices();
    public boolean  addVertex (T vertex);
    public boolean removeVertex (T vertex);
    public boolean addEdge (T from, T to,int weight);
    public boolean removeEdge (T from, T to);
    public SimpleList<T> getNeighbors (T vertex);
    public boolean containsVertex (T vertex);
    public boolean containsEdge (T from, T to);
    public int getWeight(T from, T to);
    public int size();
    public void clear();
    public boolean isEmpty();
}
