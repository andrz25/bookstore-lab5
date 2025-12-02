import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * A utility class providing various tree traversal algorithms for binary trees.
 * This class includes both recursive and iterative implementations of common
 * tree traversal methods, as well as both console output and list-returning
 * variants for practical use.
 * 
 * <p>The class supports four main traversal strategies:
 * <ul>
 *   <li>In-Order Traversal (Left-Root-Right)</li>
 *   <li>Pre-Order Traversal (Root-Left-Right)</li>
 *   <li>Post-Order Traversal (Left-Right-Root)</li>
 *   <li>Level-Order Traversal (Breadth-First)</li>
 * </ul>
 * 
 * <p><b>Time and Space Complexity:</b>
 * <table border="1">
 *   <tr><th>Traversal</th><th>Time Complexity</th><th>Space Complexity</th></tr>
 *   <tr><td>In-Order</td><td>O(n)</td><td>O(h)</td></tr>
 *   <tr><td>Pre-Order</td><td>O(n)</td><td>O(h)</td></tr>
 *   <tr><td>Post-Order</td><td>O(n)</td><td>O(h)</td></tr>
 *   <tr><td>Level-Order</td><td>O(n)</td><td>O(w)</td></tr>
 * </table>
 * Where n = number of nodes, h = tree height, w = maximum tree width.
 * 
 * @param <T> the type of data stored in the tree nodes
 */
public class TreeTraversal<T> {
    
    /**
     * Performs in-order traversal (Left-Root-Right) and prints nodes to console.
     * This method visits nodes in the order: left subtree, current node, right subtree.
     * For Binary Search Trees, this produces elements in sorted order.
     * 
     * <p><b>Characteristics:</b>
     * <ul>
     *   <li>Time Complexity: O(n)</li>
     *   <li>Space Complexity: O(h) where h is the height of the tree</li>
     *   <li>Use Case: Produces sorted output for BSTs</li>
     * </ul>
     * 
     * <p><b>Example:</b> For tree: 
     * <pre>
     *     8
     *    / \
     *   3   10
     *  / \    \
     * 1   6    14
     *    / \   /
     *   4   7 13
     * </pre>
     * Output: 1 3 4 6 7 8 13 14
     * 
     * @param node the root node of the tree/subtree to traverse
     * @throws NullPointerException if the node is null (handled internally)
     */
    public void inOrderTraversal(TreeNode<T> node) {
        if (node != null) {
            inOrderTraversal(node.left);    // Visit left subtree
            System.out.print(node.data + " "); // Process root
            inOrderTraversal(node.right);   // Visit right subtree
        }
    }
    
    /**
     * Performs in-order traversal and returns the nodes as a List.
     * This is the practical variant that collects results instead of printing.
     * 
     * <p><b>Use Cases:</b>
     * <ul>
     *   <li>Getting sorted elements from a BST</li>
     *   <li>Processing elements in natural order</li>
     *   <li>Database query results in sorted order</li>
     * </ul>
     * 
     * @param node the root node of the tree/subtree to traverse
     * @return a List containing all nodes in in-order sequence
     */
    public List<T> inOrderTraversalToList(TreeNode<T> node) {
        List<T> result = new ArrayList<>();
        inOrderHelper(node, result);
        return result;
    }
    
    /**
     * Helper method for recursive in-order traversal that populates the result list.
     * 
     * @param node the current node being processed
     * @param result the list to store traversal results
     */
    private void inOrderHelper(TreeNode<T> node, List<T> result) {
        if (node != null) {
            inOrderHelper(node.left, result);
            result.add(node.data);
            inOrderHelper(node.right, result);
        }
    }
    
    /**
     * Performs pre-order traversal (Root-Left-Right) and prints nodes to console.
     * This method visits nodes in the order: current node, left subtree, right subtree.
     * 
     * <p><b>Characteristics:</b>
     * <ul>
     *   <li>Time Complexity: O(n)</li>
     *   <li>Space Complexity: O(h) where h is the height of the tree</li>
     *   <li>Use Case: Tree structure reconstruction, expression trees</li>
     * </ul>
     * 
     * <p><b>Example:</b> For tree:
     * <pre>
     *     8
     *    / \
     *   3   10
     *  / \    \
     * 1   6    14
     *    / \   /
     *   4   7 13
     * </pre>
     * Output: 8 3 1 6 4 7 10 14 13
     * 
     * <p><b>Advantage:</b> Root is processed first, useful for copying trees
     * 
     * @param node the root node of the tree/subtree to traverse
     */
    public void preOrderTraversal(TreeNode<T> node) {
        if (node != null) {
            System.out.print(node.data + " "); // Process root first
            preOrderTraversal(node.left);      // Visit left subtree
            preOrderTraversal(node.right);     // Visit right subtree
        }
    }
    
    /**
     * Performs pre-order traversal and returns the nodes as a List.
     * 
     * <p><b>Use Cases:</b>
     * <ul>
     *   <li>Tree copying and serialization</li>
     *   <li>Expression tree evaluation (prefix notation)</li>
     *   <li>File system directory listing</li>
     * </ul>
     * 
     * @param node the root node of the tree/subtree to traverse
     * @return a List containing all nodes in pre-order sequence
     */
    public List<T> preOrderTraversalToList(TreeNode<T> node) {
        List<T> result = new ArrayList<>();
        preOrderHelper(node, result);
        return result;
    }
    
