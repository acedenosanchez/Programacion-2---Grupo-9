package treeModule;

public class AVL<E extends Comparable<E>> extends BST<E> {

    @Override
    public void insert(E value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser null");
        }
        root = insertRecursive(root, value);
    }

    @Override
    protected TreeNode<E> insertRecursive(TreeNode<E> current, E value) {
        if (current == null) {
            size++;
            return new TreeNode<E>(value);
        }

        int comparison = value.compareTo(current.value);

        if (comparison < 0) {
            current.left = insertRecursive(current.left, value);
        } else if (comparison > 0) {
            current.right = insertRecursive(current.right, value);
        } else {
            return current;
        }

        updateHeight(current);
        return rebalance(current);
    }

    @Override
    public void remove(E value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser null");
        }
        root = removeRecursive(root, value);
    }

    @Override
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

            if (current.left == null) {
                size--;
                return current.right;
            }

            if (current.right == null) {
                size--;
                return current.left;
            }

            TreeNode<E> successor = getSmallestChild(current.right);
            current.value = successor.value;
            current.right = removeRecursive(current.right, successor.value);
        }

        updateHeight(current);
        return rebalance(current);
    }

    private TreeNode<E> rebalance(TreeNode<E> current) {
        int balance = getBalance(current);

        if (balance > 1) {
            if (getBalance(current.left) < 0) {
                current.left = rotateLeft(current.left);
            }
            return rotateRight(current);
        }

        if (balance < -1) {
            if (getBalance(current.right) > 0) {
                current.right = rotateRight(current.right);
            }
            return rotateLeft(current);
        }

        return current;
    }

    private TreeNode<E> rotateRight(TreeNode<E> unbalancedNode) {
        TreeNode<E> newRoot = unbalancedNode.left;
        TreeNode<E> movedSubTree = newRoot.right;

        newRoot.right = unbalancedNode;
        unbalancedNode.left = movedSubTree;

        updateHeight(unbalancedNode);
        updateHeight(newRoot);

        return newRoot;
    }

    private TreeNode<E> rotateLeft(TreeNode<E> unbalancedNode) {
        TreeNode<E> newRoot = unbalancedNode.right;
        TreeNode<E> movedSubTree = newRoot.left;

        newRoot.left = unbalancedNode;
        unbalancedNode.right = movedSubTree;

        updateHeight(unbalancedNode);
        updateHeight(newRoot);

        return newRoot;
    }

    private void updateHeight(TreeNode<E> node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private int getHeight(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    private int getBalance(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }

    public int height() {
        return getHeight(root);
    }

    public boolean isBalanced() {
        return isBalancedRecursive(root);
    }

    private boolean isBalancedRecursive(TreeNode<E> current) {
        if (current == null) {
            return true;
        }

        int balance = getBalance(current);

        return balance >= -1 && balance <= 1
                && isBalancedRecursive(current.left)
                && isBalancedRecursive(current.right);
    }
}
