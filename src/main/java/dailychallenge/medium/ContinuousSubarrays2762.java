package dailychallenge.medium;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class ContinuousSubarrays2762 {
    public static void main(String[] args) {
        int[] nums = {5,4,2,4};
        int[] nums1 = {65,66,67,66,66,65,64,65,65,64};
        System.out.println(continuousSubarrays(nums1));
        //System.out.println(continuousSubarrays1(nums1));
    }

//    sliding window (treemap); time: O(nlogk) ~ O(n), space: O(k) ~ O(1)
    public static long continuousSubarrays(int[] nums) {
//        tree map to maintain sorted frequency map of current window
        TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        int left = 0, right = 0, n = nums.length;
        long count = 0; // total count of valid sub arrays
        while(right < n) {
//            add current element to frequency map
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);
//            while window violates the condition |nums[i] - nums[j]| <= 2
            while(freqMap.lastEntry().getKey() - freqMap.firstEntry().getKey() > 2) {
//                remove leftmost element from frequency map
//                note: not necessarily the smallest element, but just the leftmost element
                freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                if(freqMap.get(nums[left]) == 0)
                    freqMap.remove(nums[left]);
                left++;
            }
//             add count of all valid sub arrays ending at right
            count += right - left + 1;
            right++;
        }
        return count;
    }

//    priority queue; time: O(nlogn), space: O(n)
    public static long continuousSubarrays1(int[] nums) {
//        note: we compare the values, but store indices in the priority queue
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> nums[a]));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> nums[b] - nums[a]);
        int left = 0, right = 0, n = nums.length;
        long count = 0;
        while(right < n) {
            maxHeap.offer(right);
            minHeap.offer(right);
//            while window violates the condition |nums[i] - nums[j]| <= 2, shrink window from left
            while(left < right && nums[maxHeap.peek()] - nums[minHeap.peek()] > 2) {
                left++;
//                remove indices that are now outside the window
                while(!maxHeap.isEmpty() && maxHeap.peek() < left) {
                    maxHeap.poll();
                }
                while(!minHeap.isEmpty() && minHeap.peek() < left) {
                    minHeap.poll();
                }
            }
            count += right - left + 1;
            right++;
        }
        return count;
    }
}

/*
You are given a 0-indexed integer array nums. A subarray of nums is called continuous if:
Let i, i + 1, ..., j be the indices in the subarray. Then, for each pair of indices i <= i1, i2 <= j, 0 <= |nums[i1] - nums[i2]| <= 2.
Return the total number of continuous subarrays.
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [5,4,2,4]
Output: 8
Explanation:
Continuous subarray of size 1: [5], [4], [2], [4].
Continuous subarray of size 2: [5,4], [4,2], [2,4].
Continuous subarray of size 3: [4,2,4].
Thereare no subarrys of size 4.
Total continuous subarrays = 4 + 3 + 1 = 8.
It can be shown that there are no more continuous subarrays.
Example 2:
Input: nums = [1,2,3]
Output: 6
Explanation:
Continuous subarray of size 1: [1], [2], [3].
Continuous subarray of size 2: [1,2], [2,3].
Continuous subarray of size 3: [1,2,3].
Total continuous subarrays = 3 + 2 + 1 = 6.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 109
 */

/*
TreeMap[sliding window]:
The main challenge in this problem is to understand what makes a subarray 'continuous'. A subarray is considered continuous if the difference between any two elements within it is no more than 2. Understanding this simplifies the task and allows us to focus on the largest and smallest values, rather than checking every pair of elements.
Consider the subarray [4, 5, 3] from the array [4, 5, 3, 2, 6]. This subarray is valid because the difference between the largest element (5) and the smallest (3) is 2 or less. We don't need to evaluate any other pairs of elements in the array, since they can't possibly lead to a higher difference.
To solve this problem, we need a mechanism to evaluate all possible subarrays efficiently. A sliding window approach, with a variable-sized window, is well-suited for this purpose. We'll start with an empty window and expand it by adding elements from the array, as long as the difference between the maximum and minimum elements in the window is 2 or less. If this condition is violated, we shrink the window from the left until it becomes valid again.
Complexity Analysis
Let n be the length of the input array nums, k - size of map.
Time complexity: O(nlogk)≈O(n)
The outer loop iterates through the array once with the right pointer, taking O(n) operations. For each element, we perform map operations (insertion, deletion, finding min/max) which take O(logk) time, where k is the size of the map. Since we maintain a window where the max−min≤2, the size of the sorted map k is bounded by 3 (as elements can only differ by 0, 1, or 2). Therefore, logk is effectively constant, making the overall time complexity O(n).
Space complexity: O(k)≈O(1)
The sorted map stores elements within the current window. Since the difference between any two elements in a valid window cannot exceed 2, the maximum number of unique elements (k) possible in the map at any time is 3. Therefore, the space complexity is constant, O(1).

Approach 2: Priority Queue
The main focus of our previous approach was to efficiently find the maximum and minimum values within a given window. Another data structure that excels at this task is a heap, or a priority queue.
Since a heap can only remove either the maximum or the minimum value, not both, we'll need two heaps: a max-heap and a min-heap. We'll store the indices of the elements in the array nums, and the heaps will be organized based on the corresponding values in the array. The basic idea remains the same: we expand the window and add the new element to both heaps. This process continues as long as the difference between the maximum element (at the top of the max-heap) and the minimum element (at the top of the min-heap) is no greater than 2.
If the condition is violated, we need to move the start of the window forward until the condition is satisfied again. For each step we move the left pointer, we must clean up our heaps to discard any elements that are before the start of the window (this is where storing the indices becomes useful).
Just like with our previous solution, once we have a valid window, counting the number of valid subarrays ending at the current right pointer is straightforward: it's simply right - left + 1. Each valid window contributes this many continuous subarrays to our final answer.
 */