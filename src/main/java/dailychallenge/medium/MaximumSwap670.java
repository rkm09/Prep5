package dailychallenge.medium;

public class MaximumSwap670 {
    public static void main(String[] args) {
        System.out.println(maximumSwap(2736));
    }

//    brute force; time: O(n^2), space: O(n)
    public static int maximumSwap(int num) {
        String numStr = String.valueOf(num);
        int n = numStr.length();
        int maxNum = num; // track the maximum number found
//        try all possible swaps
        for(int i = 0 ; i < n ; i++) {
            for(int j = i + 1 ; j < n ; j++) {
                char[] numeral = numStr.toCharArray();
//                swap digits at i and j
                char temp = numeral[i];
                numeral[i] = numeral[j];
                numeral[j] = temp;
                int tempNum = Integer.parseInt(new String(numeral));
                maxNum = Math.max(maxNum, tempNum);
            }
        }
        return maxNum;
    }
}

/*
You are given an integer num. You can swap two digits at most once to get the maximum valued number.
Return the maximum valued number you can get.

Example 1:
Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: num = 9973
Output: 9973
Explanation: No swap.

Constraints:
0 <= num <= 108
 */