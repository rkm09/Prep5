package prep;

public class MaxConsecutiveOnes481 {
    public static void main(String[] args) {
        int[] nums = {1,0,1,1};
        System.out.println(findMaxConsecutiveOnes(nums));
    }

//    sliding window; time: O(n), space: O(1)
    public static int findMaxConsecutiveOnes(int[] nums) {
        int longestSequence = 0;
        int left = 0, right = 0, numOfZeroes = 0;
//        while our window is in bounds
        while(right < nums.length) {
//            increase the number of zeroes if the rightmost element is zero
            if(nums[right] == 0)
                numOfZeroes++;
//            if the number of zeroes are invalid, contract the window
            while(numOfZeroes > 1) {
                if(nums[left] == 0)
                    numOfZeroes--;
                left++;
            }
//            update our longest sequence answer
            longestSequence = Math.max(longestSequence, right - left + 1);
            right++;
        }
        return longestSequence;
    }

//    brute force; time: O(n^2), space: O(1)
    public static int findMaxConsecutiveOnes1(int[] nums) {
        int longestSequence = 0, n = nums.length;
        for(int left = 0 ; left < n ; left++) {
            int numOfZeroes = 0;
//            check every consecutive sequence
            for(int right = left ; right < n ; right++) {
//                count number of zeroes
                if(nums[right] == 0)
                    numOfZeroes++;
                if(numOfZeroes > 1) break;
//                update result if valid
                longestSequence = Math.max(longestSequence, right - left + 1);
            }
        }
        return longestSequence;
    }
}

/*
Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.
Example 1:
Input: nums = [1,0,1,1,0]
Output: 4
Explanation:
- If we flip the first zero, nums becomes [1,1,1,1,0] and we have 4 consecutive ones.
- If we flip the second zero, nums becomes [1,0,1,1,1] and we have 3 consecutive ones.
The max number of consecutive ones is 4.
Example 2:
Input: nums = [1,0,1,1,0,1]
Output: 4
Explanation:
- If we flip the first zero, nums becomes [1,1,1,1,0,1] and we have 4 consecutive ones.
- If we flip the second zero, nums becomes [1,0,1,1,1,1] and we have 4 consecutive ones.
The max number of consecutive ones is 4.

Constraints:
1 <= nums.length <= 105
nums[i] is either 0 or 1.

Follow up: What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
 */


/*
If our sequence is valid, let's continue expanding our sequence (because our goal is to get the largest sequence possible).
If our sequence is invalid, let's stop expanding and contract our sequence (because an invalid sequence will never count towards our largest sequence).
The pattern that comes to mind for expanding/contracting sequences is the sliding window. Let's define valid and invalid states.

Valid State = one or fewer 0's in our current sequence
Invalid State = two 0's in our current sequence
 */