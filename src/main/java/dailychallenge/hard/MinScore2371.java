package dailychallenge.hard;

import java.lang.reflect.Array;
import java.util.*;

public class MinScore2371 {
    public static void main(String[] args) {
        int[][] grid = {{3,1},{2,5}};
        int[][] res = minScore(grid);
        for(int[] r : res) {
            System.out.println(Arrays.toString(r));
        }
    }

//    sorting; time: O(m.n.log(m.n)), space: O(m.n)
    public static int[][] minScore(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] rowMax = new int[rows];
        int[] colMax = new int[cols];
        Arrays.fill(rowMax, 1);
        Arrays.fill(colMax, 1);
        List<int[]> nums = new ArrayList<>();
        for(int r = 0 ; r < rows ; r++) {
            for(int c = 0 ; c < cols ; c++) {
                int val = grid[r][c];
                nums.add(new int[] {val, r, c});
            }
        }
        nums.sort(Comparator.comparingInt(a -> a[0]));
        for(int[] num : nums) {
            int x = num[1];
            int y = num[2];
            // Determine the smallest assignable value based on rows and cols constraints
            int newValue = Math.max(rowMax[x], colMax[y]);
            grid[x][y] = newValue;
            // Update rows and cols arrays with the next possible value for each row and column
            rowMax[x] = newValue + 1;
            colMax[y] = newValue + 1;
        }
        return grid;
    }

//    priority queue; time: O(m.n.(log(m.n))), space: O(m.n)
    public static int[][] minScore1(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] rowMax = new int[rows];
        int[] colMax = new int[cols];
        Arrays.fill(rowMax, 1);
        Arrays.fill(colMax, 1);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for(int r = 0 ; r < rows ; r++) {
            for(int c = 0 ; c < cols ; c++) {
                int val = grid[r][c];
                minHeap.offer(new int[] {val, r, c});
            }
        }
        while(!minHeap.isEmpty()) {
            int[] element = minHeap.poll();
            int x = element[1];
            int y = element[2];
            // Determine the smallest assignable value based on rows and cols constraints
            int newValue = Math.max(rowMax[x], colMax[y]);
            grid[x][y] = newValue;
            // Update rows and cols arrays with the next possible value for each row and column
            rowMax[x] = newValue + 1;
            colMax[y] = newValue + 1;
        }
        return grid;
    }
}

/*
You are given an m x n integer matrix grid containing distinct positive integers.
You have to replace each integer in the matrix with a positive integer satisfying the following conditions:
The relative order of every two elements that are in the same row or column should stay the same after the replacements.
The maximum number in the matrix after the replacements should be as small as possible.
The relative order stays the same if for all pairs of elements in the original matrix such that grid[r1][c1] > grid[r2][c2] where either r1 == r2 or c1 == c2, then it must be true that grid[r1][c1] > grid[r2][c2] after the replacements.
For example, if grid = [[2, 4, 5], [7, 3, 9]] then a good replacement could be either grid = [[1, 2, 3], [2, 1, 4]] or grid = [[1, 2, 3], [3, 1, 4]].
Return the resulting matrix. If there are multiple answers, return any of them.

Example 1:
Input: grid = [[3,1],[2,5]]
Output: [[2,1],[1,2]]
Explanation: The above diagram shows a valid replacement.
The maximum number in the matrix is 2. It can be shown that no smaller value can be obtained.
Example 2:
Input: grid = [[10]]
Output: [[1]]
Explanation: We replace the only number in the matrix with 1.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 1000
1 <= m * n <= 105
1 <= grid[i][j] <= 109
grid consists of distinct integers.
 */

/*
We are given an integer grid with distinct positive integers and must replace each integer with a positive number such that the relative ordering of elements in each row and column remains the same. The goal is to minimize the maximum value in the modified grid.

These are some trivial observations before we start solving the problem:

Consecutive numbers starting from 1 should be assigned to the matrix values. For example, 3 cannot be assigned until 2 has been assigned to some cell.
The smallest value in the matrix can always be assigned 1, as no value in its row or column is smaller.
In other words, we need to replace every value in the matrix with its rank, starting from the smallest element.

This problem has a conceptual similarity to topological sort, as the values in the matrix are processed in a specific order (increasing order of values), and each cell’s rank depends on the ranks of previously processed cells in its row and column.

However, unlike in the related problem Rank Transform of a Matrix, we do not need to explicitly use graph-based approaches like Disjoint Set Union (DSU) or adjacency lists.
This is because:

All integers in the matrix are distinct, which eliminates the need to group equal elements or handle ties, a common use case for DSU in problems like "Rank Transform of a Matrix."
The condition that grid[r1][c1] > grid[r2][c2] in the original matrix (if both belong to the same row or column) naturally holds after processing, as the values are handled in sorted order. Sorting the matrix ensures that dependencies are resolved sequentially, much like the ordering in topological sort.
Since there are no ties, ranks can be directly assigned during the traversal of the sorted list, avoiding the need for additional grouping or merging steps.
The task is to assign values to matrix elements such that each value satisfies two constraints: it must preserve the relative ordering of values in rows and columns, and it must be as small as possible under these constraints. For example, the smallest element in the matrix can always be assigned the value 1, but for the second-smallest element, its value depends on its position. If it shares the same row or column as the smallest element, it cannot also be assigned 1 and must instead take the value 2. Similarly, the third-smallest element, if it is in the same row or column as the second-smallest, must be assigned a value no less than 3.

This leads to a crucial observation that the smallest value that a matrix cell can be assigned is determined by the maximum value assigned in its row or column, plus one. To implement this idea, we start by sorting all matrix elements in ascending order. Then, for each element, we compute its value as max(max rank in its row, max rank in its column) + 1. This ensures that all constraints are satisfied while keeping the assigned values minimal.


 */