package dailychallenge.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FindScore2593 {
    public static void main(String[] args) {
        int[] nums = {2,1,3,4,5,2};
        int[] nums1 = {2,5,6,6,10}; //18
        System.out.println(findScore1(nums1));
    }

//    sorting; time: O(nlogn), space: O(n)
    public static long findScore(int[] nums) {
        int n = nums.length;
        int[][] sorted = new int[n][2];
        boolean[] marked = new boolean[n];
        long score = 0;
        for(int i = 0 ; i < n ; i++) {
            sorted[i][0] = nums[i];
            sorted[i][1] = i;
        }
        Arrays.sort(sorted, Comparator.comparingInt(a -> a[0]));
        for(int i = 0 ; i < n ; i++) {
            int number = sorted[i][0];
            int index = sorted[i][1];
            if(!marked[index]) {
                score += number;
                marked[index] = true;
//                mark adjacent elements if they exist
                if(index + 1 < n)
                    marked[index + 1] = true;
                if(index - 1 >= 0)
                    marked[index - 1] = true;
            }
        }
        return score;
    }

//    min heap; time: O(nlogn), space: O(n)
    public static long findScore1(int[] nums) {
        int n = nums.length;
//        note that unlike the previous one, pq may shuffle again after each poll, hence important to sort based on 2 conditions
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            else return a[1] - b[1];
        });
        boolean[] marked = new boolean[n];
        for(int i = 0 ; i < n ; i++) {
            pq.offer(new int[]{nums[i],i});
        }
        long score = 0;
        while(!pq.isEmpty()) {
            int[] numberInfo = pq.poll();
            int number = numberInfo[0];
            int index = numberInfo[1];
            if(!marked[index]) {
                score += number;
                marked[index] = true;
                if(index - 1 >= 0)
                    marked[index - 1] = true;
                if(index + 1 < n)
                    marked[index + 1] = true;
            }
        }
        return score;
    }
}

/*
You are given an array nums consisting of positive integers.
Starting with score = 0, apply the following algorithm:
Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
Add the value of the chosen integer to score.
Mark the chosen element and its two adjacent elements if they exist.
Repeat until all the array elements are marked.
Return the score you get after applying the above algorithm.

Example 1:
Input: nums = [2,1,3,4,5,2]
Output: 7
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
- 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
- 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
Our score is 1 + 2 + 4 = 7.
Example 2:
Input: nums = [2,3,5,1,3,2]
Output: 5
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
- 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
- 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
Our score is 1 + 2 + 2 = 5.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 106
 */
