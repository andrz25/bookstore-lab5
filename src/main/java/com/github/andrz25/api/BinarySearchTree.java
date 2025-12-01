package com.github.andrz25.api;

public interface BinarySearchTree<T extends Comparable<T>> {
    // Core Operations
    void insert(T data);

    boolean contains(T data);

    void delete(T data);

    // Traversals
    void inOrderTraversal();

    void preOrderTraversal();

    void postOrderTraversal();

    void levelOrderTraversal();

    // Properties
    int height();

    int size();

    boolean isEmpty();
}
