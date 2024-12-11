package dailychallenge.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecialArrayII3152 {
    public static void main(String[] args) {
        int[][] queries = {{0,4}};
        int[][] queries1 = {{0,1}};
        int[][] queries2 = {{0,2},{2,3}};
        int[] nums = {3,4,1,2,6};
        int[] nums1 = {1,1};
        int[] nums2 = {4,3,1,6};
        System.out.println(Arrays.toString(isArraySpecial(nums2,queries2)));
    }


//    prefix sum; time: O(m + n), space: O(n)
    public static boolean[] isArraySpecial(int[] nums, int[][] queries) {
        boolean[] res = new boolean[queries.length];
        int[] prefixSum = new int[nums.length];
        for(int i = 1 ; i < nums.length ; i++) {
            if(nums[i] % 2 == nums[i - 1] % 2) // new violating index found
                prefixSum[i] = prefixSum[i - 1] + 1;
            else
                prefixSum[i] = prefixSum[i - 1];
        }
        int idx = 0;
        for(int[] query : queries) {
            int start = query[0], end = query[1];
            res[idx++] = prefixSum[end] - prefixSum[start] == 0;
        }
        return res;
    }

//    sliding window; time: O(m + n), space: O(n)
    public static boolean[] isArraySpecial1(int[] nums, int[][] queries) {
        boolean[] res = new boolean[queries.length];
        int[] maxReachable = new int[nums.length];
        int end = 0, n = nums.length;
//        compute the maximum reachable index for each starting index
        for(int start = 0 ; start < n ; start++) {
//            ensure end always starts from current index or beyond
            end = Math.max(start, end);
//            expand end as long as adjacent elements have different parity
            while(end < n - 1 && nums[end] % 2 != nums[end + 1] % 2) {
                end++;
            }
//            store the farthest reachable index from start
            maxReachable[start] = end;
        }
        int idx = 0;
        for(int[] query : queries) {
            int start = query[0], endQuery = query[1];
            res[idx++] = endQuery <= maxReachable[start];
        }
        return res;
    }

//    binary search; time: O(n + mlogn), space: O(n) [n - nums, m - queries]
    public static boolean[] isArraySpecial2(int[] nums, int[][] queries) {
        List<Integer> violatingIndices = new ArrayList<>();
        boolean[] res = new boolean[queries.length];
        for(int i = 1 ; i < nums.length ; i++) {
            if (nums[i - 1] % 2 == nums[i] % 2)
                violatingIndices.add(i);
        }
        int index = 0;
        for(int[] query : queries) {
            int start = query[0];
            int end = query[1];
            boolean foundViolatingIndex = binarySearch(start + 1, end, violatingIndices);
            if(!foundViolatingIndex)
                res[index] = true;
            index++;
        }
        return res;
    }

    private static boolean binarySearch(int start, int end, List<Integer> violatingIndices) {
        int left = 0, right = violatingIndices.size() - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int violatingIndex = violatingIndices.get(mid);
//            check right and left half
            if(violatingIndex < start)
                left = mid + 1;
            else if(violatingIndex > end)
                right = mid - 1;
            else // violating index falls in between start and end
                return true;
        }
        return false;
    }


//    brute force; TLE!!
    public static boolean[] isArraySpecialX(int[] nums, int[][] queries) {
        boolean[] res = new boolean[queries.length];
        int ind = 0;
        Arrays.fill(res, true);
        for(int[] query : queries) {
            int i = query[0], j = query[1];
            while(i < j) {
                if((nums[i] % 2 == 0 && nums[i + 1] % 2 == 0)
                || (nums[i + 1] % 2 != 0 && nums[i] % 2 != 0)) {
                    res[ind] = false;
                    break;
                }
                i++;
            }
            ind++;
        }
        return res;
    }
}

/*
An array is considered special if every pair of its adjacent elements contains two numbers with different parity.
You are given an array of integer nums and a 2D integer matrix queries, where for queries[i] = [fromi, toi] your task is to check that
subarray nums[fromi..toi] is special or not.
Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.

Example 1:
Input: nums = [3,4,1,2,6], queries = [[0,4]]
Output: [false]
Explanation:
The subarray is [3,4,1,2,6]. 2 and 6 are both even.
Example 2:
Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]
Output: [false,true]
Explanation:
The subarray is [4,3,1]. 3 and 1 are both odd. So the answer to this query is false.
The subarray is [1,6]. There is only one pair: (1,6) and it contains numbers with different parity. So the answer to this query is true.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 105
1 <= queries.length <= 105
queries[i].length == 2
0 <= queries[i][0] <= queries[i][1] <= nums.length - 1
 */

/*
binary search:
A brute force solution would involve traversing the entire subarray for each query queries[i] and checking if its elements alternate between even and odd parity. However, this approach is inefficient because traversing all subarrays will be very time-consuming, especially if there are many queries or if the subarrays are large. Also, there would be much repeated work if the queries overlap.
Instead, we can perform some precomputations to solve each query faster. If we perform an initial traversal of nums, we can easily identify the indices of elements that break or violate the special array property. Specifically, we can find the indices of elements nums[i] that have the same parity (even or odd) as its previous element: If nums[i] % 2 == nums[i-1] % 2 is true, then nums[i] is a violating element.
If a subarray contains no violating indices, then it is a special array.
Since we can perform our initial traversal of nums from left to right, the violating indices are naturally sorted in ascending order. Because they are sorted, we can perform binary search on the violating indices to see if any violating indices fall between the range [start + 1, end]. Note that we start our search at start + 1 instead of start because the violating indices are defined relative to the element to their left. Therefore, the first element of our subarray (at index start) is never a violating element, and our search should begin at start + 1.
It is also worth noting that there is usually a single target value we would like to find for traditional binary search problems. However, for this problem, we have a target range of [start + 1, end] instead.
Thus, our precomputation allows us to more efficiently evaluate each subarray, leading to an O(logn) binary search time for each query rather than a O(n) brute force traversal.
 */