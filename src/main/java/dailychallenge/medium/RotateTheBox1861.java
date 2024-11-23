package dailychallenge.medium;

public class RotateTheBox1861 {
    public static void main(String[] args) {
        char[][] box = {{'#','.','#'}};
        char[][] res = rotateTheBox(box);
    }

//    combined rotation and gravity operations; time: O(m.n), space: O(m.n)
    public static char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char[][] res = new char[n][m];
//        Initialize the result grid with '.'
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++)
                res[i][j] = '.';
        }
//        Apply gravity to let stones fall to the lowest possible empty cell in each column
        for(int i = 0 ; i < m ; i++) {
            int lowestRowWithEmptyCell = n - 1;
//            Process each cell in row `i` in reversed order
            for(int j = n - 1 ; j >= 0 ; j--) {
//                Found a stone - let it fall to the lowest empty cell
                if(box[i][j] == '#') {
//                    Place it in the correct position in the rotated grid
                    res[lowestRowWithEmptyCell][m - i - 1] = '#';
                    lowestRowWithEmptyCell--;
                } else if(box[i][j] == '*') {
//                    Found an obstacle - reset `lowestRowWithEmptyCell` to the row directly above it
//                    Place the obstacle in the correct position in the rotated grid
                    res[j][m - i - 1] = '*';
                    lowestRowWithEmptyCell = j - 1;
                }
            }
        }
        return res;
    }
}

/*
You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
A stone '#'
A stationary obstacle '*'
Empty '.'
The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
Return an n x m matrix representing the box after the rotation described above.
Example 1:
Input: box = [["#",".","#"]]
Output: [["."],
         ["#"],
         ["#"]]
Example 2:
Input: box = [["#",".","*","."],
              ["#","#","*","."]]
Output: [["#","."],
         ["#","#"],
         ["*","*"],
         [".","."]]
Example 3:
Input: box = [["#","#","*",".","*","."],
              ["#","#","#","*",".","."],
              ["#","#","#",".","#","."]]
Output: [[".","#","#"],
         [".","#","#"],
         ["#","#","*"],
         ["#","*","."],
         ["#",".","*"],
         ["#",".","."]]

Constraints:
m == box.length
n == box[i].length
1 <= m, n <= 500
box[i][j] is either '#', '*', or '.'.
 */
