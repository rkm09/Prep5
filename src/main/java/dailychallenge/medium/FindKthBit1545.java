package dailychallenge.medium;

public class FindKthBit1545 {
    public static void main(String[] args) {
        System.out.println(findKthBit(3,1));
    }

//    brute force; time: O(2^n), space: O(2^n)
    public static char findKthBit(int n, int k) {
        StringBuilder sequence = new StringBuilder("0");
//        generate sequence until we have enough elements or reach the nth iteration
        for(int i = 0 ; i < n && k > sequence.length() ; i++) {
            sequence.append("1");
//            append the inverted and reversed part of the "existing" sequence
            for(int j = sequence.length() - 2 ; j >= 0 ; j--) {
                char invertedBit = sequence.charAt(j) == '1' ? '0' : '1';
                sequence.append(invertedBit);
            }
        }
//        return the kth bit
        return sequence.charAt(k - 1);
    }
}

/*
Given two positive integers n and k, the binary string Sn is formed as follows:
S1 = "0"
Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i > 1
Where + denotes the concatenation operation, reverse(x) returns the reversed string x, and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).
For example, the first four strings in the above sequence are:
S1 = "0"
S2 = "011"
S3 = "0111001"
S4 = "011100110110001"
Return the kth bit in Sn. It is guaranteed that k is valid for the given n.

Example 1:
Input: n = 3, k = 1
Output: "0"
Explanation: S3 is "0111001".
The 1st bit is "0".
Example 2:
Input: n = 4, k = 11
Output: "1"
Explanation: S4 is "011100110110001".
The 11th bit is "1".

Constraints:
1 <= n <= 20
1 <= k <= 2n - 1
 */