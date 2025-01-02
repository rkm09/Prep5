package dailychallenge.easy;

public class MaxScore4622 {
    public static void main(String[] args) {
        String s = "011101";
        System.out.println(maxScore(s));
    }

//    one pass; time: O(n), space: O(1)
    public static int maxScore(String s) {
        int n = s.length(), ones = 0, zeros = 0;
        int best = Integer.MIN_VALUE;
        for(int i = 0 ; i < n - 1 ; i++) {
            if(s.charAt(i) == '1') ones++;
            else zeros++;
            best = Math.max(best, zeros - ones);
        }
        if(s.charAt(n - 1) == '1') ones++;
        return best + ones;
    }

//    count zeros and ones; time: O(n), space: O(1)
    public static int maxScore1(String s) {
        int ones = 0, n = s.length();
        for(int i = 0 ; i < n ; i++)
            if(s.charAt(i) == '1') ones++;
        int ans = 0, zeros = 0;
        for(int i = 0 ; i < n - 1; i++) {
            if(s.charAt(i) == '1')
                ones--;
            else
                zeros++;
            ans = Math.max(ans, zeros + ones);
        }
        return ans;
    }

//    def; left sum & right sum; time: O(n), space: O(n)
    public static int maxScore2(String s) {
        int n = s.length();
        int[] leftSum = new int[n];
        int[] rightSum = new int[n];
        leftSum[0] = s.charAt(0) == '0' ? 1 : 0;
        rightSum[n - 1] = s.charAt(n - 1) == '1' ? 1 : 0;
        for(int i = 1 ; i < n - 1; i++)
            leftSum[i] = leftSum[i - 1] + (s.charAt(i) == '0' ? 1 : 0);
        for(int i = n - 2 ; i > 0 ; i--)
            rightSum[i] = rightSum[i + 1] + (s.charAt(i) == '1' ? 1 : 0);
        int maxScore = 0;
        for(int i = 0 ; i < n - 1; i++)
            maxScore = Math.max(maxScore, leftSum[i] + rightSum[i + 1]);
        return maxScore;
    }
}

/*
Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings (i.e. left substring and right substring).
The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.
Example 1:
Input: s = "011101"
Output: 5
Explanation:
All possible ways of splitting s into two non-empty substrings are:
left = "0" and right = "11101", score = 1 + 4 = 5
left = "01" and right = "1101", score = 1 + 3 = 4
left = "011" and right = "101", score = 1 + 2 = 3
left = "0111" and right = "01", score = 1 + 1 = 2
left = "01110" and right = "1", score = 2 + 1 = 3
Example 2:
Input: s = "00111"
Output: 5
Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5
Example 3:
Input: s = "1111"
Output: 3

Constraints:
2 <= s.length <= 500
The string s consists of characters '0' and '1' only.
 */

/*
score = Z[L] + O[R] => O[R] = O[T] - O[L] => Z[L] + O[T] - O[L] = Z[L] - O[L] + O[T]
Recall that we don't iterate i over the final index since it would mean having an empty right part. Once we are done iterating over s, we will check the final index to see if it is a 1. If it is, we increment ones.
The reason we explicitly check the final index for 1 is that we want ones to represent O
in the end, but when we calculate ones, we don't iterate over the last index, so we need to account for it. Now, we have best as the maximum of all Z
and ones represents O, we can return best + ones as the answer.
 */