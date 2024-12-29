package dailychallenge.medium;

import common.TreeNode;


public class LowestCommonAncestorII1644 {
    boolean nodesFound = false;
    public static void main(String[] args) {

    }

//    dfs(2/3 conditions); time: O(n), space: O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = dfs(root,p,q);
        return nodesFound ? ans : null;
    }

    private TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null) return null;
        TreeNode left = dfs(node.left, p, q);
        TreeNode right = dfs(node.right, p, q);
        int conditions = 0;
        if(node == p || node == q) conditions++;
        if(left != null) conditions++;
        if(right != null) conditions++;
        if(conditions == 2) nodesFound = true;
        if((left != null && right != null) || node == p || node == q)
            return node;
        return left != null ? left : right;
    }

//    dfs; time: O(n), space: O(n)
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = lca(root, p, q);
//        if lca is p, verify q is in its subtree
        if(lca == p)
            return dfs(lca, q) ? lca : null;
//        if lca is q, verify p is in its subtree
        if(lca == q)
            return dfs(lca, p) ? lca : null;
//        if neither is the ancestor return lca
        return lca;
    }

    private TreeNode lca(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null || node == p || node == q) return node;
        TreeNode left = lca(node.left, p, q);
        TreeNode right = lca(node.right, p, q);
        if(left != null && right != null) return node;
        return left != null ? left : right;
    }

    private boolean dfs(TreeNode node, TreeNode target) {
        if(node == null) return false;
        if(node == target) return true;
        return dfs(node.left, target) || dfs(node.right, target);
    }
}

/*
Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.
According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.
Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.
Example 3:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
Output: null
Explanation: Node 10 does not exist in the tree, so return null.

Constraints:
The number of nodes in the tree is in the range [1, 104].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
Follow up: Can you find the LCA traversing the tree, without checking nodes existence?
 */

/*
First let's see how can we confirm that p and q are present in the tree.
For any given node, if any two of the following three conditions hold true, we can say that p and q are both present in the tree.
The node itself is either p or q.
One of the nodes (p or q) is in the left subtree of node.
One of the nodes (p or q) is in the right subtree of node.
As per the constraints of the problem, all the nodes are unique. So there will not be multiple occurrences of p and q.
If two of these conditions are true, we can confidently say that both p and q are in the tree.
Once we confirm that both nodes are present, we set a flag nodesFound to true, indicating that the solution is valid. If neither p nor q is found in the tree, the result will be null.
*/