package dailychallenge.medium;

public class LongestHappyString1405 {
    public static void main(String[] args) {
        System.out.println(longestDiverseString(1,1,7));
    }

//    greedy approach; time: O(a+b+c), space: O(1)
    public static String longestDiverseString(int a, int b, int c) {
        int currA = 0, currB = 0, currC = 0;
//        maximum total iterations is given by the sum of a, b, and c
        int totalIterations = a + b + c;
        StringBuilder ans = new StringBuilder();
        for(int i = 0 ; i < totalIterations ; i++) {
//            if 'a' is maximum and its streak is less than 2 OR if current streak of 'b' or 'c' is 2
            if((a >= b && a >= c && currA != 2) ||
                    (a > 0 && (currB == 2 || currC == 2))) {
                ans.append('a');
                a--;
                currA++;
                currB = 0;
                currC = 0;
            } else if((b >= a && b >= c && currB != 2) ||
                    (b > 0 && (currA == 2 || currC == 2))) {
                ans.append('b');
                b--;
                currB++;
                currA = 0;
                currC = 0;
            } else if((c >= a && c >= b && currC != 2) ||
                    (c > 0 && (currA == 2 || currB == 2))) {
                ans.append('c');
                c--;
                currC++;
                currA = 0;
                currB = 0;
            }
        }
        return ans.toString();
    }
}

/*
A string s is called happy if it satisfies the following conditions:
s only contains the letters 'a', 'b', and 'c'.
s does not contain any of "aaa", "bbb", or "ccc" as a substring.
s contains at most a occurrences of the letter 'a'.
s contains at most b occurrences of the letter 'b'.
s contains at most c occurrences of the letter 'c'.
Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".
A substring is a contiguous sequence of characters within a string.

Example 1:
Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.
Example 2:
Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It is the only correct answer in this case.

Constraints:
0 <= a, b, c <= 100
a + b + c > 0

 */