package tp10.graphModule;

import tp07.dictionary.SimpleArrayDictionary;
import tp02.tp03.listModule.SimpleArrayList;
import tp02.tp03.listModule.SimpleList;

public class ListGraph<T> implements Graph<T> {
   private SimpleArrayDictionary <T, SimpleList<Edge<T>>> adjencyList;


   public ListGraph() {
       adjencyList = new SimpleArrayDictionary<T , SimpleList<Edge<T>>>();
   }


    @Override
    public SimpleList<T> vertices() {
        return adjencyList.keys();
    }

    @Override
    public boolean addVertex(T vertex) {
       if(adjencyList.containsKey(vertex)) {return false;}

       adjencyList.put(vertex,new SimpleArrayList<Edge<T>>());
        return false;
    }

    @Override
    public boolean removeVertex(T vertex) {
       //No podemos remocer si no existe de antes
        if(!adjencyList.containsKey(vertex)) {return false;}

        //Creamos una lista con los vertices
        SimpleList<T> vertices = vertices();
        int size = size();

        //Recorremos el arreglo y eliminamos los edges que vayan del vertice A al vertice que querramos eliminar
        for(int i=0; i<size; i++) {
            removeEdge(vertices.get(i), vertex);
        }
        return false;
    }

    @Override
    public boolean addEdge(T from, T to, int weight) {
       //addVertex ya chequea si existen
       addVertex(from);
       addVertex(to);

       Edge<T> edge = getEdge(from,to);

       //Si no existe el edge
       if(edge==null) {
           //Agregamos al vertice el nuevo edge
           adjencyList.get(from).add(new Edge<T>(to,weight));
           return true;
       }

       //si existe pero tiene otro weigth , actualizamos
       if(edge.weight != weight) {
           edge.weight = weight;
           return true;
       }

        //sin cambios
        return false;
    }



    @Override
    public boolean removeEdge(T from, T to) {
       if(!adjencyList.containsKey(from) ||
        !adjencyList.containsKey(to)) return false;

       //Buscamos todas las aristas que van desde From
       SimpleList<Edge<T>> edges = adjencyList.get(from);

       //Si la arista va a to, la removemos
       for(int i=0; i< edges.size(); i++)
           if(edges.get(i).to.equals(to)) {
               edges.remove(i);
               return true;
           }

       //Sin cambios
        return false;
    }

    @Override
    public SimpleList<T> getNeighbors(T vertex) {
        return null;
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

       //Si no esta la arista, devolvemos un valor invalido
        if(edge == null) return -1;
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

    private Edge<T> getEdge(T from, T to) {

       if(!containsVertex(from) || !containsVertex(to)) return null;

       SimpleList <Edge<T>> edges = adjencyList.get(from);
       int edgeSize = edges.size();

       for(int i = 0; i < edgeSize; i++) {
           if(edges.get(i).to.equals(to)) return edges.get(i);
       }
    return null;
    }
}
