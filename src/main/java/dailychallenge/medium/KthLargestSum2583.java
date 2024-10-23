package dailychallenge.medium;


import common.Pair;
import common.TreeNode;

import java.util.*;

public class KthLargestSum2583 {
    public static void main(String[] args) {
        TreeNode left11 = new TreeNode(3);
        TreeNode right11 = new TreeNode(4);
        TreeNode left = new TreeNode(2, left11, right11);
        TreeNode root = new TreeNode(1, left, null);
        System.out.println(kthLargestLevelSum(root, 1));
    }

//    Min heap + Queue; time: O(logn + logk), space: O(n)
//    The level order traversal requires O(N) time. We add to the heap approximately (logN) times,
//    with a maximum heap size of k, so building the heap takes O(logN⋅logk).
    public static long kthLargestLevelSum(TreeNode root, int k) {
//        min heap of size k, at the end top element is the kth largest
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
//            level order traversal
            int size = queue.size();
            long sum = 0;
            for(int i = 0 ; i < size ; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
//                add left child
                if(node.left != null)
                    queue.offer(node.left);
//                add right child
                if(node.right != null)
                    queue.offer(node.right);
            }
            pq.offer(sum);
            if(pq.size() > k) {
//                evict the top element
                pq.poll();
            }
        }
        if(k > pq.size())
            return -1;
        return pq.peek();
    }

//    Max heap + Queue; time: O(log^2n + klogn); space: O(n)
//    The level order traversal takes O(N) time. Since our heap has a maximum of O(logN) elements (equal to the tree’s height),
//    adding a sum to the heap takes O(logN) time, resulting in a total heap build time of O(log^2N).
//    Popping k−1 elements from the heap takes O(k⋅logN) time. Therefore, the overall time complexity is O(log^2N+k⋅logN).
    public static long kthLargestLevelSum1(TreeNode root, int k) {
//        max heap;
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
//            level order traversal
            int size = queue.size();
            long sum = 0;
            for(int i = 0 ; i < size ; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
//                add left child
                if(node.left != null)
                    queue.offer(node.left);
//                add right child
                if(node.right != null)
                    queue.offer(node.right);
            }
            pq.offer(sum);
        }
        if(k > pq.size())
            return -1;
        for(int i = 0 ; i < k - 1 ; i++)
            pq.remove();
        return pq.peek();
    }

//    def; time: O(nlogn), space: O(n) ; Queue + Map
    public static long kthLargestLevelSum2(TreeNode root, int k) {
        Deque<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(root, 1));
        Map<Integer, Long> levelSum = new HashMap<>();
        while(!queue.isEmpty()) {
//            keep track of levels using a pair, use a map to keep track of sum of each of the levels
            Pair<TreeNode, Integer> nodeInfo = queue.poll();
            TreeNode node = nodeInfo.getKey();
            int level = nodeInfo.getValue();
            levelSum.put(level, levelSum.getOrDefault(level, 0L) + node.val);
            if(node.left != null) {
                queue.offer(new Pair<>(node.left, level + 1));
            }
            if(node.right != null) {
                queue.offer(new Pair<>(node.right, level + 1));
            }
        }
        if(!levelSum.containsKey(k))
            return -1;
        List<Long> values = new ArrayList<>(levelSum.values());
        values.sort(Collections.reverseOrder());
        int pos = 1;
        for(Long val : values) {
            if(pos++ == k) {
                return val;
            }
        }
        return -1;
    }
}

/*
You are given the root of a binary tree and a positive integer k.
The level sum in the tree is the sum of the values of the nodes that are on the same level.
Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the tree, return -1.
Note that two nodes are on the same level if they have the same distance from the root.


Example 1:
Input: root = [5,8,9,2,1,3,7,4,6], k = 2
Output: 13
Explanation: The level sums are the following:
- Level 1: 5.
- Level 2: 8 + 9 = 17.
- Level 3: 2 + 1 + 3 + 7 = 13.
- Level 4: 4 + 6 = 10.
The 2nd largest level sum is 13.
Example 2:
Input: root = [1,2,null,3], k = 1
Output: 3
Explanation: The largest level sum is 3.


Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= 106
1 <= k <= n
 */