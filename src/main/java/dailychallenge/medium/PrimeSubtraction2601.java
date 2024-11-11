package dailychallenge.medium;

public class PrimeSubtraction2601 {
    public static void main(String[] args) {
        PrimeSubtraction2601 p = new PrimeSubtraction2601();
        int[] nums = {4,9,6,10};
        System.out.println(p.primeSubOperation(nums));
    }

//    greedy(storing primes); time: O(n + m.sqrt(m)), space: O(m) [n- nums length, m - max value in nums array]
//    isPrime calls take m.sqrt(m)
//    we prioritize subtracting the largest possible prime from each element while ensuring each adjusted element is still
//    greater than the one before it. This allows us to minimize each value as much as possible, providing the most flexibility for later adjustments.
//    for each prime number p, we set previousPrime[p] = p. Then, for numbers in between (where no prime has been assigned), we just carry forward the most recent prime we found.
    public boolean primeSubOperation(int[] nums) {
        int maxElement = Integer.MIN_VALUE;
        for(int num : nums)
            maxElement = Math.max(num, maxElement);
//        store the maximal previous prime
        int[] previousPrime = new int[maxElement + 1];
        for(int i = 2 ; i <= maxElement ; i++) {
            if(isPrime(i))
                previousPrime[i] = i;
            else
                previousPrime[i] = previousPrime[i - 1];
        }
        for(int i = 0 ; i < nums.length ; i++) {
            int bound;
            if(i == 0)
                bound = nums[0];
            else
                bound = nums[i] - nums[i - 1];
//            bound not strictly increasing
            if(bound <= 0)
                return false;
//            find the largest prime less than the bound
            int largestPrime = previousPrime[bound - 1];
            nums[i] -= largestPrime;
        }
        return true;
    }

    private boolean isPrime(int n) {
        for(int i = 2 ; i * i <= n ; i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
}

/*
You are given a 0-indexed integer array nums of length n.
You can perform the following operation as many times as you want:
Pick an index i that you haven’t picked before, and pick a prime p strictly less than nums[i], then subtract p from nums[i].
Return true if you can make nums a strictly increasing array using the above operation and false otherwise.
A strictly increasing array is an array whose each element is strictly greater than its preceding element.

Example 1:
Input: nums = [4,9,6,10]
Output: true
Explanation: In the first operation: Pick i = 0 and p = 3, and then subtract 3 from nums[0], so that nums becomes [1,9,6,10].
In the second operation: i = 1, p = 7, subtract 7 from nums[1], so nums becomes equal to [1,2,6,10].
After the second operation, nums is sorted in strictly increasing order, so the answer is true.
Example 2:
Input: nums = [6,8,11,12]
Output: true
Explanation: Initially nums is sorted in strictly increasing order, so we don't need to make any operations.
Example 3:
Input: nums = [5,8,3]
Output: false
Explanation: It can be proven that there is no way to perform operations to make nums sorted in strictly increasing order, so the answer is false.

Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= 1000
nums.length == n
 */


/*
For example, consider nums = [5, 5, 4]. For the first element, we have two options:
Make a minimal adjustment by subtracting a small prime, like 2, from 5, resulting in [3, 5, 4]. While 3 is less than 5, we still need to adjust the second 5 to make it smaller than 4. Subtracting 2 from the second 5 gives [3, 3, 4], which isn’t strictly increasing.
Make a maximal adjustment by subtracting the largest possible prime under 5, which is 3. This results in [2, 5, 4]. Now, for the second element, we again subtract the largest prime that keeps it greater than the previous element, resulting in [2, 3, 4]—a strictly increasing sequence.
 */