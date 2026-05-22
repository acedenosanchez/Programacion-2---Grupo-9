package setModule;

public interface SimpleSet<E> {

    public boolean add (E element); //Inserta element si no existe ya en el Set. Devuelve si la operación fue exitosa.
    public boolean remove(E element); //Remueve element si existe en el Set. Devuelve si la operación fue exitosa.
    public boolean contains (E element); //Devuelve si element existe en el Set.
    public void clear(); //Borra todos los elementos del Set, dejándolo vacío.
    public boolean isEmpty(); //Devuelve si el set está vacío.
    public int size(); //Devuelve la cantidad de elementos en la pila.
    public E[] toArray(); //Devuelve un array con todos los elementos del Set.
    public SimpleSet<E> unionWith(SimpleSet<E> other); //Devuelve un Set con todos los contenidos de este Set y other.
    public SimpleSet<E> intersectWith(SimpleSet<E> other); //Devuelve un Set con los elementos que este Set y other tienen en común.
    public SimpleSet<E> differenceWith(SimpleSet<E> other); //Devuelve un Set con los elementos de este set que no están en other.
}
