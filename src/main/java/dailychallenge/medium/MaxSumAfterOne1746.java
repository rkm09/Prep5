package dailychallenge.medium;

public class MaxSumAfterOne1746 {
    public static void main(String[] args) {

    }

//   maxLeft & maxRight, time: O(n), space: O(n)
    public int maxSumAfterOperation(int[] nums) {
        int n = nums.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        maxLeft[0] = 0;
        for(int i = 1 ; i < n ; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1] + nums[i - 1], 0);
        }
        maxRight[n - 1] = 0;
        for(int i = n - 2 ; i >= 0 ; i--) {
            maxRight[i] = Math.max(maxRight[i + 1] + nums[i + 1], 0);
        }
        int maxSum = 0;
        for(int i = 0 ; i < n ; i++) {
            maxSum = Math.max(maxSum, maxLeft[i] + nums[i] * nums[i] + maxRight[i]);
        }
        return maxSum;
    }
}

/*
You are given an integer array nums. You must perform exactly one operation where you can replace one element nums[i] with nums[i] * nums[i].
Return the maximum possible subarray sum after exactly one operation. The subarray must be non-empty.
Example 1:
Input: nums = [2,-1,-4,-3]
Output: 17
Explanation: You can perform the operation on index 2 (0-indexed) to make nums = [2,-1,16,-3]. Now, the maximum subarray sum is 2 + -1 + 16 = 17.
Example 2:
Input: nums = [1,-1,1,1,-1,-1,1]
Output: 4
Explanation: You can perform the operation on index 1 (0-indexed) to make nums = [1,1,1,1,-1,-1,1]. Now, the maximum subarray sum is 1 + 1 + 1 + 1 = 4.

Constraints:
1 <= nums.length <= 105
-104 <= nums[i] <= 104
 */

/*
MaxLeft & MaxRight:
First, it's important to see that squaring an element outside the final maximum-sum subarray doesn't help. Squaring increases the value of an element, so it makes sense only to square an element that is part of the subarray contributing to the maximum sum.
This means the result subarray will look like this:
A prefix of unchanged elements from nums (possibly empty).
A single squared element nums[i] * nums[i].
A suffix of unchanged elements from nums (possibly empty).
Therefore, we calculate for each index i, the maximum sum of a subarray ending just before the index i and the maximum sum of a subarray starting just after the index i. By combining these two subarrays with the squared element nums[i] * nums[i] and taking the greatest total sum, we get the answer.

 */