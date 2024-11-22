package dailychallenge.medium;

import java.util.HashMap;
import java.util.Map;

public class MaxEqual1072 {
    public static void main(String[] args) {
        int[][] matrix = {{1,0,1},{1,0,1}};
        System.out.println(maxEqualRowsAfterFlips(matrix));
    }

//    map; time: O(m.n), space: O(m.n)
    public static int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> patternFrequency = new HashMap<>();
        for(int[] row : matrix) {
            StringBuilder pattern = new StringBuilder();
            for(int col = 0 ; col < row.length ; col++) {
                if(row[0] == row[col])
                    pattern.append("T");
                else
                    pattern.append("F");
            }
            String rowPattern = pattern.toString();
            patternFrequency.put(rowPattern, patternFrequency.getOrDefault(rowPattern, 0) + 1);
        }
        int maxFrequency = 0;
        for(int frequency : patternFrequency.values()) {
            maxFrequency = Math.max(maxFrequency, frequency);
        }
        return maxFrequency;
    }
}

/*
You are given an m x n binary matrix.
You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the value of the cell from 0 to 1 or vice versa).
Return the maximum number of rows that have all values equal after some number of flips.

Example 1:
Input: matrix = [[0,1],[1,1]]
Output: 1
Explanation: After flipping no values, 1 row has all values equal.
Example 2:
Input: matrix = [[0,1],[1,0]]
Output: 2
Explanation: After flipping values in the first column, both rows have equal values.
Example 3:
Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
Output: 2
Explanation: After flipping values in the first two columns, the last two rows have equal values.

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is either 0 or 1.
 */

/*
Notice that a row and its complement actually form the same pattern, just with opposite digits.
To represent the pattern in a more abstract way, let's use 'T' for the first digit in each row and 'F' for its opposite. In the first row, 'T' stands for 0, while in the second row, 'T' stands for 1. Essentially, we are replacing every number in a row with a symbol signifying whether the number is equal to the first number in the grid. If we rewrite our grid using these symbols, it becomes a bit easier to see the underlying structure.
This means that if we replace each row with a unique pattern that represents it, then identical and even complementary rows will share the same pattern.
So, our task simplifies to just finding the pattern that shows up the most often.
 */