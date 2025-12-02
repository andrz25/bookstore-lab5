package com.github.andrz25.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class MyBinarySearchTreePerformanceTest {

    private MyBinarySearchTree<Integer> bst;
    private Random random;

    @BeforeEach
    void setUp() {
        bst = new MyBinarySearchTree<>();
        random = new Random();
    }

    /**
     * Benchmarks insertion of random integers.
     * Expected: Fast, logarithmic growth O(log n).
     */
    @Test
    void benchmarkRandomInsertion() {
        int[] dataSizes = {1000, 10000, 100000, 500000};

        System.out.println("--- Random Insertion Benchmark ---");
        System.out.printf("%-15s %-15s %-15s%n", "N Elements", "Time (ms)", "Avg Time/Op (ns)");

        for (int n : dataSizes) {
            bst = new MyBinarySearchTree<>(); // Reset tree
            long startTime = System.nanoTime();

            for (int i = 0; i < n; i++) {
                bst.insert(random.nextInt());
            }

            long endTime = System.nanoTime();
            long durationMs = (endTime - startTime) / 1_000_000;
            long avgNs = (endTime - startTime) / n;

            System.out.printf("%-15d %-15d %-15d%n", n, durationMs, avgNs);
        }
    }

    /**
     * Benchmarks searching for elements that exist in the tree.
     * We populate the tree first, then search for every element we added.
     */
    @Test
    void benchmarkSearch() {
        int n = 100000;
        List<Integer> data = new ArrayList<>();

        // Prepare data
        for (int i = 0; i < n; i++) {
            int val = random.nextInt();
            data.add(val);
            bst.insert(val);
        }

        System.out.println("\n--- Search Benchmark (N=" + n + ") ---");

        long startTime = System.nanoTime();

        // Search for all inserted elements
        for (Integer val : data) {
            bst.contains(val);
        }

        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;
        long avgNs = (endTime - startTime) / n;

        System.out.printf("Total Time: %d ms%n", durationMs);
        System.out.printf("Average Search Time: %d ns%n", avgNs);
    }

    /**
     * WARN: This test demonstrates the worst-case scenario for a basic BST.
     * Inserting sorted data creates a skewed tree (essentially a linked list).
     * This may cause StackOverflowError on large datasets due to recursion depth.
     */
    @Test
    void benchmarkSortedInsertion_WorstCase() {
        // We use smaller sizes here because O(n^2) is very slow
        int[] dataSizes = {1000, 5000, 10000};

        System.out.println("\n--- Sorted Insertion (Worst Case) Benchmark ---");
        System.out.printf("%-15s %-15s%n", "N Elements", "Time (ms)");

        for (int n : dataSizes) {
            bst = new MyBinarySearchTree<>();

            long startTime = System.nanoTime();

            // Insert sorted data 0, 1, 2, ...
            for (int i = 0; i < n; i++) {
                bst.insert(i);
            }

            long endTime = System.nanoTime();
            long durationMs = (endTime - startTime) / 1_000_000;

            System.out.printf("%-15d %-15d%n", n, durationMs);
        }
    }
}
