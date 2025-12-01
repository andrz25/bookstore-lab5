package com.github.andrz25.model;

import com.github.andrz25.api.BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

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

public class MyBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private TreeNode<T> root;
    private int size;

    public MyBinarySearchTree() {
        root = null;
        size = 0;
    }

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

    private TreeNode<T> getSuccessor(TreeNode<T> curr) {
        curr = curr.right;

        while (curr.left != null) {
            curr = curr.left;
        }

        return curr;
    }

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
                if (current.left != null && current.right != null) {
                    TreeNode<T> successor = getSuccessor(current);

                    T successorData = successor.data;

                    delete(successorData);

                    current.data = successorData;

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

    @Override
    public void inOrderTraversal() {
        recursiveInOrderTraversal(root);
    }

    private void recursiveInOrderTraversal(TreeNode<T> node) {
        if (node != null) {
            recursiveInOrderTraversal(node.left); // Visit left subtree
            System.out.print(node.data + " "); // Process root
            recursiveInOrderTraversal(node.right); // Visit right subtree
        }
    }

    @Override
    public void preOrderTraversal() {
        recursivePreOrderTraversal(root);
    }

    private void recursivePreOrderTraversal(TreeNode<T> node) {
        if (node != null) {
            System.out.print(node.data + " "); // Process root first
            recursivePreOrderTraversal(node.left); // Visit left subtree
            recursivePreOrderTraversal(node.right); // Visit right subtree
        }
    }

    @Override
    public void postOrderTraversal() {
        recursivePostOrderTraversal(root);
    }

    private void recursivePostOrderTraversal(TreeNode<T> node) {
        if (node != null) {
            recursivePostOrderTraversal(node.left); // Visit left subtree
            recursivePostOrderTraversal(node.right); // Visit right subtree
            System.out.print(node.data + " "); // Process root last
        }
    }

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

    @Override
    public int height() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
