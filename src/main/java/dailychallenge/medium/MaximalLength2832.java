package dailychallenge.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximalLength2832 {
    public static void main(String[] args) {

    }

//    monotonic stack; time: O(n), space: O(n)
    public static int[] maximumLengthOfRanges(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0 ; i < n ; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i])
                stack.pop();
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for(int i = n - 1 ; i >= 0 ; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i])
                stack.pop();
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        int[] ans = new int[n];
        for(int i = 0 ; i < n ; i++) {
            ans[i] = right[i] - left[i] - 1;
        }
        return ans;
    }
}

/*
You are given a 0-indexed array nums of distinct integers.
Let us define a 0-indexed array ans of the same length as nums in the following way:
ans[i] is the maximum length of a subarray nums[l..r], such that the maximum element in that subarray is equal to nums[i].
Return the array ans.
Note that a subarray is a contiguous part of the array.

Example 1:
Input: nums = [1,5,4,3,6]
Output: [1,4,2,1,5]
Explanation: For nums[0] the longest subarray in which 1 is the maximum is nums[0..0] so ans[0] = 1.
For nums[1] the longest subarray in which 5 is the maximum is nums[0..3] so ans[1] = 4.
For nums[2] the longest subarray in which 4 is the maximum is nums[2..3] so ans[2] = 2.
For nums[3] the longest subarray in which 3 is the maximum is nums[3..3] so ans[3] = 1.
For nums[4] the longest subarray in which 6 is the maximum is nums[0..4] so ans[4] = 5.
Example 2:
Input: nums = [1,2,3,4,5]
Output: [1,2,3,4,5]
Explanation: For nums[i] the longest subarray in which it's the maximum is nums[0..i] so ans[i] = i + 1.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 105
All elements in nums are distinct.
 */

/*
Since nums contains distinct elements, it is guaranteed that each element will be the maximum in at least one subarray.
What’s the smallest possible size of a subarray where the current element is the maximum? The answer is 1, because the element itself is a subarray of size 1. Since we want to find the longest subarray that contains the current element, we can start adding elements from the left or the right side of the current element. Now, let's consider the elements to the left of the current element:

If these elements are smaller than the current element, they can be included in the subarray.
However, as soon as we encounter an element larger than the current one, we must stop, as the current element can no longer be the maximum in the subarray.
The same logic applies to the elements on the right side.
 */