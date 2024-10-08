package dailychallenge.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinSwaps1963 {
    public static void main(String[] args) {
        String s = "][][";
        System.out.println(minSwaps(s));
    }

//    space optimized stack; time: O(n), space: O(1)
    public static int minSwaps(String s) {
        int stackSize = 0;
        for(int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);
            if(c == '[')
                stackSize++;
            else {
                if(stackSize > 0)
                    stackSize--;
            }
        }
        return (stackSize + 1) / 2;
    }

//    stack; time: O(n), space: O(n)
    public static int minSwaps1(String s) {
        int unbalanced = 0;
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);
            if(c == '[')
                stack.push(c);
            else {
                if(!stack.isEmpty())
                    stack.pop();
                else
                    unbalanced++;
            }
        }
//        note: unbalanced / 2 + unbalanced % 2; the second part is to account for an odd number of unbalanced
//        this can be simplified to the following;
        return (unbalanced + 1) / 2;
    }
}

/*
You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.
A string is called balanced if and only if:
It is the empty string, or
It can be written as AB, where both A and B are balanced strings, or
It can be written as [C], where C is a balanced string.
You may swap the brackets at any two indices any number of times.
Return the minimum number of swaps to make s balanced.

Example 1:
Input: s = "][]["
Output: 1
Explanation: You can make the string balanced by swapping index 0 with index 3.
The resulting string is "[[]]".
Example 2:
Input: s = "]]][[["
Output: 2
Explanation: You can do the following to make the string balanced:
- Swap index 0 with index 4. s = "[]][][".
- Swap index 1 with index 5. s = "[[][]]".
The resulting string is "[[][]]".
Example 3:
Input: s = "[]"
Output: 0
Explanation: The string is already balanced.

Constraints:
n == s.length
2 <= n <= 106
n is even.
s[i] is either '[' or ']'.
The number of opening brackets '[' equals n / 2, and the number of closing brackets ']' equals n / 2.
 */