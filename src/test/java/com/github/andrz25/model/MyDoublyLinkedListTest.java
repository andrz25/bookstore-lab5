package com.github.andrz25.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}