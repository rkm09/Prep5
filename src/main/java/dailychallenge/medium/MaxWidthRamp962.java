package dailychallenge.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxWidthRamp962 {
    public static void main(String[] args) {
        int[] nums = {6,0,8,2,1,5};
        System.out.println(maxWidthRamp(nums));
    }

//    monotonic stack; time: O(n), space: O(n)
    public static int maxWidthRamp(int[] nums) {
        int n = nums.length;
        Deque<Integer> indicesStack = new ArrayDeque<>();
        for(int i = 0 ; i < n ; i++) {
            if(indicesStack.isEmpty() || nums[indicesStack.peek()] > nums[i])
                indicesStack.push(i);
        }
        int maxWidth = 0;
        for(int j = n - 1; j >= 0; j--) {
            while(!indicesStack.isEmpty() && nums[indicesStack.peek()] <= nums[j]) {
                maxWidth = Math.max(maxWidth, j - indicesStack.pop());
            }
        }
        return maxWidth;
    }
}

/*
A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]. The width of such a ramp is j - i.
Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.

Example 1:
Input: nums = [6,0,8,2,1,5]
Output: 4
Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.
Example 2:
Input: nums = [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.

Constraints:
2 <= nums.length <= 5 * 104
0 <= nums[i] <= 5 * 104
 */
