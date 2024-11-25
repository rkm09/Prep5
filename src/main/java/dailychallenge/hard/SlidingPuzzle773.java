package dailychallenge.hard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class SlidingPuzzle773 {
    public static void main(String[] args) {
        SlidingPuzzle773 s = new SlidingPuzzle773();
        int[][] board = {{1,2,3},{4,0,5}};
        System.out.println(s.slidingPuzzle(board));
    }

//    bfs; time: O((m.n)! * (m.n)), space: O(m.n)!
    public int slidingPuzzle(int[][] board) {
//        direction map for zero's possible moves in 1D representation of 2*3 grid // 1st row: 0-1-2, second row: 3-4-5
        int[][] directions = {
                {1,3},
                {0,4,2},
                {1,5},
                {0,4},
                {3,1,5},
                {4,2}
        };
        String target = "123450";
        StringBuilder startState = new StringBuilder();
//        convert the 2D board into a string representation
        for(int[] row : board) {
            for(int cell : row) {
                startState.append(cell);
            }
        }
//        store visited states
        Set<String> visited = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(startState.toString());
        visited.add(startState.toString());
        int moves = 0;
//      bfs to find minimum number of moves
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                String currentState = queue.poll();
//                check if we have reached the target state
                if(target.equals(currentState))
                    return moves;
                int zeroPos = currentState.indexOf('0');
                for(int nextPos : directions[zeroPos]) {
                    String nextState = swap(currentState, zeroPos, nextPos);
//                    skip if this state has been visited
                    if(visited.contains(nextState)) continue;
//                    mark the new state as visited and add it to the queue
                    queue.offer(nextState);
                    visited.add(nextState);
                }
            }
            moves++;
        }
        return -1;
    }

//    helper method to swap characters at indices i and j
    private String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        return sb.toString();
    }

}

/*
On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
Given the puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
Example 1:
Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Example 2:
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Example 3:
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]

Constraints:
board.length == 2
board[i].length == 3
0 <= board[i][j] <= 5
Each value board[i][j] is unique.
 */

/*
The DFS approach explores all possible board states before reaching the final state, which can be inefficient. Although we might find the solution early, DFS will still continue to explore all paths, potentially with non-optimal move counts. To address this, we switch to Breadth-First Search (BFS). BFS is better suited in scenarios like this because it explores all states at the current move level before going deeper, ensuring that the first time it reaches the goal, it has found the shortest path.
Our setup remains similar: we convert the board to a 1-D string and use a set to track visited states. A queue will handle the BFS traversal, starting from the initial state. The queue’s structure works well to support BFS’s layered exploration, since each level is processed sequentially and we stop as soon as we reach the goal.

We then loop while the queue is not empty, processing all states at the current move count. If we encounter the final state, we return the current move count as the answer. Otherwise, we explore all possible moves from the current state, modify the board accordingly, and, if unvisited, add the new state to the queue for further exploration.
Let m be the number of rows and n be the number of columns of the board.

Time complexity: O((m⋅n)!×(m⋅n))

The algorithm uses Breadth-First Search (BFS) to explore all possible board configurations. With (m⋅n)! unique configurations, BFS may process each configuration once. Each configuration requires checking moves and generating new ones, taking O(m⋅n) operations.

Therefore, the overall time complexity is O((m⋅n)!×(m⋅n)).

Space complexity: O((m⋅n)!)

The space complexity is determined by the visited set and the BFS queue, each of which can hold up to (m⋅n)! unique configurations in the worst case. Therefore, the space complexity is O((m⋅n)!).


 */