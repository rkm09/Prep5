package dailychallenge.medium;

import java.util.Arrays;

public class CountFairPairs2563 {
    public static void main(String[] args) {
        int[] nums = {1,7,9,2,5};
        CountFairPairs2563 c = new CountFairPairs2563();
        System.out.println(c.countFairPairs(nums, 11, 11));
    }

//    two pointers; time: O(nlogn), space: O(logn)
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return lowerBound(nums, upper + 1) - lowerBound(nums, lower);
    }

    private long lowerBound(int[] nums, int value) {
        int left = 0, right = nums.length - 1;
        long result = 0;
        while(left < right) {
            int sum = nums[left] + nums[right];
            if(sum < value) {
                result += right - left;
                left++;
            } else {
                right--;
            }
        }
        return result;
    }
}

/*
Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.
A pair (i, j) is fair if:
0 <= i < j < n, and
lower <= nums[i] + nums[j] <= upper
Example 1:
Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
Output: 6
Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).
Example 2:
Input: nums = [1,7,9,2,5], lower = 11, upper = 11
Output: 1
Explanation: There is a single fair pair: (2,3).

Constraints:
1 <= nums.length <= 105
nums.length == n
-109 <= nums[i] <= 109
-109 <= lower <= upper <= 109
 */
