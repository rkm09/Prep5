package dailychallenge.medium;

public class ShortestSubArray1574 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,10,4,2,3,5};
        System.out.println(findLengthOfShortestSubarray(arr));
    }

//    two pointer; time: O(n), space: O(1)
    public static int findLengthOfShortestSubarray(int[] arr) {
        int right = arr.length - 1;
        while(right > 0 && arr[right] >= arr[right - 1])
            right--;
        int ans = right;
        int left = 0;
        while(left < right && (left == 0 || arr[left] >= arr[left - 1])) {
            while(right < arr.length && arr[left] > arr[right])
                right++;
            ans = Math.min(ans, right - left - 1);
            left++;
        }
        return ans;
    }
}

/*
Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
Return the length of the shortest subarray to remove.
A subarray is a contiguous subsequence of the array.

Example 1:
Input: arr = [1,2,3,10,4,2,3,5]
Output: 3
Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
Another correct solution is to remove the subarray [3,10,4].
Example 2:
Input: arr = [5,4,3,2,1]
Output: 4
Explanation: Since the array is strictly decreasing, we can only keep a single element. Therefore, we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].
Example 3:
Input: arr = [1,2,3]
Output: 0
Explanation: The array is already non-decreasing. We do not need to remove any elements.

Constraints:
1 <= arr.length <= 105
0 <= arr[i] <= 109
 */

/*
We can think of arr as being composed of 3 parts. The first part is a block of numbers in sorted order (blue region in the
image above), followed by a block of numbers that breaks the sorted order (yellow region), and then finally another block
of numbers in sorted order (green region). We have two edge cases to consider. The first is when the entire array is
already sorted. In this case, no numbers need to be removed, so the subarray to be removed is empty. The second edge case is
when the entire array is in reverse order (sorted in decreasing order), in which we remove everything except the first or last element.
For the nontrivial cases depicted above, we know that the subarray to remove resides somewhere in the middle of the array.
You can imagine that starting from the right side of the array, there is a line dividing the sorted section (right side) from the unsorted section (left side). For example, in [1, 2, 3, 10, 4, 2, 3, 5], if we transform the array into a pattern for understanding purposes,
where 0 represents unsorted portions and 1 represents sorted ones, it looks like this:
[0, 0, 0, 0, 0, 1, 1, 1]
Since the [1, 1, 1] portion is sorted, we can actually perform a binary search on this sorted section. This allows us to
find the earliest point on the right where any sorted left section can merge with it, minimizing the middle subarray that needs to be removed. However, we can optimize this further by replacing binary search (O(nlogn)) with a more efficient two-pointer approach, reducing the complexity to O(n).
A key insight here is that the unsorted yellow region must always be part of the removed subarray since it breaks the sorted order.
 */