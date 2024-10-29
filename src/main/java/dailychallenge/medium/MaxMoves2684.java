package dailychallenge.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxMoves2684 {
    public static void main(String[] args) {
        MaxMoves2684 m = new MaxMoves2684();
        int[][] grid = {{2,4,3,5},{5,4,9,3},{3,4,2,11},{10,9,13,15}};
        System.out.println(m.maxMoves(grid));
    }

//    bfs; time: O(M.N), space: O(M.N)
    public int maxMoves(int[][] grid) {
        int[] directions = {-1,0,1};
        int maxMoves = 0;
        int m = grid.length, n = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        for(int i = 0 ; i < m ; i++) {
            queue.offer(new int[]{i, 0, 0});
            visited[i][0] = true;
        }
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int[] cellInfo = queue.poll();
                int row = cellInfo[0], col = cellInfo[1];
                int count = cellInfo[2];
                maxMoves = Math.max(maxMoves, count);
                for(int direction : directions) {
                    int nextRow = row + direction;
                    int nextCol = col + 1;
                    if(isValid(grid, row, col, nextRow, nextCol, visited)) {
                        visited[nextRow][nextCol] = true;
                        queue.offer(new int[]{nextRow, nextCol, count + 1});
                    }
                }
            }
        }
        return maxMoves;
    }

    private boolean isValid(int[][] grid, int fromRow, int fromCol, int toRow, int toCol, boolean[][] visited) {
        return (toRow >= 0 && toRow < grid.length && toCol >= 0 && toCol < grid[0].length
                && !visited[toRow][toCol] && grid[toRow][toCol] > grid[fromRow][fromCol]);
    }
}

/*
You are given a 0-indexed m x n matrix grid consisting of positive integers.
You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
Return the maximum number of moves that you can perform.

Example 1:
Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
Output: 3
Explanation: We can start at the cell (0, 0) and make the following moves:
- (0, 0) -> (0, 1).
- (0, 1) -> (1, 2).
- (1, 2) -> (2, 3).
It can be shown that it is the maximum number of moves that can be made.
Example 2:
Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
Output: 0
Explanation: Starting from any cell in the first column we cannot perform any moves.

Constraints:
m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 105
1 <= grid[i][j] <= 106
 */