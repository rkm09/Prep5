package dailychallenge.hard;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumTime2577 {
    private final int[][] directions = {
            {0,1},{0,-1},{1,0},{-1,0}
    };
    public static void main(String[] args) {
        MinimumTime2577 m = new MinimumTime2577();
        int[][] grid = {{0,1,3,2},{5,1,2,5},{4,3,8,6}};
        System.out.println(m.minimumTime(grid));
    }

    public int minimumTime(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] minTime = new int[m][n];
        for(int i = 0 ; i < m ; i++)
            for(int j = 0 ; j < n ; j++)
                minTime[i][j] = Integer.MAX_VALUE;
        minTime[0][0] = grid[0][0];
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{grid[0][0],0,0});
        while(!deque.isEmpty()) {
            int[] current = deque.poll();
            int time = current[0], row = current[1], col = current[2];
            for(int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if(isValid(grid, newRow, newCol) && minTime[newRow][newCol] == Integer.MAX_VALUE) {
                    if(grid[newRow][newCol] <= time + 1) {
                        minTime[newRow][newCol] = time + 1;
                        deque.offer(new int[]{time + 1, newRow, newCol});
                    }
                }
            }
        }
        return minTime[m - 1][n - 1] == Integer.MAX_VALUE ? -1 : minTime[m - 1][n - 1];
    }

    private boolean isValid(int[][] grid, int row, int col) {
        return (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length);
    }
}

/*
You are given a m x n matrix grid consisting of non-negative integers where grid[row][col] represents the minimum time required to be able to visit the cell (row, col), which means you can visit the cell (row, col) only when the time you visit it is greater than or equal to grid[row][col].
You are standing in the top-left cell of the matrix in the 0th second, and you must move to any adjacent cell in the four directions: up, down, left, and right. Each move you make takes 1 second.
Return the minimum time required in which you can visit the bottom-right cell of the matrix. If you cannot visit the bottom-right cell, then return -1.

Example 1:
Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
Output: 7
Explanation: One of the paths that we can take is the following:
- at t = 0, we are on the cell (0,0).
- at t = 1, we move to the cell (0,1). It is possible because grid[0][1] <= 1.
- at t = 2, we move to the cell (1,1). It is possible because grid[1][1] <= 2.
- at t = 3, we move to the cell (1,2). It is possible because grid[1][2] <= 3.
- at t = 4, we move to the cell (1,1). It is possible because grid[1][1] <= 4.
- at t = 5, we move to the cell (1,2). It is possible because grid[1][2] <= 5.
- at t = 6, we move to the cell (1,3). It is possible because grid[1][3] <= 6.
- at t = 7, we move to the cell (2,3). It is possible because grid[2][3] <= 7.
The final time is 7. It can be shown that it is the minimum time possible.
Example 2:
Input: grid = [[0,2,4],[3,2,1],[1,0,4]]
Output: -1
Explanation: There is no path from the top left to the bottom-right cell.

Constraints:
m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 105
0 <= grid[i][j] <= 105
grid[0][0] == 0

 */