package dailychallenge.medium;

public class KnightsTour2664 {
    public static void main(String[] args) {
        KnightsTour2664 k = new KnightsTour2664();
        int[][] res = k.tourOfKnight(1,1,0,0);
    }

//    backtracking; time: O(8^(m*n)), space: O(m*n)
//    The algorithm uses a depth-first search approach to explore all possible knight's tours. In the worst case, it might
//    need to examine every possible sequence of moves before finding a valid tour or concluding that no tour exists.
//    For each cell, the knight has up to 8 possible moves. The algorithm explores each of these moves recursively. The
//    depth of this recursion can go up to the total number of cells on the board, which is m×n.
//    This leads to a time complexity of O(8^(m×n)), which is exponential. It's worth noting that this is a loose upper
//    bound, as the algorithm employs backtracking and prunes invalid moves, which can significantly reduce the actual
//    number of explored paths in practice.
    public int[][] tourOfKnight(int m, int n, int r, int c) {
        int[][] chessboard = new int[m][n];
        chessboard[r][c] = -1;
//        solve recursively
        solveKnightsTour(m, n, r, c, chessboard, 1);
        chessboard[r][c] = 0;
        return chessboard;
    }

    private boolean solveKnightsTour(int rows, int cols, int currentRow, int currentCol, int[][] chessboard, int moveCount) {
//        base case: if all cells have been visited, we have found a solution
        if(moveCount == rows * cols) return true;
//        try all possible knight moves
        for(int newRow = 0 ; newRow < rows ; newRow++) {
            for(int newCol = 0 ; newCol < cols ; newCol++) {
//                check if the move is valid
                if(!isValid(chessboard, currentRow, currentCol, newRow, newCol))
                    continue;
                chessboard[newRow][newCol] = moveCount;
//                recursively try to solve from this new board position
                if(solveKnightsTour(rows, cols, newRow, newCol, chessboard, moveCount + 1))
                    return true;
//                if the move doesn't lead to solution then backtrack
                chessboard[newRow][newCol] = 0;
            }
        }
//        if no solution is found from the current position return false
        return false;
    }

    private boolean isValid(int[][] chessboard, int fromRow, int fromCol, int toRow, int toCol) {
        return (
                toRow >= 0 && toCol >= 0 && toRow < chessboard.length && toCol < chessboard[0].length &&
                        Math.min(Math.abs(fromRow - toRow), Math.abs(fromCol - toCol)) == 1 &&
                        Math.max(Math.abs(fromRow - toRow), Math.abs(fromCol - toCol)) == 2 &&
                        chessboard[toRow][toCol] == 0
                );
    }
}

/*
Given two positive integers m and n which are the height and width of a 0-indexed 2D-array board, a pair of positive integers (r, c) which is the starting position of the knight on the board.
Your task is to find an order of movements for the knight, in a manner that every cell of the board gets visited exactly once (the starting cell is considered visited and you shouldn't visit it again).
Return the array board in which the cells' values show the order of visiting the cell starting from 0 (the initial place of the knight).
Note that a knight can move from cell (r1, c1) to cell (r2, c2) if 0 <= r2 <= m - 1 and 0 <= c2 <= n - 1 and min(abs(r1 - r2), abs(c1 - c2)) = 1 and max(abs(r1 - r2), abs(c1 - c2)) = 2.

Example 1:
Input: m = 1, n = 1, r = 0, c = 0
Output: [[0]]
Explanation: There is only 1 cell and the knight is initially on it so there is only a 0 inside the 1x1 grid.
Example 2:
Input: m = 3, n = 4, r = 0, c = 0
Output: [[0,3,6,9],[11,8,1,4],[2,5,10,7]]
Explanation: By the following order of movements we can visit the entire board.
(0,0)->(1,2)->(2,0)->(0,1)->(1,3)->(2,1)->(0,2)->(2,3)->(1,1)->(0,3)->(2,2)->(1,0)

Constraints:
1 <= m, n <= 5
0 <= r <= m - 1
0 <= c <= n - 1
The inputs will be generated such that there exists at least one possible order of movements with the given condition
 */
