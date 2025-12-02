package com.github.andrz25.model;

import com.github.andrz25.api.BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * An implementation of a Binary Search Tree (BST).
 * * <h2>Design Choices & Trade-offs</h2>
 * <ul>
 * <li>
 * <b>Iterative Core Operations:</b> The <code>insert</code>, <code>delete</code>, and <code>contains</code>
 * methods are implemented iteratively. This prevents {@code StackOverflowError} when building
 * deep trees, which is a common failure point in recursive BST implementations.
 * </li>
 * <li>
 * <b>Recursive Traversals:</b> Traversals and height calculations use recursion for code clarity.
 * <i>Note:</i> On highly skewed trees (depth > 10,000), these methods may throw a StackOverflowError.
 * </li>
 * <li>
 * <b>Unbalanced Structure:</b> This implementation does not self-balance. While this keeps the code
 * simple and memory-efficient (no need to store color or height in nodes), it means
 * worst-case performance is O(n) for sorted input.
 * </li>
 * </ul>
 *
 * @param <T> the type of elements maintained by this tree, must extend Comparable
 */
class TreeNode<T> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

/**
 * An implementation of a Binary Search Tree (BST).
 *
 * <h2>Complexity Summary</h2>
 * <table border="1">
 * <tr>
 * <th>Operation</th>
 * <th>Average Case (Balanced)</th>
 * <th>Worst Case (Skewed)</th>
 * </tr>
 * <tr>
 * <td>Search/Insert/Delete</td>
 * <td>O(log n)</td>
 * <td>O(n)</td>
 * </tr>
 * <tr>
 * <td>Traversals</td>
 * <td>O(n)</td>
 * <td>O(n)</td>
 * </tr>
 * </table>
 * <br>
 * <b>Note:</b> Because this is not a self-balancing tree (like AVL or Red-Black trees),
 * the worst-case scenario occurs when data is inserted in sorted order, creating a "linked list" structure.
 *
 * @param <T> the type of elements maintained by this tree, must extend Comparable
 */
