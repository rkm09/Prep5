package dailychallenge.medium;

import common.Pair;
import common.TreeNode;

import java.util.*;

public class ReplaceValueInBT2641 {
    public static void main(String[] args) {
        TreeNode right2 = new TreeNode(7);
        TreeNode right1 = new TreeNode(10);
        TreeNode left1 = new TreeNode(1);
        TreeNode right = new TreeNode(9, null, right2);
        TreeNode left = new TreeNode(4, left1, right1);
        TreeNode root = new TreeNode(5, left, right);
        System.out.println(replaceValueInTree(root).val);
    }

//    bfs; two pass; time: O(n), space: O(n)
    public static TreeNode replaceValueInTree(TreeNode root) {
        if(root == null) return root;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        List<Integer> levelSums = new ArrayList<>();
//        first bfs: calculate level sums
        while(!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            for(int i = 0 ; i < size ; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;
                if(node.left != null)
                    queue.offer(node.left);
                if(node.right != null)
                    queue.offer(node.right);
            }
            levelSums.add(levelSum);
        }
//        second bfs: update each node's value to the sum of its cousins
        queue.offer(root);
        int levelIndex = 1;
        root.val = 0; // root has no cousins
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0 ; i < size ; i++) {
                TreeNode node = queue.poll();
                int siblingSum = (node.left != null ? node.left.val : 0) + (node.right != null ?
                        node.right.val : 0);
                if(node.left != null) {
                    node.left.val = levelSums.get(levelIndex) - siblingSum;
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    node.right.val = levelSums.get(levelIndex) - siblingSum;
                    queue.offer(node.right);
                }
            }
            levelIndex++;
        }

        return root;
    }
}

/*
Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.
Two nodes of a binary tree are cousins if they have the same depth with different parents.
Return the root of the modified tree.
Note that the depth of a node is the number of edges in the path from the root node to it.
Example 1:
Input: root = [5,4,9,1,10,null,7]
Output: [0,0,0,7,7,null,11]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 5 does not have any cousins so its sum is 0.
- Node with value 4 does not have any cousins so its sum is 0.
- Node with value 9 does not have any cousins so its sum is 0.
- Node with value 1 has a cousin with value 7 so its sum is 7.
- Node with value 10 has a cousin with value 7 so its sum is 7.
- Node with value 7 has cousins with values 1 and 10 so its sum is 11.
Example 2:
Input: root = [3,1,2]
Output: [0,0,0]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 3 does not have any cousins so its sum is 0.
- Node with value 1 does not have any cousins so its sum is 0.
- Node with value 2 does not have any cousins so its sum is 0.

Constraints:
The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 104
 */