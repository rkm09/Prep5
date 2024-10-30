package dailychallenge.medium;

import java.util.Arrays;

public class MinMountainRemovals1671 {
    public static void main(String[] args) {
        int[] nums = {1,3,1};
        System.out.println(minimumMountainRemovals(nums));
    }

//    dp; time: O(n^2), space: O(n)
//    if i is peak, L1 be the length of the lis and L2 be the length of lds. then the number of elements to remove
//    will be left[0..i]: (i + 1) - L1, right[i..n-1] : (n - 1 - i + 1) - L2 = n - i - L2
//    total elements to remove = (i + 1 - L1) + (n - i - L2) = (n - L1 - L2 + 1)
    public static int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] lisLength = new int[n];
        int[] ldsLength = new int[n];
        Arrays.fill(lisLength, 1);
        Arrays.fill(ldsLength, 1);
//        store the length of the longest increasing subsequence that ends in i
        for(int i = 0 ; i < n ; i++) {
            for(int j = i - 1 ; j >= 0 ; j--) {
                if(nums[i] > nums[j])
                    lisLength[i] = Math.max(lisLength[i], lisLength[j] + 1);
            }
        }
//        store the length of the longest decreasing subsequence that ends in i
        for(int i = n - 1 ; i >= 0 ; i--) {
            for(int j = i + 1 ; j < n ; j++) {
                if(nums[i] > nums[j])
                    ldsLength[i] = Math.max(ldsLength[i], ldsLength[j] + 1);
            }
        }

        int minRemovals = Integer.MAX_VALUE;
        for(int i = 0 ; i < n ; i++) {
            if(lisLength[i] > 1 && ldsLength[i] > 1) {
                minRemovals = Math.min(minRemovals, n - lisLength[i] - ldsLength[i] + 1);
            }
        }
        return minRemovals;
    }
}

/*
You may recall that an array arr is a mountain array if and only if:
arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array nums, return the minimum number of elements to remove to make nums a mountain array.

Example 1:
Input: nums = [1,3,1]
Output: 0
Explanation: The array itself is a mountain array so we do not need to remove any elements.
Example 2:
Input: nums = [2,1,1,5,6,2,3,1]
Output: 3
Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].

Constraints:
3 <= nums.length <= 1000
1 <= nums[i] <= 109
It is guaranteed that you can make a mountain array out of nums.

 */