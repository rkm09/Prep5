package dailychallenge.medium;

public class MinimizeXOR2429 {
    public static void main(String[] args) {
        MinimizeXOR2429 m = new MinimizeXOR2429();
        System.out.println(m.minimizeXor(3,5));
    }

//    optimal to valid; time: O(logn), space: O(1)
    public int minimizeXor(int num1, int num2) {
//        initialize result to num1, we will modify the result
        int result = num1;

        int targetSetBitsCount = Integer.bitCount(num2);
        int setBitsCount = Integer.bitCount(result);

//        start with the least significant bit
        int currentBit = 0;

//        add bits to the result if it has fewer set bits than target
        while(setBitsCount < targetSetBitsCount) {
//            if the current bit in result is not set, then set it
            if(!isSet(result, currentBit)) {
                result = setBits(result, currentBit);
                setBitsCount++;
            }
//            move to the next bit
            currentBit++;
        }

//        remove bits from the result if it has more set bits than the target
        while(setBitsCount > targetSetBitsCount) {
//            if the current bit in result is set to 1, then unset it to 0
            if(isSet(result, currentBit)) {
                result = unsetBits(result, currentBit);
                setBitsCount--;
            }
            currentBit++;
        }

        return result;
    }

//    helper function to check if the given bit position in result is set to 1
    private boolean isSet(int x, int bit) {
        return (x & (1 << bit)) != 0;
    }

//    helper function to set the given bit position in result to 1
    private int setBits(int x, int bit) {
        return x | (1 << bit);
    }

//    helper function to unset the given bit position in x (set it to 0)
    private int unsetBits(int x, int bit) {
        return x & ~(1 << bit);
    }
}

/*
Given two positive integers num1 and num2, find the positive integer x such that:
x has the same number of set bits as num2, and
The value x XOR num1 is minimal.
Note that XOR is the bitwise XOR operation.
Return the integer x. The test cases are generated such that x is uniquely determined.
The number of set bits of an integer is the number of 1's in its binary representation.
Example 1:
Input: num1 = 3, num2 = 5
Output: 3
Explanation:
The binary representations of num1 and num2 are 0011 and 0101, respectively.
The integer 3 has the same number of set bits as num2, and the value 3 XOR 3 = 0 is minimal.
Example 2:
Input: num1 = 1, num2 = 12
Output: 3
Explanation:
The binary representations of num1 and num2 are 0001 and 1100, respectively.
The integer 3 has the same number of set bits as num2, and the value 3 XOR 1 = 2 is minimal.

Constraints:
1 <= num1, num2 <= 109
 */

/*
A simple observation is that if there were no constraints, the best choice for result would be num1 itself because: num1 XOR num1 = 0 (all bits match, resulting in no differences).
However, result must also have the same number of set bits as num2, so we cannot always use num1 as is. To make result valid, we need to adjust it to match the number of set bits in num2 while trying to keep it as close to num1 as possible. A good way to adjust result is as follows:
Start with result = num1.
Compare the number of set bits in result and num2:
If result has more set bits than num2, we remove extra 1s from result by unsetting bits, starting from the least significant bits (because they are less important for matching num1 closely).
If result has fewer set bits than num2, we add more 1s to result by setting bits, starting from the least significant unset bits.
Let n be the maximum possible value of num1 or num2.
Time Complexity: O(logn).
The time complexity of the given solution is O(logn). This is because the algorithm primarily involves two operations: counting the number of set bits in the integers and adjusting the set bits of result to match the target count. The counting of set bits requires iterating over the binary representation of the integer, which takes O(logn) time since the number of bits in an integer is proportional to logn.
Additionally, the while loops iterate over the bits of result, setting or unsetting bits as needed, which also takes logn time in the worst case, as we may need to process up to all 32 bits. The helper functions (isSet, setBit, and unsetBit) involve constant-time bitwise operations and do not impact the overall complexity.
As a result, the time complexity is dominated by the bit manipulation and bit counting operations, both of which are O(logn).
Space Complexity: O(1).
We only use a fixed number of variables and therefore the algorithm requires constant extra space.
 */