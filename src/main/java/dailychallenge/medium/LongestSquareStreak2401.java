package dailychallenge.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestSquareStreak2401 {
    public static void main(String[] args) {
        int[] nums = {4,3,6,16,8,2};
        System.out.println(longestSquareStreak(nums));
    }

//    sorting; time: O(nlogn), space: O(n)
    public static int longestSquareStreak(int[] nums) {
//        map to store the length of the square streak for each number
        Map<Integer, Integer> streakLengths = new HashMap<>();
        Arrays.sort(nums);
        for(int num : nums) {
            int root = (int)Math.sqrt(num);
//            check if the number is a perfect square and its root is in the map
            if(root * root == num && streakLengths.containsKey(root)) {
//                if so, extend the streak from its square root
                streakLengths.put(num, streakLengths.get(root) + 1);
            } else {
//                otherwise, start a new streak
                streakLengths.put(num, 1);
            }
        }
//        find the maximum streak length
        int longestStreak = 0;
        for(int streakLength : streakLengths.values()) {
            longestStreak = Math.max(longestStreak, streakLength);
        }

        return longestStreak == 1 ? -1 : longestStreak;
    }
}

/*
You are given an integer array nums. A subsequence of nums is called a square streak if:
The length of the subsequence is at least 2, and
after sorting the subsequence, each element (except the first element) is the square of the previous number.
Return the length of the longest square streak in nums, or return -1 if there is no square streak.
A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

Example 1:
Input: nums = [4,3,6,16,8,2]
Output: 3
Explanation: Choose the subsequence [4,16,2]. After sorting it, it becomes [2,4,16].
- 4 = 2 * 2.
- 16 = 4 * 4.
Therefore, [4,16,2] is a square streak.
It can be shown that every subsequence of length 4 is not a square streak.
Example 2:
Input: nums = [2,3,5,6,7]
Output: -1
Explanation: There is no square streak in nums so return -1.

Constraints:
2 <= nums.length <= 105
2 <= nums[i] <= 105
 */