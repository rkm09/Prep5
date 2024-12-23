package dailychallenge.medium;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ReverseOddLevels2415 {
    public static void main(String[] args) {

    }

//    depth first search; time: O(n), space: O(logn)
    public TreeNode reverseOddLevels(TreeNode root) {
        traverseDfs(root.left, root.right, 0);
        return root;
    }

    private void traverseDfs(TreeNode leftChild, TreeNode rightChild, int level) {
        if(leftChild == null || rightChild == null) return;
//        if level is even, swap left and right child
        if(level % 2 == 0) {
            int tempValue = leftChild.val;
            leftChild.val = rightChild.val;
            rightChild.val = tempValue;
        }
        traverseDfs(leftChild.left, rightChild.right, level + 1);
        traverseDfs(leftChild.right, rightChild.left, level + 1);
    }

//    bfs; time: O(n), space: O(n)
    public TreeNode reverseOddLevels1(TreeNode root) {
        if(root == null) return null; // return null if empty
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);  // start BFS with root node
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> currentLevelNodes = new ArrayList<>();
//            process all the nodes of the current level
            for(int i = 0 ; i < size ; i++) {
                TreeNode node = queue.poll();
                currentLevelNodes.add(node);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
//            reverse the nodes if the current level is odd
            if(level % 2 == 1) {
                int left = 0, right = currentLevelNodes.size() - 1;
                while(left < right) {
                    int temp = currentLevelNodes.get(left).val;
                    currentLevelNodes.get(left).val = currentLevelNodes.get(right).val;
                    currentLevelNodes.get(right).val = temp;
                    left++;
                    right--;
                }
            }
            level++;
        }
        return root;
    }
}

/*
Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.
For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
Return the root of the reversed tree.
A binary tree
 is perfect if all parent nodes have two children and all leaves are on the same level.
The level of a node is the number of edges along the path between it and the root node.

Example 1:
Input: root = [2,3,5,8,13,21,34]
Output: [2,5,3,8,13,21,34]
Explanation:
The tree has only one odd level.
The nodes at level 1 are 3, 5 respectively, which are reversed and become 5, 3.
Example 2:
Input: root = [7,13,11]
Output: [7,11,13]
Explanation:
The nodes at level 1 are 13, 11, which are reversed and become 11, 13.
Example 3:
Input: root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
Output: [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
Explanation:
The odd levels have non-zero values.
The nodes at level 1 were 1, 2, and are 2, 1 after the reversal.
The nodes at level 3 were 1, 1, 1, 1, 2, 2, 2, 2, and are 2, 2, 2, 2, 1, 1, 1, 1 after the reversal.

Constraints:
The number of nodes in the tree is in the range [1, 214].
0 <= Node.val <= 105
root is a perfect binary tree.
 */

/*
DFS:
As we traverse the tree recursively, we process the left and right children of the current root. For nodes at even levels, we swap the values at their left and right child nodes to reverse the arrangement of nodes below their level, while leaving the children of odd levels unchanged.
Let's discuss the implementation of the recursive function traverseDFS(node, leftChild, rightChild, int level):
Base case: If leftChild or rightChild is null, then we can stop the recursive traversal for further child nodes.
Even level: If the current level is even, swap the values rooted at leftChild and rightChild.
Perfect binary tree: Since the binary tree is perfect, it is symmetrical in nature. Therefore, to reverse the levels, we would want to swap the left value of the left child with the right value of the right child, and the right value of the left child with the left value of the right child.
Let n be the number of nodes in the given tree.
Time complexity: O(n)
In the worst case, the algorithm visits each node exactly once, resulting in a time complexity of O(n). The swapping at each recursive step takes constant time. Therefore, the overall time complexity is O(n).
Space complexity: O(logn))
The space complexity is determined by the recursion depth of the DFS. Since we are given a perfect binary tree, the height of the tree is bounded by logn. Therefore, the space complexity is given by O(logn)).
 */