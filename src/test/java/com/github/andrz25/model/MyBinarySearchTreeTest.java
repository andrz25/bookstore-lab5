package com.github.andrz25.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MyBinarySearchTreeTest {

    private MyBinarySearchTree<Integer> bst;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        bst = new MyBinarySearchTree<>();
        // Redirect System.out to capture traversal outputs
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // Restore standard System.out
        System.setOut(originalOut);
    }

    @Test
    void insert() {
        assertTrue(bst.isEmpty());

        bst.insert(50);
        assertFalse(bst.isEmpty());
        assertEquals(1, bst.size());

        bst.insert(30);
        bst.insert(70);
        assertEquals(3, bst.size());

        // Test duplicate insertion (should be ignored based on your implementation)
        bst.insert(50);
        assertEquals(3, bst.size(), "Size should not change when inserting duplicates");
    }

    @Test
    void contains() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);

        assertTrue(bst.contains(50));
        assertTrue(bst.contains(30));
        assertTrue(bst.contains(70));

        assertFalse(bst.contains(100));
        assertFalse(bst.contains(10));
    }

    @Test
    void delete() {
        // Construct tree:
        //       50
        //     /    \
        //   30      70
        //  /  \    /  \
        // 20  40  60  80
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        assertEquals(7, bst.size());

        // Case 1: Delete leaf node (20)
        bst.delete(20);
        assertFalse(bst.contains(20));
        assertEquals(6, bst.size());

        // Case 2: Delete node with one child (Assume we alter tree to make this case)
        // Let's remove 30. It has one child (40) now because 20 is gone.
        // Wait, 30 has 40 as right child.
        bst.delete(30);
        assertFalse(bst.contains(30));
        assertTrue(bst.contains(40)); // 40 should move up
        assertEquals(5, bst.size());

        // Case 3: Delete node with two children (50 - root)
        // Root 50 has children (modified left subtree) and 70.
        // Successor of 50 is 60 (left-most of right subtree).
        bst.delete(50);
        assertFalse(bst.contains(50));
        assertTrue(bst.contains(60)); // 60 should be the new root or present
        assertTrue(bst.contains(70));
        assertEquals(4, bst.size());

        // Delete non-existent
        bst.delete(999);
        assertEquals(4, bst.size());
    }

    @Test
    void inOrderTraversal() {
        // Expected: Left, Root, Right (Sorted)
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);

        bst.inOrderTraversal();

        // "30 50 70 "
        assertEquals("30 50 70 ", outContent.toString());
    }

    @Test
    void preOrderTraversal() {
        // Expected: Root, Left, Right
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);

        bst.preOrderTraversal();

        // "50 30 70 "
        assertEquals("50 30 70 ", outContent.toString());
    }

    @Test
    void postOrderTraversal() {
        // Expected: Left, Right, Root
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);

        bst.postOrderTraversal();

        // "30 70 50 "
        assertEquals("30 70 50 ", outContent.toString());
    }

    @Test
    void levelOrderTraversal() {
        // Expected: Level by level
        //      50
        //     /  \
        //   30    70
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);

        bst.levelOrderTraversal();

        // "50 30 70 "
        assertEquals("50 30 70 ", outContent.toString());
    }

    @Test
    void height() {
        // Empty tree height is -1
        assertEquals(-1, bst.height());

        // Root only height is 0
        bst.insert(50);
        assertEquals(0, bst.height());

        //      50
        //     /
        //   30
        bst.insert(30);
        assertEquals(1, bst.height());

        //      50
        //     /
        //   30
        //  /
        // 20
        bst.insert(20);
        assertEquals(2, bst.height());

        // Add right side to check max logic
        //      50
        //     /  \
        //   30    70
        //  /
        // 20
        bst.insert(70);
        assertEquals(2, bst.height());
    }

    @Test
    void size() {
        assertEquals(0, bst.size());

        bst.insert(10);
        assertEquals(1, bst.size());

        bst.insert(20);
        assertEquals(2, bst.size());

        bst.delete(10);
        assertEquals(1, bst.size());

        bst.delete(20);
        assertEquals(0, bst.size());
    }

    @Test
    void isEmpty() {
        assertTrue(bst.isEmpty());

        bst.insert(10);
        assertFalse(bst.isEmpty());

        bst.delete(10);
        assertTrue(bst.isEmpty());
    }
}
