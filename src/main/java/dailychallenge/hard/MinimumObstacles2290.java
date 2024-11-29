package dailychallenge.hard;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

public class MinimumObstacles2290 {
//    directions for movement; right, left, down, up
    private final int[][] directions = {
            {0,1},
            {0,-1},
            {1,0},
            {-1,0}
    };
    public static void main(String[] args) {
        MinimumObstacles2290 m = new MinimumObstacles2290();
        int[][] grid = {{0,1,1},{1,1,0},{1,1,0}};
        System.out.println(m.minimumObstacles(grid));
    }

//    0-1 bfs; time: O(m.n), space: O(m.n)
//    cells are processed in increasing order of costs
    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
//        distance matrix used to store minimum obstacles removed to reach cell
        int[][] minObstacles = new int[m][n];
//        initialize each cell with large value to represent unvisited cells
        for(int i = 0 ; i < m ; i++)
            for(int j = 0 ; j < n ; j++)
                minObstacles[i][j] = Integer.MAX_VALUE;
        minObstacles[0][0] = 0;
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{0,0,0}); // obstacles, row, col
        while(!deque.isEmpty()) {
            int[] current = deque.poll();
            int obstacles = current[0], row = current[1], col = current[2];
//            explore all four possible directions from the current cell
            for(int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if(isValid(grid, newRow, newCol) && minObstacles[newRow][newCol] == Integer.MAX_VALUE) {
                    if(grid[newRow][newCol] == 1) {
//                        if it's an obstacle, add 1 to the obstacle count and push to the back
                        minObstacles[newRow][newCol] = obstacles + 1;
                        deque.addLast(new int[]{obstacles + 1, newRow, newCol});
                    } else {
//                        if it's an empty cell, keep the existing obstacle count and push to the front
                        minObstacles[newRow][newCol] = obstacles;
                        deque.addFirst(new int[]{obstacles, newRow, newCol});
                    }
                }
            }
        }
        return minObstacles[m - 1][n - 1];
    }

//    helper method to check whether the cell is within the grid bounds
    private boolean isValid(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

//    dijikstra; time: O(m.nlog(m.n)), space: O(m.n)
    public int minimumObstacles1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] minObstacles = new int[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for(int i = 0 ; i < m ; i++)
            for(int j = 0 ; j < n ; j++)
                minObstacles[i][j] = Integer.MAX_VALUE;
        minObstacles[0][0] = 0;
        pq.offer(new int[]{0,0,0});
        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int obstacles = current[0], row = current[1], col = current[2];
            if(row == m - 1 && col == n - 1)
                return obstacles;
            for(int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if(isValid(grid, newRow, newCol)) {
                    int newObstacles = obstacles + grid[newRow][newCol];
                    if(newObstacles < minObstacles[newRow][newCol]) {
                        minObstacles[newRow][newCol] = newObstacles;
                        pq.offer(new int[]{newObstacles, newRow, newCol});
                    }
                }
            }
        }
        return minObstacles[m - 1][n - 1];
    }
}

/*
You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:
0 represents an empty cell,
1 represents an obstacle that may be removed.
You can move up, down, left, or right from and to an empty cell.
Return the minimum number of obstacles to remove so you can move from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1).
Example 1:
Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
Output: 2
Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a path from (0, 0) to (2, 2).
It can be shown that we need to remove at least 2 obstacles, so we return 2.
Note that there may be other ways to remove 2 obstacles to create a path.
Example 2:
Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
Output: 0
Explanation: We can move from (0, 0) to (2, 4) without removing any obstacles, so we return 0.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 105
2 <= m * n <= 105
grid[i][j] is either 0 or 1.
grid[0][0] == grid[m - 1][n - 1] == 0

 */

/*
Approach 2: 0-1 Breadth-First Search (BFS)
As stated earlier, moving through cells without obstacles has no cost. Therefore, we prioritize exploring neighboring empty cells first, only moving to cells with obstacles when no free cells are left.
We perform a BFS using a deque to manage the queue. When exploring neighboring cells, we add empty cells to the front of the deque for immediate exploration, and cells with obstacles to the back, delaying their exploration.
We maintain a result grid, minObstacles, initialized to infinity (indicating they are unvisited), to track the minimum obstacles encountered at each cell. We'll add the top left cell to the deque and begin our exploration. At each step, we'll pop the top cell in the deque and explore its neighbors. All empty neighbors go to the front of the deque, while others go to the bottom with their obstacle count increased by 1. Simultaneously, we'll update the minObstacles value for each neighboring position.
Once all cells are explored, the value at the bottom-right cell of minObstacles will give the minimum obstacles encountered on the shortest path.
Let m be the number of rows and n be the number of columns in the grid.
Time complexity: O(m⋅n)
Each of the m⋅n cells in the grid is visited exactly once because we only process unvisited cells. The deque operations are all O(1).
Thus, the total time complexity is O(m⋅n).
Space complexity: O(m⋅n)
The minObstacles array and the deque both take O(m⋅n) space. All other variables take constant space.
Thus, the space complexity remains O(m⋅n).
 */