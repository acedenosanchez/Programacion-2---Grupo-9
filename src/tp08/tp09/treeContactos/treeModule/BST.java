package tp08.tp09.treeContactos.treeModule;

import tp02.tp03.listModule.SimpleArrayList;
import tp02.tp03.listModule.SimpleLinkedList;
import tp02.tp03.listModule.SimpleList;
import tp04.queueModule.SimpleArrayQueue;

public class BST<E extends Comparable<E>> {
    protected TreeNode<E> root = null;
    protected int size;

    public void insert(E value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser null");
        }
        root = insertRecursive(root, value);
    }

    protected TreeNode<E> insertRecursive(TreeNode<E> current, E value) {
        if (current == null) {
            size++;
            return new TreeNode<E>(value);
        }
        // Devuelve -1 si es menor, 1 si es mayor en la comparación de nuevo valor con ultimo.
        int comparison = value.compareTo(current.value);

        if (comparison < 0) {
            current.left = insertRecursive(current.left, value);
        } else if (comparison > 0) {
            current.right = insertRecursive(current.right, value);
        }

        return current;
    }

    public void remove(E value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser null");
        }
        root = removeRecursive(root, value);
    }

    protected TreeNode<E> removeRecursive(TreeNode<E> current, E value) {
        if (current == null) {
            return null;
        }

        int comparison = value.compareTo(current.value);

        if (comparison < 0) {
            current.left = removeRecursive(current.left, value);
        } else if (comparison > 0) {
            current.right = removeRecursive(current.right, value);
        } else {
            if (current.left == null && current.right == null) {
                size--;
                return null;
            }

            if (current.right == null) {
                size--;
                return current.left;
            }

            if (current.left == null) {
                size--;
                return current.right;
            }

            TreeNode<E> successor = getSmallestChild(current.right);
            current.value = successor.value;
            current.right = removeRecursive(current.right, successor.value);
        }

        return current;
    }

    protected TreeNode<E> getSmallestChild(TreeNode<E> current) {
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean contains(E value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser null");
        }
        return containsRecursive(root, value);
    }

    protected boolean containsRecursive(TreeNode<E> current, E value) {
        if (current == null) {
            return false;
        }

        int comparison = value.compareTo(current.value);

        if (comparison == 0) {
            return true;
        }

        if (comparison < 0) {
            return containsRecursive(current.left, value);
        }

        return containsRecursive(current.right, value);
    }

    public E find(E value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser null");
        }
        return findRecursive(root, value);
    }

    protected E findRecursive(TreeNode<E> current, E value) {
        if (current == null) {
            return null;
        }

        int comparison = value.compareTo(current.value);

        if (comparison == 0) {
            return current.value;
        }

        if (comparison < 0) {
            return findRecursive(current.left, value);
        }

        return findRecursive(current.right, value);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public SimpleList<E> preOrder() {
        SimpleList<E> result = new SimpleArrayList<E>();
        preOrderDFS(root, result);
        return result;
    }

    public SimpleList<E> inOrder() {
        SimpleList<E> result = new SimpleArrayList<E>();
        inOrderDFS(root, result);
        return result;
    }

    public SimpleList<E> postOrder() {
        SimpleList<E> result = new SimpleArrayList<E>();
        postOrderDFS(root, result);
        return result;
    }

    protected void preOrderDFS(TreeNode<E> current, SimpleList<E> list) {
        if (current == null) {
            return;
        }

        list.add(current.value);
        preOrderDFS(current.left, list);
        preOrderDFS(current.right, list);
    }

    protected void inOrderDFS(TreeNode<E> current, SimpleList<E> list) {
        if (current == null) {
            return;
        }

        inOrderDFS(current.left, list);
        list.add(current.value);
        inOrderDFS(current.right, list);
    }

    protected void postOrderDFS(TreeNode<E> current, SimpleList<E> list) {
        if (current == null) {
            return;
        }

        postOrderDFS(current.left, list);
        postOrderDFS(current.right, list);
        list.add(current.value);
    }

    public SimpleList<E> levelOrder() {
        SimpleList<E> result = new SimpleLinkedList<E>();

        if (root == null) {
            return result;
        }

        SimpleArrayQueue<TreeNode<E>> queue = new SimpleArrayQueue<TreeNode<E>>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            TreeNode<E> node = queue.dequeue();
            result.add(node.value);

            if (node.left != null) {
                queue.enqueue(node.left);
            }

            if (node.right != null) {
                queue.enqueue(node.right);
            }
        }

        return result;
    }
}
