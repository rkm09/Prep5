package dailychallenge.hard;

import java.util.*;

public class SmallestRange632 {
    public static void main(String[] args) {
        List<Integer> li1 = List.of(4,10,15,24,26);
        List<Integer> li2 = List.of(0,9,12,20);
        List<Integer> li3 = List.of(5,18,22,30);
        List<List<Integer>> nums =  List.of(li1,li2,li3);
        System.out.println(Arrays.toString(smallestRange(nums)));
    }

//    two pointer; time: O(nlogn), space: O(n)
    public static int[] smallestRange(List<List<Integer>> nums) {
        List<int[]> merged = new ArrayList<>();
//        merge all lists along with their list index
        for(int i = 0 ; i < nums.size() ; i++) {
            for(int num : nums.get(i)) {
                merged.add(new int[]{num, i});
            }
        }
//        sort the merged list
        merged.sort(Comparator.comparingInt(a -> a[0]));
//        two pointers to track the smallest range
        Map<Integer, Integer> freq = new HashMap<>();
//        count keeps track of the number of lists being referred to
        int left = 0, count = 0;
        int rangeStart = 0, rangeEnd = Integer.MAX_VALUE;
        int leftIndex, rightIndex;
        int leftValue, rightValue;

        for(int right = 0 ; right < merged.size() ; right++) {
            rightIndex = merged.get(right)[1];
            rightValue = merged.get(right)[0];

            freq.put(rightIndex,
                    freq.getOrDefault(rightIndex, 0) + 1);
            if(freq.get(rightIndex) == 1) count++;
//            if all lists have been represented, then try to shrink the window as much as possible
            while(count == nums.size()) {
                leftIndex = merged.get(left)[1];
                leftValue = merged.get(left)[0];

                int currRange = rightValue - leftValue;
                if(currRange < rangeEnd - rangeStart) {
                    rangeStart = leftValue;
                    rangeEnd = rightValue;
                }
                freq.put(leftIndex,
                        freq.get(leftIndex) - 1);
                if(freq.get(leftIndex) == 0) count--;
                left++;
            }
        }

        return new int[]{rangeStart, rangeEnd};
     }
}

/*
You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.
We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.

Example 1:
Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
Output: [20,24]
Explanation:
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Example 2:
Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
Output: [1,1]

Constraints:
nums.length == k
1 <= k <= 3500
1 <= nums[i].length <= 50
-105 <= nums[i][j] <= 105
nums[i] is sorted in non-decreasing order.
 */