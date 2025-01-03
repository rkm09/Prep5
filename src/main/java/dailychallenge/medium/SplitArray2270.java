package dailychallenge.medium;

public class SplitArray2270 {
    public static void main(String[] args) {
        int[] nums = {10,4,-8,7};
        System.out.println(waysToSplitArray(nums));
    }

//    optimised prefix sum; time: O(n), space: O(1)
    public static int waysToSplitArray(int[] nums) {
//        keep track of all elements on the left and right side
        long leftSum = 0, rightSum = 0, n = nums.length;
//        initially all elements are on the right side
        for(int num : nums)
            rightSum += num;
        int count = 0;
//        try each possible split position; note it needs to exclude last position
        for(int i = 0 ; i < n - 1; i++) {
//            move current element from right to left side
            leftSum += nums[i];
            rightSum -= nums[i];
//            check if this creates a valid split
            if(leftSum >= rightSum) count++;
        }
        return count;
    }

//  prefix sum; time: O(n), space: O(n)
    public static int waysToSplitArray1(int[] nums) {
        int n = nums.length;
        long[] prefixSum = new long[n];
        int count = 0;
        prefixSum[0] = nums[0];
        for(int i = 1 ; i < n ; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        for(int i = 0 ; i < n - 1; i++) {
            long leftSum = prefixSum[i];
            long rightSum = prefixSum[n - 1] - prefixSum[i];
            if(leftSum >= rightSum) count++;
        }
        return count;
    }

//    def; time: O(n), space: O(n)
    public static int waysToSplitArray2(int[] nums) {
        int n = nums.length;
        long[] leftSum = new long[n];
        long[] rightSum = new long[n];
        int count = 0;
        leftSum[0] = nums[0];
        rightSum[n - 1] = nums[n - 1];
        for(int i = 1 ; i < n - 1 ; i++) {
            leftSum[i] = leftSum[i - 1] + nums[i];
        }
        for(int i = n - 2 ; i > 0 ; i--) {
            rightSum[i] = rightSum[i + 1] + nums[i];
        }
        for(int i = 0 ; i < n - 1; i++) {
            if(leftSum[i] >= rightSum[i + 1]) count++;
        }
        return count;
    }
}

/*
You are given a 0-indexed integer array nums of length n.
nums contains a valid split at index i if the following are true:
The sum of the first i + 1 elements is greater than or equal to the sum of the last n - i - 1 elements.
There is at least one element to the right of i. That is, 0 <= i < n - 1.
Return the number of valid splits in nums.

Example 1:
Input: nums = [10,4,-8,7]
Output: 2
Explanation:
There are three ways of splitting nums into two non-empty parts:
- Split nums at index 0. Then, the first part is [10], and its sum is 10. The second part is [4,-8,7], and its sum is 3. Since 10 >= 3, i = 0 is a valid split.
- Split nums at index 1. Then, the first part is [10,4], and its sum is 14. The second part is [-8,7], and its sum is -1. Since 14 >= -1, i = 1 is a valid split.
- Split nums at index 2. Then, the first part is [10,4,-8], and its sum is 6. The second part is [7], and its sum is 7. Since 6 < 7, i = 2 is not a valid split.
Thus, the number of valid splits in nums is 2.
Example 2:
Input: nums = [2,3,1,0]
Output: 2
Explanation:
There are two valid splits in nums:
- Split nums at index 1. Then, the first part is [2,3], and its sum is 5. The second part is [1,0], and its sum is 1. Since 5 >= 1, i = 1 is a valid split.
- Split nums at index 2. Then, the first part is [2,3,1], and its sum is 6. The second part is [0], and its sum is 0. Since 6 >= 0, i = 2 is a valid split.

Constraints:
2 <= nums.length <= 105
-105 <= nums[i] <= 105
 */