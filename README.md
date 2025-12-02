### Lab 5 Doubly Linked List and BST Implementation

### Time Complexity Doubly Linked List:
1. Insertion
- addFirst: O(1) since insertion at the head only requires us to change node.next to head, head.prev to node, and head to node. It does not need to go through the entirety of the doubly linked list.
- addLast: O(1) since insertion at the tail only requires us to change node.prev to tail, tail.next to node, and tail to node. It does not need to go through the entirety of the doubly linked list.
- insertAt: O(n) since it requires us to go through the doubly linked list depending on the index value given. Depending on the index value given, that is the amount of traversals we need to perform before inserting the given node.

2. Deletion
- removeFirst: O(1) since deletion happens at the head where the head node and head.next is  known, the only change needed to be made is head needs to be changed to head.next and the previous value of the current head needs to be changed to null. No traversal of the doubly linked list is needed therefore making it O(1) time complexity.
- removeLast: O(1) since deletion at the tail only requires us to change tail to node, and  tail.next to null. It does not need to go through the entirety of the doubly linked list.
- removeAt: O(n) since it requires us to go through the doubly linked list depending on the index value given. Depending on the index value given, that is the amount of traversals we need to perform before removing the node.
3. Search
- contains: O(n) since it requires us to go through the doubly linked list until we find the required node. Depending on how many elements there are through the doubly linked list, we need to search through each of them until the provided node is found.
- indexOf: O(n) since it requires us to go through the entirety of the doubly linked list until the data of the current node matches the data of the given node. In this case we would need to check each node in the doubly linked list until the data matches and in the worst case it would be O(n), the length of the entire linked list.

### Time Complexity Binary Search Tree:

1. insert, contains, delete
- O(log n) is average case: This is the case for when the BST is balanced, when the height of the tree is proportional to log n. This would effectively half the time needed to delete, insert, or search for a specific element  as it would keep traversing through only one side of the tree.
- O(n) is worst case: In the case where the BST is skewed not balanced, when it resembles a linked list,  the height of the tree would be about the same as n the number  of elements. When this occurs, if you insert or delete an element or search for an element in the tree it might require a traversal through all nodes in the worst case resulting in linear time complexity.


### Space Complexity Analysis
1. Doubly Linked List: 
- O(n) for n elements
- For a doubly linked list, our growth is considered to be linear since the amount of memory we use depends on the number of nodes we have in the doubly linked list. Although nodes contain 3 different fields (prev + next pointers, and data), the big O notation simply ignores it since we only focus on what causes our use of memory to increase.

2. Binary Search Tree: 
- O(n) for n nodes
- Similar to a doubly linked list, our growth is considered to be linear since the amount of memory we use depends on the number of nodes we have in the binary search tree. For the nodes, we contain pointers that refer to the left and right child node, alongside the data itself. As we are working with big O notation, we ignore those fields and focus on what causes our use of memory to increase.

3. Traversal Algorithms: 
- O(h) for recursive, O(w) for BFS
- Recursive algorithms consume space on the call stack to keep track of nodes it must return to, which results in O(h) space, where h is the height of the tree. In a balanced tree, this height grows logarithmically, but in a skewed tree, it grows linearly, which means recursion can use anywhere from O(log n) to O(n) space
- For breadth-first search (BFS), the algorithm stores all nodes at the current level inside a queue, so its space complexity depends on the tree’s maximum width rather than its height. Most of the time, its worse case is O(w), where w is the width of the tree, such as a perfectly balanced tree, the widest level can hold nearly half of the nodes, or in extremely large trees, it can be as large as O(n) in the worst case or as low as O(1) in very narrow trees.


### When to Use Each Structure:
1. Use Linked List when:
- Frequent insertions/deletions at known positions, simple sequential access
- Inserting nodes at the tail and head would be simple, since it runs in O(1) time, while inserting at a given index position would result in an O(n) time. As long as we know where to insert the node, we can use a doubly linked list. If we need to access values in a linear order, we can use a linked list as well, since we just need to traverse through the linked list.
2. Use BST when:
- Frequent searching, need sorted data, range queries
- If we needed to do a lot of searching, a binary search tree would be a good choice in comparison to a linked list. The average search time for a binary search tree is O(log n), since we’re capable of cutting half the nodes through the way it’s being compared. All data stored in the binary search tree is in proper order, therefore it would make it easier to access sorted data. In addition, since data is properly sorted, it makes finding information within a given range much simpler by going through selected branches.
