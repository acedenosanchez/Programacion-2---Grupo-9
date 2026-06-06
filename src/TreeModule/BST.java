package TreeModule;

import com.sun.source.tree.Tree;
import listModule.SimpleArrayList;
import listModule.SimpleLinkedList;
import listModule.SimpleList;
import priorityQueue.SimpleArrayPriorityQueue;
import queueModule.SimpleArrayQueue;

public class BST<E extends Comparable<E>> {
    public TreeNode<E> root = null;
    int size;

    public void insert(E value){
        root = insertRecursive(root, value);

    }

    private TreeNode<E> insertRecursive(TreeNode<E> current, E value)
    {
        if(current == null)
        {
            size ++;
            return new TreeNode<E>(value);
        }

        //Comparación generica: -1 "menor", 0 "igual", 1 "mayor"
        int comparison = value.compareTo(current.value);

        if(comparison < 0)
            current.left = insertRecursive(current.left, value);
        else if(comparison > 0)
            current.right = insertRecursive(current.right, value);

        // No aceptamos duplicados, siempre al final devolvemos current
        return current;
    }
    public void remove(E value)
    {
        root = removeRecursive(root, value);
    }
    private TreeNode<E> removeRecursive(TreeNode<E> current, E value)
    {
        if(current == null) return null;
        //Comparación generica: -1 "menor", 0 "igual", 1 "mayor"
        int comparison = value.compareTo(current.value);

        if(comparison < 0)
            current.left = removeRecursive(current.left, value);
        else if(comparison > 0)
            current.right = removeRecursive(current.right, value);

        else
        {

            //Case 1: Sin hijos(hoja)
            if(current.left == null && current.right == null) {
                size--;
                return null;
            }

            //Case 2: un solo hijo
            if(current.right == null)
            {   size --;
                return current.left;
            }

            if(current.left == null)
            {   size --;
                return current.right;
            }

            //Case 3: dos hijos
            TreeNode<E> successor = getSmallestChild(current.right);
            current.value = successor.value;
            current.right = removeRecursive(current.right, successor.value);
        }

        return current;
    }

    private TreeNode<E> getSmallestChild(TreeNode<E> current)
    {
        while(current.left != null)
            current = current.left;

        return current;
    }

    public void clear() { root = null; }

    public boolean contains(E value)
    {
        return containsRecursive(root, value);
    }

    public boolean containsRecursive(TreeNode<E> current, E value)
    {
        if(current == null)
            return false;

        int comparison = value.compareTo(current.value);

        if (comparison == 0)
            return true;
        if(comparison < 0)
            return containsRecursive(current.left, value);

        else return containsRecursive(current.right, value);
    }

    public int size() {return size;}

    public boolean isEmpty() {return size() == 0;}

    public SimpleList<E> preOrder()
    {
        SimpleList<E> result = new SimpleArrayList<E>();
        return result;
    }

    public SimpleList<E> inOrder()
    {
        SimpleList<E> result = new SimpleArrayList<E>();
        return result;
    }

    public SimpleList<E> postOrder()
    {
        SimpleList<E> result = new SimpleArrayList<E>();
        return result;
    }

    private void preOrderDFS(TreeNode<E> current, SimpleList<E> list)
    {
        if(current == null) return;
        list.add(current.value);
        preOrderDFS(current.left, list);
        preOrderDFS(current.right, list);

    }

    private void inOrderDFS(TreeNode<E> current, SimpleList<E> list)
    {

        if(current == null) return;
        preOrderDFS(current.left, list);
        list.add(current.value);
        preOrderDFS(current.right, list);
    }

    private void postOrderDFS(TreeNode<E> current, SimpleList<E> list)
    {

        if(current == null) return;
        preOrderDFS(current.left, list);
        preOrderDFS(current.right, list);
        list.add(current.value);
    }

    public SimpleList<E> levelOrder()
    {
        SimpleList<E> result = new SimpleLinkedList<E>();

        // Acá usen su Queue, NO Priority
        SimpleArrayQueue<TreeNode<E>> q = new SimpleArrayQueue<TreeNode<E>>();

        // EN una Queue normal, solo va root (no 0)
        q.enqueue(root);

        while(!q.isEmpty())
        {
            TreeNode<E> node = q.dequeue();
            result.add(node.value);
            if(node.left != null) q.enqueue(node.left);
            if(node.right != null) q.enqueue(node.right);
        }
        return result;
    }
}
