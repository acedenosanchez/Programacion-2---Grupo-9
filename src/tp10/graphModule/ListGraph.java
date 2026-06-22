package tp10.graphModule;

import tp07.dictionary.SimpleArrayDictionary;
import tp02.tp03.listModule.SimpleArrayList;
import tp02.tp03.listModule.SimpleList;

public class ListGraph<T> implements Graph<T> {
    private SimpleArrayDictionary<T, SimpleList<Edge<T>>> adjencyList;

    public ListGraph() {
        adjencyList = new SimpleArrayDictionary<T, SimpleList<Edge<T>>>();
    }

    @Override
    public SimpleList<T> vertices() {
        return adjencyList.keys();
    }

    @Override
    public boolean addVertex(T vertex) {
        if (adjencyList.containsKey(vertex)) {
            return false;
        }

        adjencyList.put(vertex, new SimpleArrayList<Edge<T>>());
        return true;
    }

    @Override
    public boolean removeVertex(T vertex) {
        if (!adjencyList.containsKey(vertex)) {
            return false;
        }

        SimpleList<T> vertices = vertices();
        int totalVertices = vertices.size();

        for (int i = 0; i < totalVertices; i++) {
            removeEdge(vertices.get(i), vertex);
        }

        adjencyList.remove(vertex);
        return true;
    }

    @Override
    public boolean addEdge(T from, T to, int weight) {
        addVertex(from);
        addVertex(to);

        Edge<T> edge = getEdge(from, to);

        if (edge == null) {
            adjencyList.get(from).add(new Edge<T>(to, weight));
            return true;
        }

        if (edge.weight != weight) {
            edge.weight = weight;
            return true;
        }

        return false;
    }

    @Override
    public boolean removeEdge(T from, T to) {
        if (!adjencyList.containsKey(from) || !adjencyList.containsKey(to)) {
            return false;
        }

        SimpleList<Edge<T>> edges = adjencyList.get(from);

        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).to.equals(to)) {
                edges.remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public SimpleList<T> getNeighbors(T vertex) {
        SimpleArrayList<T> neighbors = new SimpleArrayList<T>();

        if (!adjencyList.containsKey(vertex)) {
            return neighbors;
        }

        SimpleList<Edge<T>> edges = adjencyList.get(vertex);

        for (int i = 0; i < edges.size(); i++) {
            neighbors.add(edges.get(i).to);
        }

        return neighbors;
    }

    @Override
    public boolean containsVertex(T vertex) {
        return adjencyList.containsKey(vertex);
    }

    @Override
    public boolean containsEdge(T from, T to) {
        return getEdge(from, to) != null;
    }

    @Override
    public int getWeight(T from, T to) {
        Edge<T> edge = getEdge(from, to);

        if (edge == null) {
            return -1;
        }

        return edge.weight;
    }

    @Override
    public int size() {
        return adjencyList.size();
    }

    @Override
    public void clear() {
        adjencyList.clear();
    }

    @Override
    public boolean isEmpty() {
        return adjencyList.isEmpty();
    }

    public void printGraph() {
        SimpleList<T> vertices = vertices();

        for (int i = 0; i < vertices.size(); i++) {
            T from = vertices.get(i);
            SimpleList<Edge<T>> edges = adjencyList.get(from);

            for (int j = 0; j < edges.size(); j++) {
                Edge<T> edge = edges.get(j);
                System.out.println(from + " -> " + edge.to + ": " + edge.weight);
            }
        }
    }

    private Edge<T> getEdge(T from, T to) {
        if (!containsVertex(from) || !containsVertex(to)) {
            return null;
        }

        SimpleList<Edge<T>> edges = adjencyList.get(from);

        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).to.equals(to)) {
                return edges.get(i);
            }
        }

        return null;
    }
}