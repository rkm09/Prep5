package dailychallenge.medium;


public class CanChange2337 {
    public static void main(String[] args) {
        String start = "_L";
        String target = "LL";
        System.out.println(canChange(start, target));
    }

//    two pointer; time: O(n), space: O(1)
    public static boolean canChange(String start, String target) {
//        Pointers for start string and target string
        int startIndex = 0, targetIndex = 0;
        int startLength = start.length();
        while(startIndex < startLength || targetIndex < startLength) {
//            Skip underscores in start
            while(startIndex < startLength && start.charAt(startIndex) == '_')
                startIndex++;
//            Skip underscores in target
            while(targetIndex < startLength && target.charAt(targetIndex) == '_')
                targetIndex++;
//            If one string is exhausted, both should be exhausted
            if(startIndex == startLength || targetIndex == startLength)
                return startIndex == startLength && targetIndex == startLength;
//            Check if the pieces match and follow movement rules
            if(start.charAt(startIndex) != target.charAt(targetIndex)
            || (start.charAt(startIndex) == 'L' && startIndex < targetIndex)
            || (start.charAt(startIndex) == 'R' && startIndex > targetIndex))
                return false;
            startIndex++;
            targetIndex++;
        }
//        If all conditions are satisfied, return true
        return true;
    }
}

/*
You are given two strings start and target, both of length n. Each string consists only of the characters 'L', 'R', and '_' where:
The characters 'L' and 'R' represent pieces, where a piece 'L' can move to the left only if there is a blank space directly to its left, and a piece 'R' can move to the right only if there is a blank space directly to its right.
The character '_' represents a blank space that can be occupied by any of the 'L' or 'R' pieces.
Return true if it is possible to obtain the string target by moving the pieces of the string start any number of times. Otherwise, return false.

Example 1:
Input: start = "_L__R__R_", target = "L______RR"
Output: true
Explanation: We can obtain the string target from start by doing the following moves:
- Move the first piece one step to the left, start becomes equal to "L___R__R_".
- Move the last piece one step to the right, start becomes equal to "L___R___R".
- Move the second piece three steps to the right, start becomes equal to "L______RR".
Since it is possible to get the string target from start, we return true.
Example 2:
Input: start = "R_L_", target = "__LR"
Output: false
Explanation: The 'R' piece in the string start can move one step to the right to obtain "_RL_".
After that, no pieces can move anymore, so it is impossible to obtain the string target from start.
Example 3:
Input: start = "_R", target = "R_"
Output: false
Explanation: The piece in the string start can move only to the right, so it is impossible to obtain the string target from start.

Constraints:
n == start.length == target.length
1 <= n <= 105
start and target consist of the characters 'L', 'R', and '_'.
 */
