/**
 * Author: Jayna Ong and Nina Zhang
 * Date: November 29, 2025
 * Version: 1.0
* A generic Doubly Linked List interface for Lab 5.
* Students must implement all methods.
*/
public interface DoublyLinkedList<T> {
    // Basic operations

    /**
     * Inserts a node at the head
     * @param data the data contained inside the node
     */
    void addFirst(T data);

    /**
     * Inserts a node at the tail
     * @param data the data contained inside the node
     */
    void addLast(T data);

    /**
     * Inserts a node at a given index
     * @param index the index that will obtain a new node
     * @param data the data contained inside the node
     */
    void insertAt(int index, T data);
    
    // Deletion

    /**
     * Removes the head node
     * @return the removed head node
     */
    T removeFirst(); 

    /**
     * Removes the tail node
     * @return the removed tail node
     */
    T removeLast();

    /**
     * Removes a node at a given index
     * @param index the index that a node will be deleted
     * @return the deleted node
     */
    T removeAt(int index);

    // Access

    /**
     * Gets head element
     * @return the head element
     */
    T getFirst();

    /**
     * Gets tail element
     * @return the tail element
     */
    T getLast();

    /**
     * Gets element at given index
     * @param index the index that a node will be retrieved
     * @return the node that was retrieved
     */
    T getAt(int index);
    
    // Utility

    /**
     * Finds number of elements in doubly linked list
     * @return number of elements in doubly linked list
     */
    int size();

    /**
     * Checks if doubly linked list is empty
     * @return true if doubly linked list is empty, false if not
     */
    boolean isEmpty();

    /**
     * Removes all elements in doubly linked list
     */
    void clear();

    // Search

    /**
     * Checks if a node exists in doubly linked list
     * @param data the data contained inside the node
     * @return true if node is in doubly linked list, false if not
     */
    boolean contains(T data);

    /**
     * Gets first index of given element
     * @param data the data contained inside the node
     * @return the index of the element searched, if not found, -1 is returned
     */
    int indexOf(T data);
}

/**
* Node class for Doubly Linked List.
*/
class Node<T> {
    T data;
    Node<T> prev;
    Node<T> next;

    Node (T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

//////
class MyDoublyLinkedList<T> implements DoublyLinkedList<T> {

    Node<T> head;
    Node<T> tail;

    public MyDoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }
    
    /////////////////Basic Operations/////////////////
    
    // Insert at the head
    @Override
    public void addFirst(T data) {
        Node<T> node = new Node<> (data);
        node.prev = null;

        if (head != null) {
            node.next = head;
            head.prev = node;
        }
        else {
            node.next = null;
            tail = node;
        }
        head = node;
    }
    
    // Insert at the tail
    @Override
    public void addLast(T data) {
        Node<T> node = new Node<> (data);
        node.next = null;

        if (tail != null) {
            node.prev = tail;
            tail.next = node;
        }
        else {
            node.prev = null;
            head = node;
        }
        tail = node;
    }
       
    // Insert at a given index
    @Override
    public void insertAt(int index, T data) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        if (index == size()) {
            addLast(data);
            return;
        }


        Node<T> node = new Node<> (data);
        Node<T> cur = head.next;
        Node<T> prev = head;

        for (int i = 1; i < size(); i++) {
            if (i == index) {
                node.prev = prev;
                node.next = cur;
                prev.next = node;
                cur.prev = node;
                break;
            }
            prev = cur;
            cur = cur.next;
        }
    }
    /////////////////Deletion/////////////////

    // Remove from head
    @Override
    public T removeFirst() {
        if (head == null) {
            return null;
        }

        Node<T> node = head.next;
        T data = head.data;
        head = node;

        if (head != null) {
            head.prev = null;
        }
        else {
            tail = null;
        }
        return data;
    }

    // Remove from tail
    @Override
    public T removeLast() {
        if (tail == null) {
            return null;
        }
        Node<T> node = tail.prev;

        T data = tail.data;
        tail = node;

        if (tail != null) {
            tail.next = null;
        }
        else {
            head = null;
        }
        return data;
    }

    // Remove from a given index
    @Override
    public T removeAt(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> cur = head;
        int i = 0;

        while (cur != null){
            if (i == index){
                Node<T> prev = cur.prev;
                Node<T> next = cur.next;

                if (prev != null) {
                    prev.next = next;
                }
                else {
                    head = next;
                }

                if (next != null) {
                    next.prev = prev;
                }
                else {
                    tail = prev;
                }

                return cur.data;
            }
            i++;
            cur = cur.next;
        }
        return null;
    }

    /////////////////Access/////////////////
    
    // Get head element
    @Override
    public T getFirst() {
        if (head == null || tail == null) {
            return null;
        }
        return head.data;
    }

    // Get tail element
    @Override
    public T getLast() {
        if (head == null || tail == null) {
            return null;
        }
        return tail.data;
    }

    // Get element at index
    @Override
    public T getAt(int index) {
        Node<T> cur = head;
        int i = 0;
        while(cur != null){
            if(i == index){
                return cur.data;
            }
            i++;
            cur = cur.next;
        }
        return null;
    }
    
    /////////////////Utility/////////////////

    // Number of elements
    @Override
    public int size() {
        Node cur = head;
        int count = 0;
        while (cur != null){
            count++;
            cur = cur.next;
        }
        return count;
    }

    // Check if empty
    @Override
    public boolean isEmpty() {
        if (head == null){
            return true;
        }
        return false;
    }

    // Remove all elements
    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    /////////////////Search/////////////////
    
    // Check existence
    @Override
    public boolean contains(T data) {
        Node<T> cur = head;
        while(cur != null){
            if(cur.data.equals(data)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // First index of element
    @Override
    public int indexOf(T data) {
        Node<T> cur = head;
        int index = 0;
        while(cur != null){
            if(cur.data.equals(data)){
                return index;
            }
            index++;
            cur = cur.next;
        }
        return -1;
    }
}
