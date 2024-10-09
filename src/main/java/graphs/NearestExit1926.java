package graphs;


import java.util.ArrayDeque;
import java.util.Deque;

public class NearestExit1926 {

    public static void main(String[] args) {
        char[][] maze = {{'+','+','.','+'},{'.','.','.','+'},{'+','+','+','.'}};
        int[] entrance = {1,2};
        System.out.println(nearestExit(maze, entrance));
    }

//  bfs(in-place); time: O(m.n), space: O(max(m,n))
    public static int nearestExit(char[][] maze, int[] entrance) {
        int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};
        int rows = maze.length, cols = maze[0].length;
//        mark the entrance as visited as it is not an exit
        int startRow = entrance[0], startCol = entrance[1];
        maze[startRow][startCol] = '+';
//        start bfs from the entrance, and use a queue to store all the cells to be visited
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startRow, startCol, 0});
        while(!queue.isEmpty()) {
            int[] currentState = queue.poll();
            int currentRow = currentState[0], currentCol = currentState[1];
            int currentDistance = currentState[2];
//            for the current cell check its four neighbours
            for(int[] direction : directions) {
                int nextRow = currentRow + direction[0], nextCol = currentCol + direction[1];
//               if there exists an empty unvisited neighbour
                if(nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                && maze[nextRow][nextCol] == '.') {
//                    if this empty cell is an exit, return currentDistance + 1
                    if(nextRow == 0 || nextRow == rows - 1 || nextCol == 0 || nextCol == cols - 1)
                        return currentDistance + 1;
//                    else, add this cell to the queue and mark it as visited
                    maze[nextRow][nextCol] = '+';
                    queue.offer(new int[]{nextRow, nextCol, currentDistance + 1});
                }
            }
        }
//        if we finish, without finding an exit
        return -1;
    }
}

/*
You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.
In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.
Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.

Example 1:
Input: maze = [['+','+','.','+'],['.','.','.','+'],['+','+','+','.']], entrance = [1,2]
Output: 1
Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
Initially, you are at the entrance cell [1,2].
- You can reach [1,0] by moving 2 steps left.
- You can reach [0,2] by moving 1 step up.
It is impossible to reach [2,3] from the entrance.
Thus, the nearest exit is [0,2], which is 1 step away.
Example 2:
Input: maze = [['+','+','+'],['.','.','.'],['+','+','+']], entrance = [1,0]
Output: 2
Explanation: There is 1 exit in this maze at [1,2].
[1,0] does not count as an exit since it is the entrance cell.
Initially, you are at the entrance cell [1,0].
- You can reach [1,2] by moving 2 steps right.
Thus, the nearest exit is [1,2], which is 2 steps away.
Example 3:
Input: maze = [['.','+']], entrance = [0,0]
Output: -1
Explanation: There are no exits in this maze.
 
Constraints:
maze.length == m
maze[i].length == n
1 <= m, n <= 100
maze[i][j] is either '.' or '+'.
entrance.length == 2
0 <= entrancerow < m
0 <= entrancecol < n
entrance will always be an empty cell.
 */