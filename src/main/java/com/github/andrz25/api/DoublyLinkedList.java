package com.github.andrz25.api;

/**
 * Author: Jayna Ong and Nina Zhang Date: November 29, 2025 Version: 1.0 A generic Doubly Linked
 * List interface for Lab 5. Students must implement all methods.
 */
public interface DoublyLinkedList<T> {
    // Basic operations

    /**
     * Inserts a node at the head
     *
     * @param data the data contained inside the node
     */
    void addFirst(T data);

    /**
     * Inserts a node at the tail
     *
     * @param data the data contained inside the node
     */
    void addLast(T data);

    /**
     * Inserts a node at a given index
     *
     * @param index the index that will obtain a new node
     * @param data the data contained inside the node
     */
    void insertAt(int index, T data);

    // Deletion

    /**
     * Removes the head node
     *
     * @return the removed head node
     */
    T removeFirst();

    /**
     * Removes the tail node
     *
     * @return the removed tail node
     */
    T removeLast();

    /**
     * Removes a node at a given index
     *
     * @param index the index that a node will be deleted
     * @return the deleted node
     */
    T removeAt(int index);

    // Access

    /**
     * Gets head element
     *
     * @return the head element
     */
    T getFirst();

    /**
     * Gets tail element
     *
     * @return the tail element
     */
    T getLast();

    /**
     * Gets element at given index
     *
     * @param index the index that a node will be retrieved
     * @return the node that was retrieved
     */
    T getAt(int index);

    // Utility

    /**
     * Finds number of elements in doubly linked list
     *
     * @return number of elements in doubly linked list
     */
    int size();

    /**
     * Checks if doubly linked list is empty
     *
     * @return true if doubly linked list is empty, false if not
     */
    boolean isEmpty();

    /** Removes all elements in doubly linked list */
    void clear();

    // Search

    /**
     * Checks if a node exists in doubly linked list
     *
     * @param data the data contained inside the node
     * @return true if node is in doubly linked list, false if not
     */
    boolean contains(T data);

    /**
     * Gets first index of given element
     *
     * @param data the data contained inside the node
     * @return the index of the element searched, if not found, -1 is returned
     */
    int indexOf(T data);
}
