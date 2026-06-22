package grahpModule;

public class Edge<T> {
    public T to;
    public int weight;


    public Edge(T to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    public boolean equals(Object other) {

        //Si el otro objeto ni siquierea es un Edgem no es igual
        if(other.getClass() != this.getClass()) {
            return false;
        }

        //Ahora que se puede casteamos
        Edge<T> otherEdge = (Edge<T>)other;

        //Sino rienen el mismo destino, no es el mismo edge
        if(otherEdge.to != to) return false;


    }
}
