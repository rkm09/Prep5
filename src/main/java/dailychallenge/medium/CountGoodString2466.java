package dailychallenge.medium;

import java.util.Arrays;

public class CountGoodString2466 {
    int mod = 1_000_000_007;
    int[] memo;
    public static void main(String[] args) {

    }

//    bottom up dp; time: O(n), space: O(n) [faster]
    public int countGoodStrings(int low, int high, int zero, int one) {
//        use dp[i] to record the number of good strings of length i
        int[] dp = new int[high + 1];
        dp[0] = 1;
//        iterate over each length 'end'
        for(int end = 1 ; end <= high ; end++) {
//            check if the current string can be made by appending zero '0' and one '1'
            if(end >= zero)
                dp[end] += dp[end - zero];
            if(end >= one)
                dp[end] += dp[end - one];
            dp[end] %= mod;
        }
//        add up the number of strings with each valid length
        int count = 0;
        for(int i = low ; i <= high ; i++) {
            count += dp[i];
            count %= mod;
        }
        return count;
    }

//    top down (recursive + memoization); time: O(n), space: O(n)
    public int countGoodStrings1(int low, int high, int zero, int one) {
        memo = new int[high + 1];
        int answer = 0;
        Arrays.fill(memo, - 1);
        memo[0] = 1;
        for(int end = low ; end <= high ; end++) {
            answer += dfs(end, zero, one);
            answer %= mod;
        }
        return answer;
    }

    private int dfs(int end, int zero, int one) {
//        base case
        if(memo[end] != -1)
            return memo[end];
        int count = 0;
        if(end >= zero)
            count += dfs(end - zero, zero, one);
        if(end >= one)
            count += dfs(end - one, zero, one);
        memo[end] = count % mod;
        return memo[end];
    }
}

/*
Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:
Append the character '0' zero times.
Append the character '1' one times.
This can be performed any number of times.
A good string is a string constructed by the above process having a length between low and high (inclusive).
Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.
Example 1:
Input: low = 3, high = 3, zero = 1, one = 1
Output: 8
Explanation:
One possible valid good string is "011".
It can be constructed as follows: "" -> "0" -> "01" -> "011".
All binary strings from "000" to "111" are good strings in this example.
Example 2:
Input: low = 2, high = 3, zero = 1, one = 2
Output: 5
Explanation: The good strings are "00", "11", "000", "110", and "011".

Constraints:
1 <= low <= high <= 105
1 <= zero, one <= low
 */

/*
bottom-up:
Note that every good string either ends with zero of 0s or one of 1s, which in our case is 0 or 11.
If a good string of length 5 ends with 0, it means that every good string of length 4 can be turned into a good string of length 5 by appending 0. Thus we increment dp[5] by dp[4], which in the general case is dp[end] += dp[end - zero].
Note that it is suggested to check if end >= zero before we increment dp[end], and only apply the increase if end >= zero.
top-down:
The trick is as described before, each time a recursive function calls itself, it reduces the given problem dfs(end) into subproblems dfs(end - zero) and dfs(end - one). The recursion call continues until it reaches a point where the subproblem can be solved without further recursion, that is dfs(0) = 1.
Similarly, we will also build an auxiliary array dp to avoid repeated computation. Initially, we set every value dp[i] (except dp[0]) as -1, which also implies that dp[i] is not visited. During the recursion, if dp[end] != -1, it means we have already calculated dfs(end) previously, so just return dp[end].
 */