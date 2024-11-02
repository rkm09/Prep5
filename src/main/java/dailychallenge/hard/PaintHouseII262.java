package dailychallenge.hard;

public class PaintHouseII262 {
    public static void main(String[] args) {
        int[][] costs = {{1,5,3},{2,9,4}};
        System.out.println(minCostII(costs));
    }

//    dp; time: O(n.k^2), space: O(1) [in place]
    public static int minCostII(int[][] costs) {
        if(costs.length == 0) return 0;
        int k = costs[0].length, n = costs.length;
        for(int house = 1   ; house < n ; house++) {
            for(int color = 0 ; color < k ; color++) {
                int minCost = Integer.MAX_VALUE;
                for(int previousColor = 0 ; previousColor < k ; previousColor++) {
                    if(color == previousColor) continue;
                    minCost = Math.min(minCost, costs[house - 1][previousColor]);
                }
                costs[house][color] += minCost;
            }
        }
        int minCost = Integer.MAX_VALUE;
        for(int c : costs[n - 1])
            minCost = Math.min(minCost, c);
        return minCost;
    }
}

/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by an n x k cost matrix costs.
For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
Return the minimum cost to paint all houses.

Example 1:
Input: costs = [[1,5,3],[2,9,4]]
Output: 5
Explanation:
Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
Example 2:
Input: costs = [[1,3],[2,4]]
Output: 5

Constraints:
costs.length == n
costs[i].length == k
1 <= n <= 100
2 <= k <= 20
1 <= costs[i][j] <= 20

Follow up: Could you solve it in O(nk) runtime?
 */