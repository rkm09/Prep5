package dailychallenge.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestDistance3243 {
    public static void main(String[] args) {
        ShortestDistance3243 s = new ShortestDistance3243();
        int[][] queries = {{2,4},{0,2},{0,4}};
        System.out.println(Arrays.toString(s.shortestDistanceAfterQueries(5, queries)));
    }

//    bottom up dp; time: O((n + q) * q), space: O(n + q)
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<Integer> resList = new ArrayList<>();
        List<List<Integer>> adjList = new ArrayList<>();
//        initialize adjacency list
        for(int i = 0 ; i < n ; i++)
            adjList.add(new ArrayList<>());
//        initialize edges between consecutive nodes
        for(int i = 0 ; i < n - 1 ; i++)
            adjList.get(i).add(i + 1);
//        process each query to add new nodes
        for(int[] road : queries) {
            int u = road[0];
            int v = road[1];
//            add the directed edge from u to v
            adjList.get(u).add(v);
//            calculate the minimum distance after adding the new node
            resList.add(minDistance(adjList, n));
        }
//        convert list to array before returning
        int[] res = new int[resList.size()];
        for(int i = 0 ; i < resList.size() ; i++)
            res[i] = resList.get(i);
        return res;
    }

//    function to find the minimum distance from 0 to n - 1
    private int minDistance(List<List<Integer>> adjList, int n) {
        int[] dp = new int[n];
//        base case: distance to destination (n-1) is 0
        dp[n - 1] = 0;
        for(int currentNode = n - 2 ; currentNode >= 0 ; currentNode--) {
            int minDistance = n;
//            explore neighbours to find the minimum distance
            for(int neighbour : adjList.get(currentNode)) {
                minDistance = Math.min(minDistance, dp[neighbour] + 1);
            }
//            store the calculated distance for the current node
            dp[currentNode] = minDistance;
        }
        return dp[0];
    }
}

/*
You are given an integer n and a 2D integer array queries.
There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.
queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After each query, you need to find the length of the shortest path from city 0 to city n - 1.
Return an array answer where for each i in the range [0, queries.length - 1], answer[i] is the length of the shortest path from city 0 to city n - 1 after processing the first i + 1 queries.
Example 1:
Input: n = 5, queries = [[2,4],[0,2],[0,4]]
Output: [3,2,1]
Explanation:
After the addition of the road from 2 to 4, the length of the shortest path from 0 to 4 is 3.
After the addition of the road from 0 to 2, the length of the shortest path from 0 to 4 is 2.
After the addition of the road from 0 to 4, the length of the shortest path from 0 to 4 is 1.
Example 2:
Input: n = 4, queries = [[0,3],[0,2]]
Output: [1,1]
Explanation:
After the addition of the road from 0 to 3, the length of the shortest path from 0 to 3 is 1.
After the addition of the road from 0 to 2, the length of the shortest path remains 1.

Constraints:
3 <= n <= 500
1 <= queries.length <= 500
queries[i].length == 2
0 <= queries[i][0] < queries[i][1] < n
1 < queries[i][1] - queries[i][0]
There are no repeated roads among the queries.

 */

/*
Bottom up dp:
Complexity Analysis
Let n be the number of cities and q the number of queries.
Time Complexity: O(q×(n+q)).
The findMinDistance function iterates over each edge exactly once, so its time complexity for a graph with e edges is O(e).
Therefore, like the previous approaches, the total time complexity of the algorithm can be expressed as:
O(n)+O(n+1)+...+O(n+q−1)= O(q×(n+q)).
Space Complexity: O(n+q).
The total space complexity is once again determined by the size of the adjacency list which is at most O(n+q).
 */