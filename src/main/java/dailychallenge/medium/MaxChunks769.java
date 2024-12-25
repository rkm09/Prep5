package dailychallenge.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxChunks769 {
    public static void main(String[] args) {
        int[] arr = {4,3,2,1,0};
        System.out.println(maxChunksToSorted(arr));
    }

//    A key observation here is that a split is valid if and only if each segment contains numbers strictly greater than those in the previous segment.
//    In other words, the minimum value of each segment must be greater than the maximum value of the previous segment.

//    maximum element; time: O(n), space: O(1)
    public static int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int maxElement = 0, chunks = 0;
        for(int i = 0 ; i < n ; i++) {
//            update the max element
            maxElement = Math.max(maxElement, arr[i]);
//            all values in the range [0,i] belong to prefix arr[0:i], a new chunk can be formed
            if(maxElement == i)
                chunks++;
        }
        return chunks;
    }

//    prefix sums; time: O(n), space: O(1)
    public static int maxChunksToSorted1(int[] arr) {
        int n = arr.length;
        int prefixSum = 0, sortedPrefixSum = 0;
        int chunks = 0;
        for(int i = 0 ; i < n ; i++) {
//            update prefix sum of array
            prefixSum += arr[i];
//            update prefix sum of sorted array
            sortedPrefixSum += i;
//            if the two sums are equal, the two arrays contain the same elements; a chunk can be formed
            if(sortedPrefixSum == prefixSum)
                chunks++;
        }
        return chunks;
    }

//    monotonic stack; time: O(n), space: O(n)
    public static int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        Deque<Integer> monotonicStack = new ArrayDeque<>();
        for(int i = 0 ; i < n ; i++) {
//            case 1: current element is larger, start a new chunk
            if(monotonicStack.isEmpty() || arr[i] > monotonicStack.peek()) {
                monotonicStack.push(arr[i]);
            } else {
//                case 2: current element is smaller, merge chunks
                int maxElement = monotonicStack.pop();
                while(!monotonicStack.isEmpty() && arr[i] < monotonicStack.peek()) {
                    monotonicStack.pop();
                }
                monotonicStack.push(maxElement);
            }
        }
        return monotonicStack.size();
    }
}

/*
You are given an integer array arr of length n that represents a permutation of the integers in the range [0, n - 1].
We split arr into some number of chunks (i.e., partitions), and individually sort each chunk. After concatenating them, the result should equal the sorted array.
Return the largest number of chunks we can make to sort the array.
Example 1:
Input: arr = [4,3,2,1,0]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
Example 2:
Input: arr = [1,0,2,3,4]
Output: 4
Explanation:
We can split into two chunks, such as [1, 0], [2, 3, 4].
However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.

Constraints:
n == arr.length
1 <= n <= 10
0 <= arr[i] < n
All the elements of arr are unique.
 */

/*
Monotonic stack:
The main idea of this approach is that if a number in the array is less than any number in the previous chunks, this number cannot create a new chunk, as we cannot swap elements from different chunks to fix their relative order.
We will iterate over the array and maintain a stack to represent the maximum values of the chunks created so far.
 */