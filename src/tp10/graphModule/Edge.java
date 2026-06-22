package tp10.graphModule;

public class Edge<T> {
    public T to;
    public int weight;


    public Edge(T to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    public boolean equals(Object other) {

        if (other == null) {
            return false;
        }

        //Si el otro objeto ni siquiera es un Edge no es igual
        if(other.getClass() != this.getClass()) {
            return false;
        }

        //Ahora que se puede casteamos
        Edge<T> otherEdge = (Edge<T>) other;

        //Sino rienen el mismo destino, no es el mismo edge
        if (!otherEdge.to.equals(to)) {
            return false;
        }

        return true;
    }
}
