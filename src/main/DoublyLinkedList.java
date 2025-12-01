/**
* A generic Doubly Linked List interface for Lab 5.
* Students must implement all methods.
*/
public interface DoublyLinkedList<T> {
    // Basic operations
    void addFirst(T data); // Insert at the head
    void addLast(T data); // Insert at the tail
    void insertAt(int index, T data); // Insert at a given index
    
    // Deletion
    T removeFirst(); // Remove from head
    T removeLast(); // Remove from tail
    T removeAt(int index); // Remove from a given index

    // Access
    T getFirst(); // Get head element
    T getLast(); // Get tail element
    T getAt(int index); // Get element at index
    
    // Utility
    int size(); // Number of elements
    boolean isEmpty(); // Check if empty
    void clear(); // Remove all elements

    // Search
    boolean contains(T data); // Check existence
    int indexOf(T data); // First index of element
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
class DoublyLinkedLists<T> implements DoublyLinkedList<T> {

    Node<T> head;
    Node<T> tail;

    public DoublyLinkedLists<T>() {
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
        }
        tail = node;
    }
       
    // Insert at a given index
    @Override
    public void insertAt(int index, T data) {
        Node<T> node = new Node<> (data);
        Node<T> cur = head;
        Node<T> prev = head.prev;

        for (int i = 1; i < size(); i++) {
            if (i == index) {
                node.prev = prev;
                prev.next = node;
                cur.prev = node;
                node.next = cur;
            }
            prev = cur;
            cur = cur.next;
        }
    }
    /////////////////Deletion/////////////////

    // Remove from head
    @Override
    public T removeFirst() {
        Node<T> node = head.next;

        T data = head.data;
        head = node;
        head.prev = null;
        return data;
    }

    // Remove from tail
    @Override
    public T removeLast() {
        Node<T> node = tail.prev;

        T data = tail.data;
        tail = node;
        tail.next = null;
        return data;
    }

    // Remove from a given index
    @Override
    public T removeAt(int index) {
        Node<T> node = new
    }

    /////////////////Access/////////////////
    
    // Get head element
    @Override
    public T getFirst() {
        return head.data;
    }

    // Get tail element
    @Override
    public T getLast() {
        return tail.data;
    }

    // Get element at index
    @Override
    public T getAt(int index) {

    }
    
    /////////////////Utility/////////////////

    // Number of elements
    @Override
    public int size() {
        while (head)
    }

    // Check if empty
    @Override
    public boolean isEmpty() {
        return true;
    }

    // Remove all elements
    @Override
    public void clear() {
        
    }

    /////////////////Search/////////////////
    
    // Check existence
    @Override
    public boolean contains(T data) {
        return true;
    }

    // First index of element
    @Override
    public int indexOf(T data) {
        return 0;
    }
}
