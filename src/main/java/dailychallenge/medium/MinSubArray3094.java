package dailychallenge.medium;

public class MinSubArray3094 {
    public static void main(String[] args) {
        MinSubArray3094 m = new MinSubArray3094();
        int[] nums = {1,2,3};
        System.out.println(m.minimumSubarrayLength(nums, 2));
    }

//    sliding window; time: O(n), space: O(1)
    public int minimumSubarrayLength(int[] nums, int k) {
        int minLength = Integer.MAX_VALUE;
        int windowStart = 0, windowEnd = 0, n = nums.length;
//        tracks the count of set bits at each position
        int[] bitCounts = new int[32];
//        expand window until end of array
        while(windowEnd < n) {
//            add current number to window
            updateBitCounts(bitCounts, nums[windowEnd], 1);
//            contract window while OR value is still valid
            while(windowStart <= windowEnd &&
            convertBitCountsToNumber(bitCounts) >= k) {
//                update the minLength found so far
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
//                remove the leftmost number and shrink the window
                updateBitCounts(bitCounts, nums[windowStart], -1);
                windowStart++;
            }
            windowEnd++;
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

//    update bit counts array when adding/removing a number
    private void updateBitCounts(int[] bitCounts, int number, int delta) {
        for(int bitPosition = 0 ; bitPosition < 32 ; bitPosition++) {
            if(((number >> bitPosition) & 1) != 0) {
                bitCounts[bitPosition] += delta;
            }
        }
    }

//    convert bit counts array back to number using OR
    private int convertBitCountsToNumber(int[] bitCounts) {
        int result = 0;
        for(int bitPosition = 0 ; bitPosition < 32 ; bitPosition++) {
            if(bitCounts[bitPosition] != 0)
                result |= 1 << bitPosition;
        }
        return result;
    }

}

/*
You are given an array nums of non-negative integers and an integer k.
An array is called special if the bitwise OR of all of its elements is at least k.
Return the length of the shortest special non-empty
subarray of nums, or return -1 if no special subarray exists.
Example 1:
Input: nums = [1,2,3], k = 2
Output: 1
Explanation:
The subarray [3] has OR value of 3. Hence, we return 1.
Example 2:
Input: nums = [2,1,8], k = 10
Output: 3
Explanation:
The subarray [2,1,8] has OR value of 11. Hence, we return 3.
Example 3:
Input: nums = [1,2], k = 0
Output: 1
Explanation:
The subarray [1] has OR value of 1. Hence, we return 1.

Constraints:
1 <= nums.length <= 2 * 105
0 <= nums[i] <= 109
0 <= k <= 109
 */

/*
Binary Search:
The OR operation has a unique property: the result is always greater than or equal to its operands. When we perform the OR operation on a series of numbers, each intermediate result will be greater than or equal to all previous results.
This property indicates that the highest OR values of subarray lengths, when arranged from 1 to n, form a non-decreasing sequence. This insight lets us use binary search in our solution.
Sliding window:
In our previous method, we used binary search to adjust the size of the window to find the smallest possible window size. However, we can simplify things by using a variable-size sliding window instead, which eliminates the logn factor from our time complexity.
We’ll iterate through the nums array and add each element to our window one by one. After adding an element, we’ll check if the current OR value of the subarray meets or exceeds the target value k. If it does, we’ll keep track of the current size of the window in a variable called minLength.
Next, we’ll try to shrink the window from the start by removing elements one at a time. Each time we remove an element, we reduce the window size and update minLength accordingly. We keep doing this until the OR value of the window drops below k, at which point we stop removing elements and continue with the next element in the array.
 */