public class MyBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private TreeNode<T> root;
    private int size;

    public MyBinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * Inserts a new element into the binary search tree.
     *
     * <h3>Complexity Analysis</h3>
     * <ul>
     * <li><b>Time:</b> O(h) where h is the height of the tree.
     * Average: O(log n), Worst: O(n).</li>
     * <li><b>Space:</b> O(1) (Iterative implementation).</li>
     * </ul>
     *
     * @param data the element to insert
     */
    @Override
    public void insert(T data) {
        if (root == null) {
            root = new TreeNode<T>(data);
            size++;
            return;
        }

        TreeNode<T> current = root;
        TreeNode<T> parent = null;

        while (current != null) {
            parent = current;

            int comparison = data.compareTo(current.data);

            if (comparison < 0) {
                current = current.left;
            } else if (comparison > 0) {
                current = current.right;
            } else {
                return;
            }
        }

        TreeNode<T> newNode = new TreeNode<T>(data);

        if (data.compareTo(parent.data) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        size++;
    }

    /**
     * Checks if the tree contains the specified element.
     *
     * <h3>Complexity Analysis</h3>
     * <ul>
     * <li><b>Time:</b> O(h). Average: O(log n), Worst: O(n).</li>
     * <li><b>Space:</b> O(1) (Iterative implementation).</li>
     * </ul>
     *
     * @param data the element to search for
     * @return true if found, false otherwise
     */
    @Override
    public boolean contains(T data) {
        TreeNode<T> current = root;

        while (current != null) {
            int comparison = data.compareTo(current.data);

            if (comparison == 0) {
                return true;
            } else if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    /**
     * Removes the specified element from the tree.
     *
     * <h3>Complexity Analysis</h3>
     * <ul>
     * <li><b>Time:</b> O(h) to find the node and potentially the successor.
     * Average: O(log n), Worst: O(n).</li>
     * <li><b>Space:</b> O(1) (Iterative implementation).</li>
     * </ul>
     *
     * @param data the element to delete
     */
    @Override
    public void delete(T data) {
        TreeNode<T> parent = null;
        TreeNode<T> current = root;

        while (current != null) {
            int comparison = data.compareTo(current.data);

            if (comparison < 0) {
                parent = current;
                current = current.left;
            } else if (comparison > 0) {
                parent = current;
                current = current.right;
            } else {
                size--;

                // Case 1: Two Children
                if (current.left != null && current.right != null) {
                    TreeNode<T> successorParent = current;
                    TreeNode<T> successor = current.right;

                    while (successor.left != null) {
                        successorParent = successor;
                        successor = successor.left;
                    }

                    current.data = successor.data;

                    if (successorParent == current) {
                        successorParent.right = successor.right;
                    } else {
                        successorParent.left = successor.right;
                    }

                    return;
                }

                TreeNode<T> replacement = (current.left != null) ? current.left : current.right;

                if (parent == null) {
                    root = replacement;
                } else if (parent.left == current) {
                    parent.left = replacement;
                } else {
                    parent.right = replacement;
                }

                return;
            }
        }
    }

    /**
     * Performs an in-order traversal (Left, Root, Right).
     *
     * <h3>Complexity Analysis</h3>
     * <ul>
     * <li><b>Time:</b> O(n) (Visits every node).</li>
     * <li><b>Space:</b> O(h) for recursion stack. Worst case O(n).</li>
     * </ul>
     */
    @Override
    public void inOrderTraversal() {
        recursiveInOrderTraversal(root);
    }

    private void recursiveInOrderTraversal(TreeNode<T> node) {
        if (node != null) {
            recursiveInOrderTraversal(node.left);
            System.out.print(node.data + " ");
            recursiveInOrderTraversal(node.right);
        }
    }

    /**
     * Performs a pre-order traversal (Root, Left, Right).
     *
     * <h3>Complexity Analysis</h3>
     * <ul>
     * <li><b>Time:</b> O(n) (Visits every node).</li>
     * <li><b>Space:</b> O(h) for recursion stack. Worst case O(n).</li>
     * </ul>
     */
    @Override
    public void preOrderTraversal() {
        recursivePreOrderTraversal(root);
    }

    private void recursivePreOrderTraversal(TreeNode<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            recursivePreOrderTraversal(node.left);
            recursivePreOrderTraversal(node.right);
        }
    }

    /**
     * Performs a post-order traversal (Left, Right, Root).
     *
     * <h3>Complexity Analysis</h3>
     * <ul>
     * <li><b>Time:</b> O(n) (Visits every node).</li>
     * <li><b>Space:</b> O(h) for recursion stack. Worst case O(n).</li>
     * </ul>
     */
    @Override
    public void postOrderTraversal() {
        recursivePostOrderTraversal(root);
    }

    private void recursivePostOrderTraversal(TreeNode<T> node) {
        if (node != null) {
            recursivePostOrderTraversal(node.left);
            recursivePostOrderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }

    /**
     * Performs a level-order traversal (Breadth-First Search).
     *
     * <h3>Complexity Analysis</h3>
     * <ul>
     * <li><b>Time:</b> O(n) (Visits every node).</li>
     * <li><b>Space:</b> O(w) where w is the maximum width of the tree.
     * In a perfect binary tree, w = n/2, so O(n).</li>
     * </ul>
     */
    @Override
    public void levelOrderTraversal() {
        recursiveLevelOrderTraversal(root);
    }

    private void recursiveLevelOrderTraversal(TreeNode<T> root) {
        if (root == null) return;

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();
            System.out.print(current.data + " ");

            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
    }

    /**
     * Calculates the height of the tree.
     *
     * <h3>Complexity Analysis</h3>
     * <ul>
     * <li><b>Time:</b> O(n) (Must visit every node to find max depth).</li>
     * <li><b>Space:</b> O(h) for recursion stack. Worst case O(n).</li>
     * </ul>
     *
     * @return the height of the tree
     */
    @Override
    public int height() {
        return recursiveHeight(root);
    }

    private int recursiveHeight(TreeNode<T> node) {
        if (node == null) return -1;

        int leftHeight = recursiveHeight(node.left);
        int rightHeight = recursiveHeight(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Returns the number of elements in the tree.
     *
     * <h3>Complexity Analysis</h3>
     * <ul>
     * <li><b>Time:</b> O(1) (Returns cached variable).</li>
     * <li><b>Space:</b> O(1).</li>
     * </ul>
     *
     * @return the size of the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the tree is empty.
     *
     * <h3>Complexity Analysis</h3>
     * <ul>
     * <li><b>Time:</b> O(1).</li>
     * <li><b>Space:</b> O(1).</li>
     * </ul>
     *
     * @return true if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
