package dailychallenge.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaxCount2554 {
    public static void main(String[] args) {
        int[] banned = {1,6,5};
        System.out.println(maxCount(banned,5,6));
    }

//    hashset; time: O(m + n), space: O(m)
    public static int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> set = new HashSet<>();
        for(int val : banned)
            set.add(val);
        int count = 0;
//        try each number from 1 to n
        for(int num = 1 ; num <= n ; num++) {
//            skip banned numbers
            if(set.contains(num)) continue;
//            return if adding number exceeds maxSum
            if(maxSum - num < 0) return count;
//            include current number
            maxSum -= num;
            count++;
        }
        return count;
    }

//    binary search; time: O((m + n)log(m + n)), space: O(S)
    public static int maxCount1(int[] banned, int n, int maxSum) {
        Arrays.sort(banned);
        int count = 0;
        for(int num = 1 ; num <= n ; num++) {
            if(binarySearch(banned, num)) continue;
            if(maxSum - num < 0) return count;
            maxSum -= num;
            count++;
        }
        return count;
    }

    private static boolean binarySearch(int[] banned, int num) {
        int left = 0;
        int right = banned.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(banned[mid] == num) return true;
            if(banned[mid] < num)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return false;
    }
}

/*
You are given an integer array banned and two integers n and maxSum. You are choosing some number of integers following the below rules:
The chosen integers have to be in the range [1, n].
Each integer can be chosen at most once.
The chosen integers should not be in the array banned.
The sum of the chosen integers should not exceed maxSum.
Return the maximum number of integers you can choose following the mentioned rules.

Example 1:
Input: banned = [1,6,5], n = 5, maxSum = 6
Output: 2
Explanation: You can choose the integers 2 and 4.
2 and 4 are from the range [1, 5], both did not appear in banned, and their sum is 6, which did not exceed maxSum.
Example 2:
Input: banned = [1,2,3,4,5,6,7], n = 8, maxSum = 1
Output: 0
Explanation: You cannot choose any integer while following the mentioned conditions.
Example 3:
Input: banned = [11], n = 7, maxSum = 50
Output: 7
Explanation: You can choose the integers 1, 2, 3, 4, 5, 6, and 7.
They are from the range [1, 7], all did not appear in banned, and their sum is 28, which did not exceed maxSum.

Constraints:
1 <= banned.length <= 104
1 <= banned[i], n <= 104
1 <= maxSum <= 109
 */