package com.github.andrz25.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.LinkedList;


class MyDoublyLinkedListTest {

    MyDoublyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyDoublyLinkedList<>();
    }

    @Test
    void addFirst() {
        list.addFirst(100);
        assertEquals(100, list.getFirst());
        assertEquals(100, list.getLast());
        assertEquals(1, list.size());

        list.addFirst(1);
        assertEquals(1, list.getFirst());
        assertEquals(100, list.getLast());
        assertEquals(2, list.size());
    }

    @Test
    void addLast() {
        list.addLast(100);
        assertEquals(100, list.getFirst());
        assertEquals(100, list.getLast());
        assertEquals(1, list.size());

        list.addLast(1);
        assertEquals(100, list.getFirst());
        assertEquals(1, list.getLast());
        assertEquals(2, list.size());
    }

    @Test
    void insertAt() {
        list.addLast(5);
        list.addLast(4);
        list.insertAt(1, 3);
        assertEquals(3, list.getAt(1));
        assertEquals(3, list.size());

        list.insertAt(0, 10);
        assertEquals(10, list.getFirst());
        assertEquals(4, list.size());

        list.insertAt(4, 9);
        assertEquals(9, list.getLast());
        assertEquals(5, list.size());
    }

    @Test
    void removeFirst() {
        assertNull(list.removeFirst());

        list.addFirst(5);
        assertEquals(5, list.removeFirst());
        assertTrue(list.isEmpty());

        list.addLast(4);
        list.addLast(3);
        assertEquals(4, list.removeFirst());
        assertEquals(3, list.getFirst());
    }

    @Test
    void removeLast() {
        assertNull(list.removeLast());

        list.addFirst(5);
        assertEquals(5, list.removeLast());
        assertTrue(list.isEmpty());

        list.addLast(4);
        list.addLast(3);
        assertEquals(3, list.removeLast());
        assertEquals(4, list.getLast());
    }

    @Test
    void removeAt() {
        list.addLast(5);
        list.addLast(4);
        list.addLast(3);

        assertEquals(4, list.removeAt(1));
        assertEquals(2, list.size());

        assertEquals(5, list.removeAt(0));
        assertEquals(1, list.size());

        assertEquals(3, list.removeAt(0));
        assertTrue(list.isEmpty());
    }

    @Test
    void getFirst() {
        assertNull(list.getFirst());
        list.addLast(5);
        assertEquals(5, list.getFirst());
    }

    @Test
    void getLast() {
        assertNull(list.getLast());
        list.addLast(5);
        assertEquals(5, list.getLast());
    }

    @Test
    void getAt() {
        list.addLast(5);
        list.addLast(4);
        list.addLast(3);

        assertEquals(5, list.getAt(0));
        assertEquals(4, list.getAt(1));
        assertEquals(3, list.getAt(2));

        assertNull(list.getAt(3));
    }

    @Test
    void size() {
        assertEquals(0, list.size());
    }

    @Test
    void sizeNonEmpty() {
        list.addFirst(5);
        list.addLast(4);
        list.addFirst(3);
        assertEquals(3, list.size());

        list.removeFirst();
        assertEquals(2, list.size());
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty(), "Needs to be empty list at start");
    }

    @Test
    void isEmptyNonEmpty() {
        list.addFirst(5);
        assertFalse(list.isEmpty(), "List should not be empty");

        list.removeFirst();
        assertTrue(list.isEmpty(), "List should be empty");
    }

    @Test
    void clear() {
        list.addFirst(5);
        list.addLast(4);
        list.clear();
        assertTrue(list.isEmpty());
        assertNull(list.getFirst());
        assertNull(list.getLast());
    }

    @Test
    void contains() {
        assertFalse(list.contains(5));

        list.addLast(5);
        list.addLast(4);
        assertTrue(list.contains(5));
        assertTrue(list.contains(4));
        assertFalse(list.contains(3));
    }

    @Test
    void indexOf() {
        list.addLast(5);
        list.addLast(4);
        list.addLast(5);

        assertEquals(0, list.indexOf(5));
        assertEquals(1, list.indexOf(4));
        assertEquals(-1, list.indexOf(6));
    }

    //Performance Tests
    private final MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

    private long usedMemory() {
        MemoryUsage heap = memoryBean.getHeapMemoryUsage();
        return heap.getUsed();
    }

    private void populate(MyDoublyLinkedList<Integer> list, int n){
        for(int i = 0; i < n; i++) list.addLast(i);
    }

    private void populate(LinkedList<Integer> list, int n){
        for(int i = 0; i < n; i++) list.addLast(i);
    }

    private final int[] sizes = {100, 1000, 10000};

    //Time Tests
    @Test
    void testAddLastTime(){
        System.out.println("\n--- Time: addLast ---\n");

        for(int n: sizes){
            MyDoublyLinkedList<Integer> myList = new MyDoublyLinkedList<>();
            LinkedList<Integer> javaList = new LinkedList<>();

            long startMy = System.nanoTime();
            populate(myList, n);
            long endMy = System.nanoTime();

            long startJava = System.nanoTime();
            populate(javaList, n);
            long endJava = System.nanoTime();

            System.out.printf("n=%d%nMyList: %d μs, JavaList: %d μs%n", n, (endMy-startMy)/1000, (endJava - startJava)/1000);
        }
    }

    @Test
    void testgetAtTime(){
        System.out.println("\n--- Time: getAt() ---\n");

        for(int n: sizes){
            MyDoublyLinkedList<Integer> myList = new MyDoublyLinkedList<>();
            LinkedList<Integer> javaList = new LinkedList<>();

            populate(myList, n);
            populate(javaList, n);

            int index = n/2;

            long startMy = System.nanoTime();
            myList.getAt(index);
            long endMy = System.nanoTime();

            long startJava = System.nanoTime();
            javaList.get(index);
            long endJava = System.nanoTime();

            System.out.printf("n=%d%nMyList: %d ns, JavaList: %d ns%n", n, (endMy-startMy), (endJava - startJava));
        }
    }

    @Test
    void testRemoveAtTime(){
        System.out.println("\n--- Time: removeAt() ---\n");

        for(int n: sizes){
            MyDoublyLinkedList<Integer> myList = new MyDoublyLinkedList<>();
            LinkedList<Integer> javaList = new LinkedList<>();

            populate(myList, n);
            populate(javaList, n);

            int index = n/2;

            long startMy = System.nanoTime();
            myList.removeAt(index);
            long endMy = System.nanoTime();

            long startJava = System.nanoTime();
            javaList.remove(index);
            long endJava = System.nanoTime();

            System.out.printf("n=%d%nMyList: %d ns, JavaList: %d ns%n", n, (endMy-startMy), (endJava - startJava));
        }
    }

    //Memory Test
    @Test
    void testMemoryUsage(){
        System.out.println("\n--- Memory: myList ---\n");

        for(int n: sizes){
            MyDoublyLinkedList<Integer> doublyll = new MyDoublyLinkedList<>();
            long before = usedMemory();
            populate(doublyll, n);
            long after = usedMemory();

            System.out.printf("n=%d%nMemory Used: %.2f KB%n", n, (after - before)/1024.0);
        }
    }

    @Test
    void testMemoryComparison(){
        System.out.println("\n--- Memory Comparison ---\n");

        for(int n: sizes){
            MyDoublyLinkedList<Integer> myList = new MyDoublyLinkedList<>();
            long beforeMy = usedMemory();
            populate(myList, n);
            long afterMy = usedMemory();
            long myMem = afterMy - beforeMy;

            LinkedList<Integer> javaList = new LinkedList<>();
            long beforeJava = usedMemory();
            populate(javaList, n);
            long afterJava = usedMemory();
            long javaMem = afterJava - beforeJava;

            System.out.printf("n=%d%nMyList: %.2f KB, JavaList: %.2f KB%n", n, myMem/1024.0, javaMem/1024.0);
        }
    }
    
    //Scalability Test
    @Test
    void testScalability(){
        System.out.println("\n--- Scalability Test ---\n");

        for(int n: sizes){
            MyDoublyLinkedList<Integer> dll = new MyDoublyLinkedList<>();
            long insertStart = System.nanoTime();
            populate(dll, n);
            long insertEnd = System.nanoTime();

            long removeStart = System.nanoTime();
            while(!dll.isEmpty()) dll.removeLast();
            long removeEnd = System.nanoTime();

            System.out.printf("n=%d%nInsert: %.2f ms, Remove: %.2f ms%n", n, (insertEnd - insertStart)/1_000_000.0, (removeEnd - removeStart)/1_000_000.0);
        }
    }
}   