    /**
     * Helper method for recursive pre-order traversal that populates the result list.
     * 
     * @param node the current node being processed
     * @param result the list to store traversal results
     */
    private void preOrderHelper(TreeNode<T> node, List<T> result) {
        if (node != null) {
            result.add(node.data);
            preOrderHelper(node.left, result);
            preOrderHelper(node.right, result);
        }
    }
    
    /**
     * Performs post-order traversal (Left-Right-Root) and prints nodes to console.
     * This method visits nodes in the order: left subtree, right subtree, current node.
     * 
     * <p><b>Characteristics:</b>
     * <ul>
     *   <li>Time Complexity: O(n)</li>
     *   <li>Space Complexity: O(h) where h is the height of the tree</li>
     *   <li>Use Case: Tree deletion, expression evaluation, directory size calculation</li>
     * </ul>
     * 
     * <p><b>Example:</b> For tree:
     * <pre>
     *     8
     *    / \
     *   3   10
     *  / \    \
     * 1   6    14
     *    / \   /
     *   4   7 13
     * </pre>
     * Output: 1 4 7 6 3 13 14 10 8
     * 
     * <p><b>Advantage:</b> Children are processed before parent
     * 
     * @param node the root node of the tree/subtree to traverse
     */
    public void postOrderTraversal(TreeNode<T> node) {
        if (node != null) {
            postOrderTraversal(node.left);     // Visit left subtree
            postOrderTraversal(node.right);    // Visit right subtree
            System.out.print(node.data + " "); // Process root last
        }
    }
    
    /**
     * Performs post-order traversal and returns the nodes as a List.
     * 
     * <p><b>Use Cases:</b>
     * <ul>
     *   <li>Tree deletion (children before parent)</li>
     *   <li>Expression tree evaluation (postfix notation)</li>
     *   <li>Directory size calculation</li>
     *   <li>Memory cleanup operations</li>
     * </ul>
     * 
     * @param node the root node of the tree/subtree to traverse
     * @return a List containing all nodes in post-order sequence
     */
    public List<T> postOrderTraversalToList(TreeNode<T> node) {
        List<T> result = new ArrayList<>();
        postOrderHelper(node, result);
        return result;
    }
    
    /**
     * Helper method for recursive post-order traversal that populates the result list.
     * 
     * @param node the current node being processed
     * @param result the list to store traversal results
     */
    private void postOrderHelper(TreeNode<T> node, List<T> result) {
        if (node != null) {
            postOrderHelper(node.left, result);
            postOrderHelper(node.right, result);
            result.add(node.data);
        }
    }
    
    /**
     * Performs breadth-first search (BFS) level-order traversal and prints nodes to console.
     * This method visits nodes level by level, from left to right at each level.
     * 
     * <p><b>Characteristics:</b>
     * <ul>
     *   <li>Time Complexity: O(n)</li>
     *   <li>Space Complexity: O(w) where w is the maximum width of the tree</li>
     *   <li>Use Case: Level-by-level processing, finding shortest path</li>
     * </ul>
     * 
     * <p><b>Example:</b> For tree:
     * <pre>
     *     8
     *    / \
     *   3   10
     *  / \    \
     * 1   6    14
     *    / \   /
     *   4   7 13
     * </pre>
     * Output: 8 3 10 1 6 14 4 7 13
     * 
     * <p><b>Advantage:</b> Processes nodes by depth levels
     * 
     * @param root the root node of the tree to traverse
     */
    public void levelOrderTraversal(TreeNode<T> root) {
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
    
    /**
     * Performs level-order traversal and returns the nodes as a List.
     * 
     * <p><b>Use Cases:</b>
     * <ul>
     *   <li>Finding shortest path in unweighted graphs</li>
     *   <li>Level-by-level tree printing</li>
     *   <li>Network broadcasting</li>
     *   <li>Game tree evaluation</li>
     * </ul>
     * 
     * @param root the root node of the tree to traverse
     * @return a List containing all nodes in level-order sequence
     */
    public List<T> levelOrderTraversalToList(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();
            result.add(current.data);
            
            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
        return result;
    }
    
    /**
     * Performs iterative in-order traversal using an explicit stack.
     * This method provides the same result as recursive in-order traversal
     * but avoids potential stack overflow for deep trees.
     * 
     * <p><b>Advantages over recursive approach:</b>
     * <ul>
     *   <li>No risk of stack overflow for deep trees</li>
     *   <li>Better control over memory usage</li>
     *   <li>Easier to debug and profile</li>
     * </ul>
     * 
     * <p><b>Disadvantages:</b>
     * <ul>
     *   <li>More complex implementation</li>
     *   <li>Requires explicit stack management</li>
     * </ul>
     * 
     * @param root the root node of the tree to traverse
     * @return a List containing all nodes in in-order sequence
     */
    public List<T> iterativeInOrder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        TreeNode<T> current = root;
        
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.data);
            current = current.right;
        }
        return result;
    }
    
    /**
     * Performs iterative pre-order traversal using an explicit stack.
     * This method provides the same result as recursive pre-order traversal
     * but uses iterative approach with better memory control.
     * 
     * <p><b>Algorithm:</b>
     * <ol>
     *   <li>Push root to stack</li>
     *   <li>While stack is not empty:
     *     <ol>
     *       <li>Pop node and process it</li>
     *       <li>Push right child (if exists)</li>
     *       <li>Push left child (if exists)</li>
     *     </ol>
     *   </li>x
     * </ol>
     * 
     * @param root the root node of the tree to traverse
     * @return a List containing all nodes in pre-order sequence
     */
    public List<T> iterativePreOrder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode<T> current = stack.pop();
            result.add(current.data);
            
            // Push right first so left is processed first (LIFO)
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
        return result;
    }
}