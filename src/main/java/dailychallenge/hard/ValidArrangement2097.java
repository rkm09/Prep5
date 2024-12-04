package dailychallenge.hard;

import java.util.*;

public class ValidArrangement2097 {
    public static void main(String[] args) {
        ValidArrangement2097 v = new ValidArrangement2097();
        int[][] pairs = {{5,1},{4,5},{11,9},{9,4}};
        int[][] res = v.validArrangement(pairs);
    }

//    euler path; time: O(v + e), space: O(v + e)
    public int[][] validArrangement(int[][] pairs) {
        Map<Integer, Deque<Integer>> adjMap = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();
//        build adjacency map and track in degree and out degree
        for(int[] pair : pairs) {
            int start = pair[0], end = pair[1];
            adjMap.computeIfAbsent(start, k -> new ArrayDeque<>()).add(end);
            outDegree.put(start, outDegree.getOrDefault(start, 0) + 1);
            inDegree.put(end, inDegree.getOrDefault(end, 0) + 1);
        }
//        find the start node (outDegree = inDegree + 1)
        int startNode = -1;
        for(int node : outDegree.keySet()) {
            if (outDegree.get(node) == inDegree.getOrDefault(node, 0) + 1) {
                startNode = node;
                break;
            }
        }
//        if no such node exists, start from the first pairs first node
        if(startNode == -1)
            startNode = pairs[0][0];
        List<Integer> res = new ArrayList<>();
//        start DFS traversal
        visit(startNode, adjMap, res);
//        reverse the result, as dfs gives us the result in reverse order
        Collections.reverse(res);
        int[][] result = new int[pairs.length][2];
        for(int i = 0 ; i < res.size() - 1 ; i++) {
            result[i] = new int[] {
                    res.get(i),
                    res.get(i + 1)
            };
        }
        return result;
    }

    private void visit(int node, Map<Integer, Deque<Integer>> adjMap, List<Integer> res) {
        Deque<Integer> neighbours = adjMap.get(node);
        while(neighbours != null && !neighbours.isEmpty()) {
            int nextNode = neighbours.poll();
            visit(nextNode, adjMap, res);
        }
        res.add(node);
    }

}

/*
You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.
Return any valid arrangement of pairs.
Note: The inputs will be generated such that there exists a valid arrangement of pairs.

Example 1:
Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
Output: [[11,9],[9,4],[4,5],[5,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 9 == 9 = start1
end1 = 4 == 4 = start2
end2 = 5 == 5 = start3
Example 2:
Input: pairs = [[1,3],[3,2],[2,1]]
Output: [[1,3],[3,2],[2,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 3 == 3 = start1
end1 = 2 == 2 = start2
The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.
Example 3:
Input: pairs = [[1,2],[1,3],[2,1]]
Output: [[1,2],[2,1],[1,3]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 2 == 2 = start1
end1 = 1 == 1 = start2

Constraints:
1 <= pairs.length <= 105
pairs[i].length == 2
0 <= starti, endi <= 109
starti != endi
No two pairs are exactly the same.
There exists a valid arrangement of pairs.
 */

/*
To solve this, we can borrow ideas from Eulerian paths. If you’re not familiar with them, the basic idea is that an Eulerian path in a graph visits every edge exactly once, and it turns out that this is pretty similar
to our goal where each pair’s end needs to connect smoothly(end i−1==start i to the start of the next pair.
The Rules of Eulerian Path
Eulerian paths have a couple of conditions:
In an undirected graph, either all nodes have an even degree, or exactly two have an odd degree.
In a directed graph (which is what we have here), we need to check if:
Each node’s outDegree matches its inDegree.
Or, exactly one node has one more outgoing edge (outDegree = inDegree + 1), which indicates our starting point.

Complexity Analysis
Let n be the number of pairs in the input pairs, V be the number of unique vertices in the graph formed by these pairs, and E be the number of edges in the graph, which equals n since each pair represents an edge.
Time Complexity: O(V+E)
Building the adjacency list and tracking degrees involves iterating through each pair once. For each pair, we perform constant-time operations to update the adjacency list and degree maps. This step takes O(n) time.
To find the start node, we iterate through the outDegree map, which has at most V entries. For each entry, we perform constant-time operations. This step takes O(V) time.
In the DFS traversal, we visit each edge exactly once. Since there are E edges, the DFS traversal itself takes O(E) time. During the traversal, we perform constant-time operations per edge, like popping from the deque and pushing to the result array.
Reversing the result array takes O(V) time because it contains V vertices. Finally, constructing the result pairs requires iterating through the result array once, performing constant-time operations for each vertex. This step also takes O(V) time.
Overall, the dominant term in the time complexity is O(V+E). Given that E=n, the time complexity can be expressed as O(V+n).
Space Complexity: O(V+E)
The adjacency list stores E edges, with each edge stored in a deque associated with a vertex. Thus, the total space used by the adjacency list is O(V+E).
The degree maps, both inDegree and outDegree, store one entry per unique vertex. Therefore, they take O(V) space.
The result array stores V vertices, requiring O(V) space.
The maximum depth of the recursive stack during DFS is V, so the recursive stack requires O(V) space.
Overall, the space complexity is dominated by the adjacency list, resulting in O(V+E).
*/
