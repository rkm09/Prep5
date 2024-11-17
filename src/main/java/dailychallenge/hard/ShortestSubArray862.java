package dailychallenge.hard;

import common.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestSubArray862 {
    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(shortestSubarray(nums, 1));
    }

//    priority queue; time: O(nlogn), space: O(n)
    public static int shortestSubarray(int[] nums, int k) {
        PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<>(Comparator.comparingLong(Pair::getKey));
        long cumulativeSum = 0;
        int shortestSubLength = Integer.MAX_VALUE;
        for(int i = 0 ; i < nums.length ; i++) {
            cumulativeSum += nums[i];
            if(cumulativeSum >= k)
                shortestSubLength = Math.min(shortestSubLength, i + 1);
            while(!pq.isEmpty() && cumulativeSum - pq.peek().getKey() >= k) {
                shortestSubLength = Math.min(shortestSubLength, i - pq.poll().getValue());
            }
            pq.offer(new Pair<>(cumulativeSum, i));
        }
        return shortestSubLength == Integer.MAX_VALUE ? -1 : shortestSubLength;
    }
}

/*
Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.
A subarray is a contiguous part of an array.

Example 1:
Input: nums = [1], k = 1
Output: 1
Example 2:
Input: nums = [1,2], k = 4
Output: -1
Example 3:
Input: nums = [2,-1,2], k = 3
Output: 3

Constraints:
1 <= nums.length <= 105
-105 <= nums[i] <= 105
1 <= k <= 109
 */

/*
This problem is very similar to 209. Minimum Size Subarray Sum with one key difference: we have negative values here. We strongly suggest solving the original problem first, as our solution will build upon that approach.

The original problem was solved using a variable-length sliding window, but that approach will no longer work here. Let's take an example to understand why:

Consider nums = [2, -1, 1, 3] and k = 4.

Let's walk through a naive variable-size sliding window approach step by step:

Start with window [2]: sum = 2 (not ≥ 4)
Expand to [2, -1]: sum = 1 (not ≥ 4)
Expand to [2, -1, 1]: sum = 2 (not ≥ 4)
Expand to [2, -1, 1, 3]: sum = 5 (≥ 4)
Now, we try to minimize the window size by shrinking the window from the left.

Remove the first element. Window: [-1, 1, 3]: sum = 3 (not ≥ 4)
The sliding window now stops because it assumes reducing the window further would only decrease the sum value. However, if it shrinks once more:

Remove the second element. Window: [1, 3]: sum = 4 (≥ 4)
We find that our condition is satisfied again, and we find our required answer.

The negative value -1 breaks the monotonic sum property that a standard sliding window relies on, making a simple variable-length sliding window approach unreliable.
 */