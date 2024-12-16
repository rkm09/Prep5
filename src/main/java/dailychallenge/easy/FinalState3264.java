package dailychallenge.easy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FinalState3264 {
    public static void main(String[] args) {
        int[] nums = {2,1,3,5,6};
        System.out.println(Arrays.toString(getFinalState(nums, 5, 2)));
    }

//    def; min heap;
    public static int[] getFinalState(int[] nums, int k, int multiplier) {
        int n = nums.length;
        int[] res = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        for(int i = 0 ; i < n ; i++) {
            pq.offer(new int[]{nums[i],i});
        }
        while(k-- > 0 && !pq.isEmpty()) {
            int[] info = pq.poll();
            pq.offer(new int[]{info[0] * multiplier, info[1]});
        }
        while(!pq.isEmpty()) {
            int[] info = pq.poll();
            res[info[1]] = info[0];
        }
        return res;
    }
}

/*
You are given an integer array nums, an integer k, and an integer multiplier.
You need to perform k operations on nums. In each operation:
Find the minimum value x in nums. If there are multiple occurrences of the minimum value, select the one that appears first.
Replace the selected minimum value x with x * multiplier.
Return an integer array denoting the final state of nums after performing all k operations.
Example 1:
Input: nums = [2,1,3,5,6], k = 5, multiplier = 2
Output: [8,4,6,5,6]
Explanation:
Operation	Result
After operation 1	[2, 2, 3, 5, 6]
After operation 2	[4, 2, 3, 5, 6]
After operation 3	[4, 4, 3, 5, 6]
After operation 4	[4, 4, 6, 5, 6]
After operation 5	[8, 4, 6, 5, 6]
Example 2:
Input: nums = [1,2], k = 3, multiplier = 4
Output: [16,8]
Explanation:
Operation	Result
After operation 1	[4, 2]
After operation 2	[4, 8]
After operation 3	[16, 8]

Constraints:
1 <= nums.length <= 100
1 <= nums[i] <= 100
1 <= k <= 10
1 <= multiplier <= 5
 */