package com.github.andrz25.model;

import com.github.andrz25.api.BinarySearchTree;

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

    public void MyBinarySearchTree() {
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
        return false;
    }

    @Override
    public void delete(T data) {

    }

    @Override
    public void inOrderTraversal() {

    }

    @Override
    public void preOrderTraversal() {

    }

    @Override
    public void postOrderTraversal() {

    }

    @Override
    public void levelOrderTraversal() {

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
