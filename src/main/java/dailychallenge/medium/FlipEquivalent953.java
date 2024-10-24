package dailychallenge.medium;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class FlipEquivalent953 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        System.out.println(flipEquiv(root1, root2));
    }

//    recursion; time: O(n), space: O(n)
    public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
//        both trees are empty
        if(root1 == null && root2 == null)
            return true;
//        one of the trees is empty
        if(root1 == null || root2 == null)
            return false;
//        values differ
        if(root1.val != root2.val)
            return false;
//        check if they are flip equivalent
        boolean noSwap = flipEquiv(root1.left, root2.left) &&
                flipEquiv(root1.right, root2.right);
        boolean swap = flipEquiv(root1.left, root2.right) &&
                flipEquiv(root1.right, root2.left);

        return noSwap || swap;
    }
}

/*
For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivalent or false otherwise.

Example 1:
Flipped Trees Diagram
Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
Output: true
Explanation: We flipped at nodes with values 1, 3, and 5.
Example 2:
Input: root1 = [], root2 = []
Output: true
Example 3:
Input: root1 = [], root2 = [1]
Output: false

Constraints:
The number of nodes in each tree is in the range [0, 100].
Each tree will have unique node values in the range [0, 99].
 */